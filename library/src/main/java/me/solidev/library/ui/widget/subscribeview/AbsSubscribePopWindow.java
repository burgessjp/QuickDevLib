package me.solidev.library.ui.widget.subscribeview;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.solidev.library.R;
import me.solidev.library.rx.RxBus;

import static android.R.id.list;

/**
 * Created by _SOLID
 * Date:2016/8/10
 * Time:10:26
 */
public abstract class AbsSubscribePopWindow<E extends SubscribeItem> implements PopupWindow.OnDismissListener {


    private String mCategory = "default";

    private Context mContext;
    private PopupWindow mSubscribeWindow;
    private RecyclerView mSubscribeRecyclerView;
    private SubscribeAdapter mSubscribeAdapter;

    private List<E> mMyChannels = new ArrayList<>();
    private List<E> mOtherChannels = new ArrayList<>();


    public AbsSubscribePopWindow(Context context) {
        mContext = context;
        mMyChannels = new ArrayList<>();
        mOtherChannels = new ArrayList<>();

        View popupView = LayoutInflater.from(context).inflate(R.layout.lib_layout_subscribe_pop_window, null);
        mSubscribeWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, true);
        mSubscribeWindow.setBackgroundDrawable(new ColorDrawable());
        mSubscribeWindow.setOutsideTouchable(true);
        mSubscribeWindow.setFocusable(true);
        mSubscribeWindow.setAnimationStyle(R.style.subscribe_pop_window_anim_style);
        mSubscribeRecyclerView = (RecyclerView) popupView.findViewById(R.id.subscribe_recycler_view);
        initSubscribeRecyclerView();

