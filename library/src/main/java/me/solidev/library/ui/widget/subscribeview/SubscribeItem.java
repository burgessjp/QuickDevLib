package me.solidev.library.ui.widget.subscribeview;

/**
 * Created by _SOLID
 * Date:2016/10/17
 * Time:16:54
 * Desc:
 */

public interface SubscribeItem {

    //频道所属类别
    String getCategory();

    void setCategory(String category);

    //频道标题
    void setTitle(String title);

    String getTitle();

    //是否固定
    void setIsFix(int isFix);

    int getIsFix();

    //是否订阅
    void setIsSubscribe(int isSubscribe);

    int getIsSubscribe();

    //频道排序
    void setSort(int sort);

    int getSort();

}
