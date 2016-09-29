package me.solidev.library.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import me.solidev.library.R;
import me.solidev.library.adapter.Item;
import me.solidev.library.adapter.MultiTypeAdapter;
import me.solidev.library.adapter.wrapper.HeaderAndFooterWrapper;
import me.solidev.library.utils.ToastUtil;
import me.solidev.library.widget.StatusViewLayout;
import me.solidev.library.widget.pulltorefresh.PullToRefresh;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by _SOLID
 * Date:2016/9/28
 * Time:15:02
 * Desc:
 */

public abstract class BaseRecyclerViewFragment<E extends Item> extends BaseFragment implements IList {

    private StatusViewLayout mStatusViewLayout;
    private PullToRefresh mPullToRefresh;
    private RecyclerView mRecyclerView;
    private MultiTypeAdapter mAdapter;
    private int mCurrentPageIndex;
    private List<E> mItems;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private List<View> mHeaderViews;


    @Override
    protected int setLayoutResourceID() {
        return R.layout.lib_fragment_base_recyclerview;
    }

    @Override
    protected void init() {
        mCurrentPageIndex = getInitPageIndex();
        mHeaderViews = new ArrayList<>();
        mItems = new ArrayList<>();
        mAdapter = getAdapter();
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);
    }

    protected void addHeaderView(View view) {
        if (!mHeaderViews.contains(view))
            mHeaderViews.add(view);
    }

    @Override
    protected void setUpView() {
        mStatusViewLayout = $(R.id.status_view_layout);
        mPullToRefresh = $(R.id.ptr);
        mRecyclerView = $(R.id.recyclerview);
        mRecyclerView.setAdapter(mHeaderAndFooterWrapper);

        mPullToRefresh.setListener(new PullToRefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(getInitPageIndex());
            }

            @Override
            public void onLoadMore() {
                loadData(++mCurrentPageIndex);
            }
        });
        mStatusViewLayout.setOnRetryListener(new View.OnClickListener() {//错误重试
            @Override
            public void onClick(View v) {
                mStatusViewLayout.showLoading();
                loadData(getInitPageIndex());
            }
        });
    }

    @Override
    protected void setUpData() {
        showLoading();
        loadData(getInitPageIndex());//初始加载首页数据
    }

    protected int getInitPageIndex() {
        return 1;
    }

    protected MultiTypeAdapter getAdapter() {
        return new MultiTypeAdapter(mItems);
    }

    protected List<E> getItems() {
        return mItems;
    }

    protected abstract void loadData(int pageIndex);

    /**
     * 列表数据接收成功
     *
     * @param pageIndex
     * @param items
     */
    protected void onDataSuccessReceived(int pageIndex, List<E> items) {
        showContent();
        if (pageIndex == getInitPageIndex()) {//刷新
            for (View view : mHeaderViews) {//添加HeaderView
                mHeaderAndFooterWrapper.addHeaderView(view);
            }
            mCurrentPageIndex = getInitPageIndex();
            mItems.clear();
            mItems.addAll(items);
            mHeaderAndFooterWrapper.notifyDataSetChanged();

        } else if (items != null && items.size() != 0) {//加载更多
            mItems.addAll(items);
            mHeaderAndFooterWrapper.notifyDataSetChanged();
            mRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRecyclerView.scrollBy(0, 100);
                }
            }, 1000);
        } else {//没有更多数据了
            ToastUtil.getInstance().showShortToast("没有更多数据了");
        }

    }

    //region 数据加载状态的处理
    @Override
    public void showError(Exception e) {
        mStatusViewLayout.showError(e.getMessage());
        mPullToRefresh.onFinishLoading();
    }

    @Override
    public void showEmpty(String msg) {
        mStatusViewLayout.showEmpty(msg);
        mPullToRefresh.onFinishLoading();
    }

    @Override
    public void showLoading() {
        mStatusViewLayout.showLoading();
    }

    @Override
    public void showContent() {
        mStatusViewLayout.showContent();
        mPullToRefresh.onFinishLoading();
    }
    //endregion
}
