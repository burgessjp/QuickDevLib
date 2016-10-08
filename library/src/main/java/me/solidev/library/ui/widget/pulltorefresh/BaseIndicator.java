package me.solidev.library.ui.widget.pulltorefresh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Vincent Woo
 * Date: 2016/6/6
 * Time: 16:54
 */
public abstract class BaseIndicator {
    public abstract View createView(LayoutInflater inflater, ViewGroup parent);
    public abstract void onAction();
    public abstract void onUnAction();
    public abstract void onRestore();
    public abstract void onLoading();
}
