package me.solidev.library.ui.widget.banner;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by _SOLID
 * Date:2016/10/10
 * Time:15:28
 * Desc:
 */

public class BannerController {
    private Context mContext;
    private final BannerView mBannerView;
    private int mBannerHeight;
    private final ViewGroup.LayoutParams mLayoutParams;

    public BannerController(Context context) {
        mContext = context;
        mBannerHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, context.getResources().getDisplayMetrics());
        mLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mBannerHeight);
        mBannerView = new BannerView(context);
        mBannerView.setLayoutParams(mLayoutParams);

    }

    public BannerController setHeight(int dp) {
        mBannerHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources().getDisplayMetrics());
        mLayoutParams.height = mBannerHeight;
        return this;
    }

    public BannerController setBannerList(List<? extends BannerItem> bannerList) {
        mBannerView.setBannerList(bannerList);
        return this;
    }

    public View getView() {
        return mBannerView;

    }

    public void onResume() {
        mBannerView.startSwitchPage();
    }

    public void onPause() {
        mBannerView.cancelSwitchPage();
    }
}
