package me.solidev.library.ui.recyclerview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by _SOLID
 * Date:2016/10/9
 * Time:16:12
 * Desc:用于RecyclerView加载更多的监听，实现滑动到底部自动加载更多
 */

public abstract class LoadMoreScrollListener extends RecyclerView.OnScrollListener {


    private int previousTotal;
    private boolean isLoading = true;

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = lm.getItemCount();
        int lastVisibleItemPosition = lm.findLastVisibleItemPosition();

        if (isLoading) {
            if (totalItemCount > previousTotal) {//加载更多结束
                isLoading = false;
                previousTotal = totalItemCount;
            } else if (totalItemCount < previousTotal) {//用户刷新结束
                previousTotal = totalItemCount;
                isLoading = false;
            } else {//有可能是在第一页刷新也可能是加载完毕

            }


        }
        if (!isLoading && visibleItemCount > 0 && totalItemCount - 1 == lastVisibleItemPosition && recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
            loadMore();
        }

    }


    public abstract void loadMore();
}
