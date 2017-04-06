package me.solidev.quickdevlib.entity;

import me.solidev.library.module.banner.BannerItem;

/**
 * Created by _SOLID
 * Date:2016/11/4
 * Time:14:36
 * Desc:
 */

public class AppBannerItem implements BannerItem {

    private String imageUrl;
    private String title;

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
