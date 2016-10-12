package me.solidev.library.ui.widget.gridpager;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import me.solidev.library.R;
import me.solidev.library.imageloader.ImageLoader;
import me.solidev.library.ui.recyclerview.GridDecoration;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

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
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mPageAdapter);
    }

    public void setItems(List<? extends GridPagerItem> items) {
        if (items == null) {
            throw new IllegalArgumentException("the items cannot be null!");
        }
        mItems = items;
        mPageAdapter.notifyDataSetChanged();
    }

    public void setRows(int rows) {
        mRows = rows;
        mPageSize = mRows * mColumns;
    }

    public void setColumns(int columns) {
        mColumns = columns;
        mPageSize = mRows * mColumns;
    }


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
            recyclerView.addItemDecoration(new GridDecoration(getContext()));
            recyclerView.setBackgroundColor(Color.GRAY);
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
        public void onBindViewHolder(ItemHolder holder, int position) {
            holder.tv_title.setText(items.get(position).getTitle());
            ImageLoader.displayImage(holder.iv_icon, items.get(position).getImageUrl());
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
}
