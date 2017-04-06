package me.solidev.library.module.banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.solidev.library.R;
import me.solidev.library.module.imageloader.ImageLoader;

/**
 * Created by _SOLID
 * Date:2016/10/10
 * Time:13:46
 * Desc:Banner
 */

public class BannerView extends FrameLayout {
    public static final int SWITCH_PAGE_DURATION = 5 * 1000;
    private ViewPager mViewPager;
    private TextView mTvTitle;
    private List<? extends BannerItem> mBannerList = new ArrayList<>();
    private EDog mSwitchPageEDog = new EDog();
    private boolean mAutoSwitchPage = true;


    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.lib_layout_banner_view, this);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTvTitle = (TextView) findViewById(R.id.tv_title);

    }

    private void setViewPager() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                BannerView.this.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        cancelSwitchPage();
                        break;
                    case MotionEvent.ACTION_UP:
                        startSwitchPage();
                        break;
                }
                return false;
            }
        });
    }

    private void onPageSelected(int position) {
        if (position >= 0) {
            mTvTitle.setText(mAdapter.getPageTitle(position));
        }
    }

    public void setBannerList(List<? extends BannerItem> bannerList) {
        if (bannerList == null) {
            throw new IllegalArgumentException("the bannerList cannot be null!");
        }
        mBannerList = bannerList;

    }

    public void startScroll() {
        setViewPager();
        onPageSelected(mViewPager.getCurrentItem());
        if (mBannerList.size() > 1)
            startSwitchPage();
    }


    public void cancelSwitchPage() {
        mSwitchPageEDog.cancel();
    }

    public void startSwitchPage() {
        if (mAutoSwitchPage) {
            mSwitchPageEDog.feed(mSwitchPageTask, SWITCH_PAGE_DURATION);
        }
    }

    public void setPageTransformer(ViewPager.PageTransformer pageTransformer) {
        mViewPager.setPageTransformer(true, pageTransformer);
    }

    private Runnable mSwitchPageTask = new Runnable() {
        @Override
        public void run() {
            if (mViewPager.getAdapter().getCount() > 0) {
                int switchToItem = (mViewPager.getCurrentItem() + 1) % mViewPager.getAdapter().getCount();
                mViewPager.setCurrentItem(switchToItem);
            }
        }
    };

    private PagerAdapter mAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return mBannerList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BannerItem item = mBannerList.get(position);
            ImageView imgView = new ImageView(getContext());
            imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            String imageUrl = item.getImageUrl();
            ImageLoader.displayImage(imgView, imageUrl);
            container.addView(imgView);
            return imgView;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = mBannerList.get(position).getTitle();
            return TextUtils.isEmpty(title) ? "无标题" : title;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    };

}
