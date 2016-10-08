package me.solidev.quickdevlib.entity;

import me.solidev.library.ui.adapter.Item;

/**
 * Created by _SOLID
 * Date:2016/10/8
 * Time:13:27
 * Desc:
 */

public class ImageItem implements Item {
    private String imageTitle;
    private String imageUrl;

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
