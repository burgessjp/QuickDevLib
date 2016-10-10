package me.solidev.library.ui.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import me.solidev.library.ui.adapter.wrapper.HeaderAndFooterWrapper;

/**
 * Created by _SOLID
 * Date:2016/10/10
 * Time:16:19
 * Desc:处理带有header的分类标题(待完善)
 */

public class ClassTitleDecoration2 extends ClassTitleDecoration {
    public ClassTitleDecoration2(Context context, List<? extends ClassTitleItem> items) {
        super(context, items);
    }

    @Override
    protected boolean isDataUnValid(int position, RecyclerView recyclerView) {
        boolean result = super.isDataUnValid(position, recyclerView);
        int headCount = 0;
        if (recyclerView.getAdapter() instanceof HeaderAndFooterWrapper) {
            headCount = ((HeaderAndFooterWrapper) recyclerView.getAdapter()).getHeadersCount();
        }
        if (headCount == 0) {
            return result;
        } else {
            return result || position < headCount;
        }
    }

    @Override
    protected int getAdjustPosition(int position, RecyclerView recyclerView) {
//        if (recyclerView.getAdapter() instanceof HeaderAndFooterWrapper) {
//            position -= ((HeaderAndFooterWrapper) recyclerView.getAdapter()).getHeadersCount();
//        }
        return super.getAdjustPosition(position, recyclerView);
    }
}
