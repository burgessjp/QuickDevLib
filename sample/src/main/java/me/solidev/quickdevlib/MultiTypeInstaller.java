package me.solidev.quickdevlib;

import me.solidev.library.ui.adapter.MultiTypePool;
import me.solidev.quickdevlib.entity.NewsItem;
import me.solidev.quickdevlib.entity.image_type.GridImageItem;
import me.solidev.quickdevlib.entity.image_type.StaggeredImageItem;
import me.solidev.quickdevlib.entity.news_type.DefaultNewsItem;
import me.solidev.quickdevlib.entity.news_type.SubjectNewsItem;
import me.solidev.quickdevlib.entity.news_type.TextNewsItem;
import me.solidev.quickdevlib.provider.DefaultNewsItemViewProvider;
import me.solidev.quickdevlib.provider.GridImageItemViewProvider;
import me.solidev.quickdevlib.provider.StaggeredImageItemViewProvider;
import me.solidev.quickdevlib.provider.SubjectNewsItemViewProvider;
import me.solidev.quickdevlib.provider.TextNewsItemViewProvider;

/**
 * Created by _SOLID
 * Date:2016/10/9
 * Time:10:53
 * Desc:
 */

public class MultiTypeInstaller {
    public static void install() {

        MultiTypePool.register(NewsItem.class, new DefaultNewsItemViewProvider());
        MultiTypePool.register(DefaultNewsItem.class, new DefaultNewsItemViewProvider());
        MultiTypePool.register(TextNewsItem.class, new TextNewsItemViewProvider());
        MultiTypePool.register(SubjectNewsItem.class, new SubjectNewsItemViewProvider());


        MultiTypePool.register(GridImageItem.class, new GridImageItemViewProvider());
        MultiTypePool.register(StaggeredImageItem.class, new StaggeredImageItemViewProvider());
    }
}
