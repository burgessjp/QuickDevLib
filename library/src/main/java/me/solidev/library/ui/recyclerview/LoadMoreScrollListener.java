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


    private int totalItemCount;
    private int previousTotal;
    private int visibleItemCount;
    private int firstVisibleItem;
    private boolean isLoading = true;


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = lm.getItemCount();
        firstVisibleItem = lm.findFirstVisibleItemPosition();
        if (isLoading) {
            if (totalItemCount > previousTotal) {//加载更多结束
                isLoading = false;
                previousTotal = totalItemCount;
            }
            if (totalItemCount < previousTotal) {//用户刷新结束
                previousTotal = totalItemCount;
                isLoading = false;
            }
        }
        if (!isLoading && totalItemCount - visibleItemCount <= firstVisibleItem) {
            loadMore();
            isLoading = true;
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

    }


    public abstract void loadMore();
}
