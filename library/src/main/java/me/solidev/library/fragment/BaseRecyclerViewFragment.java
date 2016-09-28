package me.solidev.library.fragment;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.solidev.library.R;
import me.solidev.library.adapter.Item;
import me.solidev.library.adapter.Items;
import me.solidev.library.adapter.MultiTypeAdapter;
import me.solidev.library.widget.StatusViewLayout;
import me.solidev.library.widget.pulltorefresh.PullToRefresh;

/**
 * Created by _SOLID
 * Date:2016/9/28
 * Time:15:02
 * Desc:
 */

public abstract class BaseRecyclerViewFragment extends BaseFragment {

    protected StatusViewLayout mStatusViewLayout;
    protected PullToRefresh mPullToRefresh;
    protected RecyclerView mRecyclerView;
    protected MultiTypeAdapter mAdapter;
    protected int mCurrentPageIndex;
    protected List<? extends Item> mItems;


    @Override
    protected int setLayoutResourceID() {
        return R.layout.lib_fragment_base_recyclerview;
    }

    @Override
    protected void init() {
        mCurrentPageIndex = getInitPageIndex();
        mItems = new ArrayList<>();
        mAdapter = setAdapter();
    }


    @Override
    protected void setUpView() {
        mStatusViewLayout = $(R.id.status_view_layout);
        mPullToRefresh = $(R.id.ptr);
        mRecyclerView = $(R.id.recyclerview);

        mPullToRefresh.setListener(new PullToRefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(getInitPageIndex());
            }

            @Override
            public void onLoadMore() {
                loadData(1);
            }
        });
    }


    @Override
    protected void setUpData() {

    }

    protected int getInitPageIndex() {
        return 1;
    }

    protected abstract MultiTypeAdapter setAdapter();

    protected abstract void loadData(int pageIndex);

    protected void onDataSuccessReceived(int pageIndex, Items items) {
        if (pageIndex == getInitPageIndex()) {//刷新
            mCurrentPageIndex = getInitPageIndex();
            //mAdapter.addAll(items);

        } else {
            mCurrentPageIndex++;

        }
    }
}
