package me.solidev.library.ui.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;

import java.util.List;

/**
 * Created by _SOLID
 * Date:2016/10/9
 * Time:11:16
 * Desc:
 */

public class ClassTitleDecoration extends RecyclerView.ItemDecoration {

    private final Paint mPaint;
    private final Context mContext;
    private Rect mTextBounds;
    private List<? extends ClassTitleItem> mItems;
    private int mClassTitleHeight;
    private int mClassTitleSize;
    private int mClassTitleBgColor;
    private int mClassTitleFontColor;

    public ClassTitleDecoration(Context context, List<? extends ClassTitleItem> items) {
        this.mContext = context;
        this.mItems = items;
        mClassTitleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36, context.getResources().getDisplayMetrics());
        mClassTitleSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, context.getResources().getDisplayMetrics());
        mClassTitleBgColor = Color.parseColor("#FFDFDFDF");
        mClassTitleFontColor = Color.parseColor("#FF999999");
        mTextBounds = new Rect();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(mClassTitleSize);
    }

    public ClassTitleDecoration setTextSize(int sp) {
        mClassTitleSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, mContext.getResources().getDisplayMetrics());
        return this;
    }

    public ClassTitleDecoration setTitleHeight(int dp) {
        mClassTitleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources().getDisplayMetrics());
        return this;
    }

    public ClassTitleDecoration setTitleBgColor(int color) {
        mClassTitleBgColor = color;
        return this;
    }

    public ClassTitleDecoration setTitleFontColor(int color) {
        mClassTitleFontColor = color;
        return this;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (isDataValid(position)) {
            return;
        }
        if (position > -1) {
            if (position == 0) {//等于0肯定要有title的
                outRect.set(0, mClassTitleHeight, 0, 0);
            } else {//其他的通过判断
                if (null != mItems.get(position).getClassTitle() && !mItems.get(position).getClassTitle().equals(mItems.get(position - 1).getClassTitle())) {
                    outRect.set(0, mClassTitleHeight, 0, 0);//不为空 且跟前一个tag不一样了，说明是新的分类
                } else {
                    outRect.set(0, 0, 0, 0);
                }
            }
        }

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int position = params.getViewLayoutPosition();
            if (isDataValid(position)) {
                return;
            }
            if (position == 0) {
                drawTitleArea(c, left, right, child, params, position);

            } else if (null != mItems.get(position).getClassTitle() && !mItems.get(position).getClassTitle().equals(mItems.get(position - 1).getClassTitle())) {

                drawTitleArea(c, left, right, child, params, position);
            }

        }
    }

    private void drawTitleArea(Canvas c, int left, int right, View child, RecyclerView.LayoutParams params, int position) {//最先调用，绘制在最下层
        mPaint.setColor(mClassTitleBgColor);
        c.drawRect(left, child.getTop() - params.topMargin - mClassTitleHeight, right, child.getTop() - params.topMargin, mPaint);
        mPaint.setColor(mClassTitleFontColor);
        mPaint.getTextBounds(mItems.get(position).getClassTitle(), 0, mItems.get(position).getClassTitle().length(), mTextBounds);
        c.drawText(mItems.get(position).getClassTitle(), child.getPaddingLeft(), child.getTop() - params.topMargin - (mClassTitleHeight / 2 - mTextBounds.height() / 2), mPaint);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int pos = ((LinearLayoutManager) (parent.getLayoutManager())).findFirstVisibleItemPosition();
        if (isDataValid(pos)) {
            return;
        }

        String title = mItems.get(pos).getClassTitle();
        View child = parent.findViewHolderForLayoutPosition(pos).itemView;
        boolean flag = false;//定义一个flag，Canvas是否位移过的标志
        if (pos < mItems.size() - 1) {
            if (!TextUtils.isEmpty(title) && !title.equals(mItems.get(pos + 1).getClassTitle())) {
                if (child.getHeight() + child.getTop() < mClassTitleHeight) {//当第一个可见的item在屏幕中还剩的高度小于title区域的高度时，我们也该开始做悬浮Title的“交换动画”
                    c.save();//每次绘制前 保存当前Canvas状态
                    flag = true;

                    //一种头部折叠起来的视效
                    //c.clipRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + child.getHeight() + child.getTop());

                    //上滑时，将canvas上移
                    c.translate(0, child.getHeight() + child.getTop() - mClassTitleHeight);
                }
            }
        }
        mPaint.setColor(mClassTitleBgColor);
        c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + mClassTitleHeight, mPaint);
        mPaint.setColor(mClassTitleFontColor);
        mPaint.getTextBounds(title, 0, title.length(), mTextBounds);
        c.drawText(title, child.getPaddingLeft(),
                parent.getPaddingTop() + mClassTitleHeight - (mClassTitleHeight / 2 - mTextBounds.height() / 2),
                mPaint);
        if (flag)
            c.restore();
    }

    private boolean isDataValid(int position) {
        return mItems == null || mItems.isEmpty() || position > mItems.size() - 1;
    }

}
