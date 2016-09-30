package me.solidev.quickdevlib;

import me.solidev.library.BaseApp;
import me.solidev.library.adapter.MultiTypePool;
import me.solidev.quickdevlib.entity.Channel;
import me.solidev.quickdevlib.entity.NewsItem;
import me.solidev.quickdevlib.entity.news_type.DefaultNewsItem;
import me.solidev.quickdevlib.provider.ChannelItemViewProvider;
import me.solidev.quickdevlib.provider.DefaultNewsItemViewProvider;
import me.solidev.quickdevlib.entity.news_type.SubjectNewsItem;
import me.solidev.quickdevlib.provider.SubjectNewsItemViewProvider;
import me.solidev.quickdevlib.entity.news_type.TextNewsItem;
import me.solidev.quickdevlib.provider.TextNewsItemViewProvider;

/**
 * Created by _SOLID
 * Date:2016/9/26
 * Time:14:40
 */

public class SampleApp extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiTypePool.register(NewsItem.class, new DefaultNewsItemViewProvider());
        MultiTypePool.register(DefaultNewsItem.class, new DefaultNewsItemViewProvider());
        MultiTypePool.register(TextNewsItem.class, new TextNewsItemViewProvider());
        MultiTypePool.register(SubjectNewsItem.class, new SubjectNewsItemViewProvider());
        MultiTypePool.register(Channel.class, new ChannelItemViewProvider());
    }
}
