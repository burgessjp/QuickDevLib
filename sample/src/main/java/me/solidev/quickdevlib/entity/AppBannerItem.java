package me.solidev.quickdevlib.entity;

/**
 * Created by _SOLID
 * Date:2016/11/4
 * Time:14:36
 * Desc:
 */

public class AppBannerItem implements me.solidev.library.ui.widget.banner.BannerItem {

    private String imageUrl;

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String getTitle() {
        return null;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
