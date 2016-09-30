package me.solidev.library.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by _SOLID
 * Date:2016/9/28
 * Time:15:04
 * Desc:
 */

public abstract class BaseFragment extends Fragment {
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutResourceID(), container, false);//setContentView(inflater, container);

        init();
        setUpView();
        setUpData();
        return mRootView;
    }

    protected abstract int setLayoutResourceID();

    /**
     * initialize before  setUpView and  setUpData
     */
    protected void init() {

    }

    protected abstract void setUpView();

    protected abstract void setUpData();

    protected <T extends View> T $(int id) {
        return (T) mRootView.findViewById(id);
    }

    protected View getRootView() {
        return mRootView;
    }
}
