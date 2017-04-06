package me.solidev.library.ui.widget.gridpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import me.solidev.library.R;
import me.solidev.library.module.imageloader.ImageLoader;
import me.solidev.library.ui.widget.indicator.CircleIndicator;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by _SOLID
 * Date:2016/10/12
 * Time:13:12
 * Desc:
 */

public class GridPagerView extends FrameLayout {
    private int mRows = 2;
    private int mColumns = 4;
    private List<? extends GridPagerItem> mItems;
    private int mPageSize;
    private ViewPager mViewPager;
    private CircleIndicator mIndicator;
    private int mCurrentPage;

    public GridPagerView(Context context) {
        this(context, null);
    }

    public GridPagerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GridPagerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.lib_layout_grid_pager_view, this);
        mItems = new ArrayList<>();
        mPageSize = mRows * mColumns;
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mIndicator = (CircleIndicator) findViewById(R.id.indicator);
        mViewPager.setAdapter(mPageAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void setItems(List<? extends GridPagerItem> items) {
        if (items == null) {
            throw new IllegalArgumentException("the items cannot be null!");
        }
        mItems = items;
        mPageAdapter.notifyDataSetChanged();
        mIndicator.setViewPager(mViewPager);
    }

    public void setRows(int rows) {
        mRows = rows;
        mPageSize = mRows * mColumns;
    }

    public void setColumns(int columns) {
        mColumns = columns;
        mPageSize = mRows * mColumns;
    }

    //region indicator settings
    public void setIndicatorRadius(float mIndicatorRadius) {
        mIndicator.setIndicatorRadius(mIndicatorRadius);
    }

    public void setIndicatorMargin(float mIndicatorMargin) {
        mIndicator.setIndicatorMargin(mIndicatorMargin);
    }

    public void setIndicatorBackground(int mIndicatorBackground) {
        mIndicator.setIndicatorBackground(mIndicatorBackground);
    }

    public void setIndicatorSelectedBackground(int mIndicatorSelectedBackground) {
        mIndicator.setIndicatorSelectedBackground(mIndicatorSelectedBackground);
    }

    public void setIndicatorLayoutGravity(CircleIndicator.Gravity mIndicatorLayoutGravity) {
        mIndicator.setIndicatorLayoutGravity(mIndicatorLayoutGravity);
    }

    public void setIndicatorMode(CircleIndicator.Mode mIndicatorMode) {
        mIndicator.setIndicatorMode(mIndicatorMode);
    }
    //endregion

    //region PagerAdapter and ItemAdapter
    private PagerAdapter mPageAdapter = new PagerAdapter() {
        @Override
        public int getCount() {

            int totalSize = mItems.size();
            return mItems.size() / mPageSize + (totalSize % mPageSize == 0 ? 0 : 1);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            List<? extends GridPagerItem> items;
            if (position != getCount() - 1) {
                items = mItems.subList(position * mPageSize, position * mPageSize + mPageSize);
            } else {
                items = mItems.subList(position * mPageSize, mItems.size() - 1);
            }

            RecyclerView recyclerView = new RecyclerView(getContext());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), mColumns);
            gridLayoutManager.setAutoMeasureEnabled(true);
            recyclerView.setLayoutManager(gridLayoutManager);
            //recyclerView.addItemDecoration(new GridDividerItemDecoration(getContext()));
            recyclerView.setAdapter(new ItemAdapter(items));

            container.addView(recyclerView, new ViewGroup.LayoutParams(MATCH_PARENT, 200));
            return recyclerView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    };

    class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {
        List<? extends GridPagerItem> items;
        LayoutInflater inflater;

        ItemAdapter(List<? extends GridPagerItem> items) {
            this.items = items;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (inflater == null) {
                inflater = LayoutInflater.from(parent.getContext());
            }
            View itemView = inflater.inflate(R.layout.lib_item_grid_pager, parent, false);

            return new ItemHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, final int position) {
            holder.tv_title.setText(items.get(position).getTitle());
            ImageLoader.displayImage(holder.iv_icon, items.get(position).getImageUrl());
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(items.get(position), mCurrentPage * mPageSize + position);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class ItemHolder extends RecyclerView.ViewHolder {
            TextView tv_title;
            ImageView iv_icon;

            ItemHolder(View itemView) {
                super(itemView);
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            }
        }
    }
    //endregion

    //region 设置 Item 点击监听
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener<T extends GridPagerItem> {
        void onItemClick(T item, int position);
    }
    //endregion
}
