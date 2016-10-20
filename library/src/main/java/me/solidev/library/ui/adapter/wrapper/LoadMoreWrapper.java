package me.solidev.library.ui.adapter.wrapper;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.solidev.library.ui.adapter.ViewHolder;
import me.solidev.library.ui.recyclerview.LoadMoreScrollListener;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by _SOLID
 * Date:2016/9/29
 * Time:9:30
 * Desc:load more wrap adapter for recyclerview
 */
public class LoadMoreWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_TYPE_LOAD_FAILED_VIEW = Integer.MAX_VALUE - 1;
    public static final int ITEM_TYPE_NO_MORE_VIEW = Integer.MAX_VALUE - 2;
    public static final int ITEM_TYPE_LOAD_MORE_VIEW = Integer.MAX_VALUE - 3;

    private Context mContext;
    private RecyclerView.Adapter mInnerAdapter;

    private View mLoadMoreView;
    private View mLoadMoreFailedView;
    private View mNoMoreView;

    private int mCurrentItemType = ITEM_TYPE_LOAD_MORE_VIEW;
    private LoadMoreScrollListener mLoadMoreScrollListener;


    private boolean isLoadError = false;//标记是否加载出错

    public LoadMoreWrapper(Context context, RecyclerView.Adapter adapter) {
        this.mContext = context;
        this.mInnerAdapter = adapter;
        mLoadMoreScrollListener = new LoadMoreScrollListener() {
            @Override
            public void loadMore() {
                if (mOnLoadListener != null) {
                    if (!isLoadError) {
                        showLoadMore();
                        mOnLoadListener.onLoadMore();
                    }
                }
            }
        };
    }

    public void showLoadMore() {
        mCurrentItemType = ITEM_TYPE_LOAD_MORE_VIEW;
        isLoadError = false;
        notifyItemChanged(getItemCount());
    }

    public void showLoadError() {
        mCurrentItemType = ITEM_TYPE_LOAD_FAILED_VIEW;
        isLoadError = true;
        notifyItemChanged(getItemCount());
    }

    public void showLoadComplete() {
        mCurrentItemType = ITEM_TYPE_NO_MORE_VIEW;
        isLoadError = false;
        notifyItemChanged(getItemCount());
    }

    //region Get ViewHolder
    private ViewHolder getLoadMoreViewHolder() {
        if (mLoadMoreView == null) {
            mLoadMoreView = new TextView(mContext);
            mLoadMoreView.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
            mLoadMoreView.setPadding(20, 20, 20, 20);
            ((TextView) mLoadMoreView).setText("正在加载中");
            ((TextView) mLoadMoreView).setGravity(Gravity.CENTER);
        }
        return ViewHolder.createViewHolder(mContext, mLoadMoreView);
    }

    private ViewHolder getLoadFailedViewHolder() {
        if (mLoadMoreFailedView == null) {
            mLoadMoreFailedView = new TextView(mContext);
            mLoadMoreFailedView.setPadding(20, 20, 20, 20);
            mLoadMoreFailedView.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
            ((TextView) mLoadMoreFailedView).setText("加载失败，请点我重试");
            ((TextView) mLoadMoreFailedView).setGravity(Gravity.CENTER);
        }
        return ViewHolder.createViewHolder(mContext, mLoadMoreFailedView);
    }

    private ViewHolder getNoMoreViewHolder() {
        if (mNoMoreView == null) {
            mNoMoreView = new TextView(mContext);
            mNoMoreView.setPadding(20, 20, 20, 20);
            mNoMoreView.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
            ((TextView) mNoMoreView).setText("--end--");
            ((TextView) mNoMoreView).setGravity(Gravity.CENTER);
        }
        return ViewHolder.createViewHolder(mContext, mNoMoreView);
    }
    //endregion

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return mCurrentItemType;
        }
        return mInnerAdapter.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_NO_MORE_VIEW) {
            return getNoMoreViewHolder();
        } else if (viewType == ITEM_TYPE_LOAD_MORE_VIEW) {
            return getLoadMoreViewHolder();
        } else if (viewType == ITEM_TYPE_LOAD_FAILED_VIEW) {
            return getLoadFailedViewHolder();
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == ITEM_TYPE_LOAD_FAILED_VIEW) {
            mLoadMoreFailedView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnLoadListener != null) {
                        mOnLoadListener.onRetry();
                        showLoadMore();
                    }
                }
            });
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        WrapperUtils.onAttachedToRecyclerView(mInnerAdapter, recyclerView, new WrapperUtils.SpanSizeCallback() {
            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {
                if (position == getItemCount() - 1) {
                    return layoutManager.getSpanCount();
                }
                if (oldLookup != null) {
                    return oldLookup.getSpanSize(position);
                }
                return 1;
            }
        });
        recyclerView.addOnScrollListener(mLoadMoreScrollListener);
    }


    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);

        if (holder.getLayoutPosition() == getItemCount() - 1) {
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
        return mInnerAdapter.getItemCount() + 1;
    }


    //region 加载监听

    public interface OnLoadListener {
        void onRetry();

        void onLoadMore();
    }

    private OnLoadListener mOnLoadListener;

    public LoadMoreWrapper setOnLoadListener(OnLoadListener onLoadListener) {
        mOnLoadListener = onLoadListener;
        return this;
    }

    //endregion
}
