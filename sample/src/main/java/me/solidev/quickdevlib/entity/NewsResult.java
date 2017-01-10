package me.solidev.quickdevlib.entity;

import java.util.List;

/**
 * Created by _SOLID
 * Date:2016/11/7
 * Time:14:07
 * Desc:
 */

public class NewsResult {

    private List<AppBannerItem> banners;

    private List<NewsItem> datas;

    public List<NewsItem> getDatas() {
        return datas;
    }

    public void setDatas(List<NewsItem> datas) {
        this.datas = datas;
    }

    public List<AppBannerItem> getBanners() {
        return banners;
    }

    public void setBanners(List<AppBannerItem> banners) {
        this.banners = banners;
    }
}
