package me.solidev.quickdevlib;


import me.solidev.library.adapter.Item;

/**
 * Created by _SOLID
 * Date:2016/9/23
 * Time:13:07
 */

public class NewsItem implements Item {
    public String title;
    public String content;
    public int imgId;

    public NewsItem(String title, String content, int imgId) {
        this.content = content;
        this.imgId = imgId;
        this.title = title;
    }

}
