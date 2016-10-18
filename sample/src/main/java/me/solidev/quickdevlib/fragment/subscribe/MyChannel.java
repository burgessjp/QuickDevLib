package me.solidev.quickdevlib.fragment.subscribe;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import me.solidev.library.ui.widget.subscribeview.SubscribeItem;

/**
 * Created by _SOLID
 * Date:2016/10/18
 * Time:11:03
 * Desc:
 */

@DatabaseTable(tableName = "MyChannel")
public class MyChannel implements SubscribeItem {


    @DatabaseField(columnName = "id", generatedId = true)
    private long id;
    @DatabaseField(columnName = "sort")
    private int sort;
    @DatabaseField(columnName = "title")
    private String title;
    @DatabaseField(columnName = "isFix")
    private int isFix;
    @DatabaseField(columnName = "isSubscribe")
    private int isSubscribe;
    @DatabaseField(columnName = "category")
    private String category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setIsFix(int isFix) {
        this.isFix = isFix;
    }

    @Override
    public int getIsFix() {
        return isFix;
    }

    @Override
    public void setIsSubscribe(int isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    @Override
    public int getIsSubscribe() {
        return isSubscribe;
    }

    @Override
    public void setSort(int sort) {
        this.sort = sort;
    }

    @Override
    public int getSort() {
        return sort;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }
}