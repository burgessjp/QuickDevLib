package me.solidev.library.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import me.solidev.library.R;
import me.solidev.library.ui.widget.loading.LVCircularJump;
import me.solidev.library.ui.widget.loading.LVCircularZoom;

/**
 * Created by _SOLID
 * Date:2016/8/9
 * Time:17:43
 * <p>
 * 用来显示加载状态的View
 */
public class StatusViewLayout extends FrameLayout {

    private int mEmptyViewResId;
    private int mErrorViewResId;
    private int mLoadingViewResId;

    private View mEmptyView;
    private View mErrorView;
    private View mLoadingView;

    private TextView lib_tv_error;
    private TextView lib_tv_empty_msg;


    private FrameLayout.LayoutParams mLayoutParams;
    private OnClickListener mOnRetryListener;

    private LVCircularZoom mLoadingProgressView;


    public StatusViewLayout(Context context) {
        this(context, null);
    }

    public StatusViewLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mEmptyViewResId = R.layout.lib_status_view_layout_empty;
        mErrorViewResId = R.layout.lib_status_view_layout_error;
        mLoadingViewResId = R.layout.lib_status_view_layout_loading;
        setUpView();
        //默认显示主内容
        showContent();
    }

    private void setUpView() {
        mLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParams.gravity = Gravity.CENTER;

        mLoadingView = LayoutInflater.from(getContext()).inflate(mLoadingViewResId, null);
        mErrorView = LayoutInflater.from(getContext()).inflate(mErrorViewResId, null);
        mEmptyView = LayoutInflater.from(getContext()).inflate(mEmptyViewResId, null);
        mLoadingProgressView = (LVCircularZoom) mLoadingView.findViewById(R.id.lv_loading_view);

        lib_tv_empty_msg = (TextView) mEmptyView.findViewById(R.id.lib_tv_empty_msg);
        lib_tv_error = (TextView) mErrorView.findViewById(R.id.lib_tv_error);

        addView(mLoadingView, mLayoutParams);
        addView(mErrorView, mLayoutParams);
        addView(mEmptyView, mLayoutParams);

        mErrorView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnRetryListener != null) {
                    showLoading();
                    mOnRetryListener.onClick(view);
                }
            }
        });
    }


    public void setOnRetryListener(OnClickListener listener) {
        mOnRetryListener = listener;
    }


    public void showLoading() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(View.GONE);
        }
        mLoadingView.setVisibility(View.VISIBLE);
        mLoadingProgressView.startAnim();

    }

    public void showError(String msg) {
        lib_tv_error.setText(msg);
        showError();
    }

    public void showError() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(View.GONE);
        }
        mErrorView.setVisibility(View.VISIBLE);
        mLoadingProgressView.stopAnim();
    }

    public void showEmpty(String msg) {
        lib_tv_empty_msg.setText(msg);
        showEmpty();
    }

    public void showEmpty() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(View.GONE);
        }
        mEmptyView.setVisibility(View.VISIBLE);
        mLoadingProgressView.stopAnim();
    }


    public void showContent() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(View.GONE);
        }
        getChildAt(getChildCount() - 1).setVisibility(View.VISIBLE);
        mLoadingProgressView.stopAnim();
    }


}
