package me.solidev.library.ui.adapter.wrapper;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.solidev.library.ui.adapter.ViewHolder;

/**
 * Created by _SOLID
 * Date:2016/9/29
 * Time:9:30
 * Desc:加载更多的Adapter（有问题）
 */
public class LoadMoreWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int ITEM_TYPE_FOOTER_VIEW = Integer.MAX_VALUE - 2;
    public static final int ITEM_TYPE_LOAD_MORE_VIEW = Integer.MAX_VALUE - 3;

    private Context mContext;
    private RecyclerView.Adapter mInnerAdapter;
    private LinearLayout mFooterLayout;
    private View mLoadMoreView;
    private View mLoadMoreFailedView;
    private View mNoMoreView;
    private boolean mLoadMoreEnable;

    public LoadMoreWrapper(Context context, RecyclerView.Adapter adapter) {
        this.mContext = context;
        this.mLoadMoreEnable = true;
        this.mInnerAdapter = adapter;
    }

    public void addFooterView(View view) {
        mLoadMoreEnable = false;
        if (mFooterLayout == null) {
            mFooterLayout = new LinearLayout(view.getContext());
            mFooterLayout.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        mFooterLayout.addView(view);
        notifyItemChanged(getItemCount());
    }

    public void removeAllFooterView() {
        if (mFooterLayout == null) return;
        mFooterLayout.removeAllViews();
        mFooterLayout = null;
        this.notifyDataSetChanged();
    }

    public void showLoadMoreFailed() {
        if (mLoadMoreFailedView == null) {
            mLoadMoreFailedView = new TextView(mContext);
            ((TextView) mLoadMoreFailedView).setText("加载失败，请点我重试");
        }
        mLoadMoreFailedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAllFooterView();
                openLoadMore();
            }
        });
        addFooterView(mLoadMoreFailedView);
    }

    private void openLoadMore() {
        mLoadMoreEnable = true;
    }

    private void closeLoadMore() {
        mLoadMoreEnable = false;
    }

    public void loadComplete() {
        mLoadMoreEnable = false;
        if (mNoMoreView == null) {
            mNoMoreView = new TextView(mContext);
            ((TextView) mNoMoreView).setText("--end--");
        }
        addFooterView(mNoMoreView);
    }

    private boolean hasLoadMore() {
        return mLoadMoreEnable;
    }

    private boolean isShowLoadMore(int position) {
        return hasLoadMore() && (position >= mInnerAdapter.getItemCount());
    }

    private boolean isShowFooter() {
        return !mLoadMoreEnable && mFooterLayout != null;
    }

    private ViewHolder getLoadMoreHolder() {
        if (mLoadMoreView == null) {
            mLoadMoreView = new TextView(mContext);
            ((TextView) mLoadMoreView).setText("正在加载中");
        }
        return ViewHolder.createViewHolder(mContext, mLoadMoreView);
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowLoadMore(position)) {
            return ITEM_TYPE_LOAD_MORE_VIEW;
        } else if (isShowFooter()) {
            return ITEM_TYPE_FOOTER_VIEW;
        }
        return mInnerAdapter.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_FOOTER_VIEW) {
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mFooterLayout);
            return holder;
        } else if (viewType == ITEM_TYPE_LOAD_MORE_VIEW) {
            return getLoadMoreHolder();
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isShowLoadMore(position)) {
            if (mOnLoadMoreListener != null) {
                mOnLoadMoreListener.onLoadMoreRequested();
            }
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        WrapperUtils.onAttachedToRecyclerView(mInnerAdapter, recyclerView, new WrapperUtils.SpanSizeCallback() {
            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {
                if (isShowLoadMore(position)) {
                    return layoutManager.getSpanCount();
                }
                if (oldLookup != null) {
                    return oldLookup.getSpanSize(position);
                }
                return 1;
            }
        });
    }


    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);

        if (isShowLoadMore(holder.getLayoutPosition())) {
            setFullSpan(holder);
        }
    }

    private void setFullSpan(RecyclerView.ViewHolder holder) {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;

            p.setFullSpan(true);
        }
    }

    @Override
    public int getItemCount() {
        return mInnerAdapter.getItemCount() + (hasLoadMore() ? 1 : 0) + (isShowFooter() ? 0 : 1);
    }


    //region 加载更多监听
    public interface OnLoadMoreListener {
        void onLoadMoreRequested();
    }

    private OnLoadMoreListener mOnLoadMoreListener;

    public LoadMoreWrapper setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        if (loadMoreListener != null) {
            mOnLoadMoreListener = loadMoreListener;
        }
        return this;
    }
    //endregion
}
