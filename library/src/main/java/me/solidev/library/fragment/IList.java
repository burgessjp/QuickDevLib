package me.solidev.library.fragment;

/**
 * Created by _SOLID
 * Date:2016/9/29
 * Time:9:28
 * Desc:
 */

public interface IList {

    void showError(Exception e);

    void showLoading();

    void showEmpty(String msg);

    void showContent();
}
