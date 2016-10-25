package me.solidev.quickdevlib;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import me.solidev.library.ui.activity.BaseActivity;
import me.solidev.library.utils.ToastUtil;
import me.solidev.quickdevlib.fragment.GridPagerFragment;
import me.solidev.quickdevlib.fragment.list.ClassTitleListFragment;
import me.solidev.quickdevlib.fragment.list.GridListFragment;
import me.solidev.quickdevlib.fragment.list.HeaderListFragment;
import me.solidev.quickdevlib.fragment.list.SimpleListFragment;
import me.solidev.quickdevlib.fragment.list.StaggeredListFragment;
import me.solidev.quickdevlib.fragment.subscribe.SubscribeFragment;


public class MainActivity extends BaseActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ArrayList<String> mDemoTitles;
    private BottomNavigationView mNavigationView;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpView() {

        mNavigationView = $(R.id.navigation);

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setElevation(0);
        mTabLayout = $(R.id.tab_layout);
        mViewPager = $(R.id.viewpager);

        mDemoTitles = new ArrayList<>();
        mDemoTitles.add("简单列表");
        mDemoTitles.add("带有Header");
        mDemoTitles.add("分组列表");
        mDemoTitles.add("网格");
        mDemoTitles.add("瀑布流");
        mDemoTitles.add("订阅");
        mDemoTitles.add("GridPager");

        for (String title : mDemoTitles) {
            mTabLayout.addTab(mTabLayout.newTab().setText(title));
        }
        mViewPager.setAdapter(new DemoPagerAdapter(getSupportFragmentManager(), mDemoTitles));
        mTabLayout.setupWithViewPager(mViewPager);

        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                ToastUtil.getInstance().showShortToast(item.getTitle() + "");
                return false;
            }
        });

    }

    @Override
    protected void setUpData() {

    }

    class DemoPagerAdapter extends FragmentStatePagerAdapter {

        List<String> mTitles;
        private int mChildCount = 0;

        public DemoPagerAdapter(FragmentManager fm, List<String> titles) {
            super(fm);
            mTitles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new SimpleListFragment();
            if ("简单列表".equals(mTitles.get(position))) {
                fragment = new SimpleListFragment();
            } else if ("带有Header".equals(mTitles.get(position))) {
                fragment = new HeaderListFragment();
            } else if ("分组列表".equals(mTitles.get(position))) {
                fragment = new ClassTitleListFragment();
            } else if ("网格".equals(mTitles.get(position))) {
                fragment = new GridListFragment();
            } else if ("瀑布流".equals(mTitles.get(position))) {
                fragment = new StaggeredListFragment();
            } else if ("订阅".equals(mTitles.get(position))) {
                fragment = new SubscribeFragment();
            } else if ("GridPager".equals(mTitles.get(position))) {
                fragment = new GridPagerFragment();
            }
            return fragment;
        }

        @Override
        public void notifyDataSetChanged() {
            mChildCount = getCount();
            super.notifyDataSetChanged();
        }

        @Override
        public int getItemPosition(Object object) {
            if (mChildCount > 0) {
                mChildCount--;
                return POSITION_NONE;
            }
            return super.getItemPosition(object);
        }

        @Override
        public int getCount() {
            return mTitles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

    }

}