        mSubscribeWindow.setOnDismissListener(this);

    }

    /***
     * @param category 订阅的类别，用来支持存在多个订阅的情况
     * @param context  上下文
     */
    public AbsSubscribePopWindow(Context context, String category) {
        this(context);
        this.mCategory = category;
    }

    private void initSubscribeRecyclerView() {
        GridLayoutManager manager = new GridLayoutManager(mContext, 4);
        mSubscribeRecyclerView.setLayoutManager(manager);

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mSubscribeRecyclerView);

        mSubscribeAdapter = new SubscribeAdapter(mContext, helper, mMyChannels, mOtherChannels);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = mSubscribeAdapter.getItemViewType(position);
                return viewType == SubscribeAdapter.TYPE_MY || viewType == SubscribeAdapter.TYPE_OTHER ? 1 : 4;
            }
        });
        mSubscribeRecyclerView.setAdapter(mSubscribeAdapter);

        mSubscribeAdapter.setOnMyChannelItemClickListener(new SubscribeAdapter.OnMyChannelItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                mSubscribeWindow.dismiss();
                RxBus.getInstance()
                        .post(new ChannelSubscribeEvent(ChannelSubscribeEvent.TYPE_ON_MY_CHANNEL_ITEM_CLICK, position));

            }
        });
        mSubscribeAdapter.setOnBackClickListener(new SubscribeAdapter.OnBackClickListener() {
            @Override
            public void onBackClick() {
                updateSubscribeInfo();
                sortList();
                RxBus.getInstance().post(new ChannelSubscribeEvent(ChannelSubscribeEvent.TYPE_ON_BACK_CLICK));
                mSubscribeWindow.dismiss();
            }
        });
    }


    /**
     * 更新订阅频道信息
     */
    private void updateSubscribeInfo() {
        List<E> list1 = mSubscribeAdapter.getMyChannelItems();
        for (int i = 0; i < list1.size(); i++) {
            E channel = list1.get(i);
            channel.setIsSubscribe(1);
            channel.setSort(i);
            updateItemInDB(channel);
        }
        mMyChannels = list1;

        List<E> list2 = mSubscribeAdapter.getOtherChannelItems();
        for (int i = 0; i < list2.size(); i++) {
            E channel = list2.get(i);
            channel.setIsSubscribe(0);
            channel.setSort(-1);
            updateItemInDB(channel);
        }
        mOtherChannels = list2;
    }

    //region 数据库操作（客户端实现）

    /**
     * 查询指定类别下的所有item
     *
     * @param category 类别
     * @return
     */
    public abstract List<E> getAllItemInDB(String category);

    /**
     * 查询指定的item
     *
     * @param category 类别名
     * @param title    title
     * @return
     */
    public abstract E getItemInDB(String category, String title);

    /**
     * 删除数据库中的一个item
     *
     * @param item
     */
    public abstract void deleteItemInDB(E item);

    /**
     * 向数据库中添加一个item
     *
     * @param item
     */
    public abstract void addItemInDB(E item);

    /**
     * 更新本地数据库中的item
     *
     * @param item item
     */
    public abstract void updateItemInDB(E item);
    //endregion

    /**
     * 更新数据库中的订阅数据
     * <p>
     * 1.从数据库中删除服务器方已不存在的频道
     * <p>
     * 2.刷新数据库中的数据
     *
     * @param channels 频道列表
     */
    private void updateDB(List<E> channels) {
        List<E> list_in_db = getAllItemInDB(mCategory);
        if (list_in_db != null && list_in_db.size() != 0) {
            for (E item : list_in_db) {
                boolean isContains = false;
                for (E channel : channels) {
                    if (channel.getTitle().equals(item.getTitle())) {
                        isContains = true;
                        break;
                    }
                }
                if (!isContains) {
                    deleteItemInDB(item);
                }
            }
        }

        for (E item : channels) {
            E item_in_db = getItemInDB(mCategory, item.getTitle());
            if (item_in_db == null) {//数据库中不存在
                item.setSort(Integer.MAX_VALUE);
                item.setCategory(mCategory);
                addItemInDB(item);
            } else {
                item_in_db.setTitle(item.getTitle());
                item.setIsFix(item.getIsFix());
                updateItemInDB(item_in_db);
            }
        }
    }

    /**
     * 从数据库获取订阅信息，并标记我的频道和其他频道
     */
    private void assignChannelsFromDB() {
        List<E> list = getAllItemInDB(mCategory);
        if (list != null && list.size() != 0) {
            mMyChannels.clear();
            mOtherChannels.clear();
            for (E item : list) {
                if (item.getIsSubscribe() == 1) {
                    mMyChannels.add(item);
                } else {
                    mOtherChannels.add(item);
                }
            }
        }

    }

    private void sortList() {
        Collections.sort(mMyChannels, new Comparator<E>() {
            @Override
            public int compare(E c1, E c2) {
                if (c1.getSort() - c2.getSort() < 0) {
                    return -1;
                } else if (c1.getSort() - c2.getSort() > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }


    /***
     * 设置新的数据
     *
     * @param channels
     */
    public void setNewData(List<E> channels) {
        for (E item : channels) {
            if (item.getIsSubscribe() == 1) {
                mMyChannels.add(item);
            } else {
                mOtherChannels.add(item);
            }
        }
        //1.根据传入的频道信息，刷新数据库信息
        updateDB(channels);
        //2.从数据库获取频道信息，并标记我的频道和其他频道
        assignChannelsFromDB();
        //3.对频道信息进行排序操作
        sortList();

        mSubscribeAdapter.notifyDataSetChanged();

    }

    public void showAsDropDown(View anchor) {
        mSubscribeWindow.showAsDropDown(anchor);
    }

    private boolean isShowing() {
        return mSubscribeWindow.isShowing();
    }

    public void dismiss() {
        if (isShowing()) {
            mSubscribeWindow.dismiss();
        }
    }

    @Override
    public void onDismiss() {
        updateSubscribeInfo();
        sortList();
        RxBus.getInstance().post(new ChannelSubscribeEvent(ChannelSubscribeEvent.TYPE_ON_DISMISS));
    }
}
