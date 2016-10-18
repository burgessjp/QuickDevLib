package me.solidev.library.ui.widget.subscribeview;

/**
 * Created by _SOLID
 * Date:2016/10/17
 * Time:16:54
 * Desc:
 */

public interface SubscribeItem {

    String getCategory();

    void setCategory(String category);

    void setTitle(String title);

    String getTitle();

    void setIsFix(int isFix);

    int getIsFix();

    void setIsSubscribe(int isSubscribe);

    int getIsSubscribe();

    void setSort(int sort);

    int getSort();

}
