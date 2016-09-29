package me.solidev.quickdevlib.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.solidev.library.adapter.Item;
import me.solidev.library.adapter.MultiTypeAdapter;
import me.solidev.library.fragment.BaseRecyclerViewFragment;
import me.solidev.library.rx.TransformUtils;
import me.solidev.library.utils.FileUtil;
import me.solidev.library.utils.json.JsonConvert;
import me.solidev.quickdevlib.ChannelController;
import me.solidev.quickdevlib.R;
import me.solidev.quickdevlib.entity.Channel;
import me.solidev.quickdevlib.entity.news_type.DefaultNewsItem;
import me.solidev.quickdevlib.entity.NewsItem;
import me.solidev.quickdevlib.entity.news_type.SubjectNewsItem;
import me.solidev.quickdevlib.entity.news_type.TextNewsItem;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by _SOLID
 * Date:2016/9/29
 * Time:9:30
 * Desc:
 */

public class ExampleListFragment extends BaseRecyclerViewFragment<NewsItem> {


    private ChannelController mChannelController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChannelController = new ChannelController(getContext());
    }

    @Override
    protected void loadData(final int pageIndex) {
        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        subscriber.onNext(FileUtil.getString(getContext(), "raw://news_list"));
                    }
                })
                .compose(TransformUtils.<String>defaultSchedulers())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String newsString) {
                        List<NewsItem> items;
                        List<Channel> channels;
                        try {
                            JSONObject jsonObject = new JSONObject(newsString);
                            JsonConvert<List<NewsItem>> convert = new JsonConvert<List<NewsItem>>() {
                            };
                            JsonConvert<List<Channel>> convertChannel = new JsonConvert<List<Channel>>() {
                            };
                            items = convert.parseData(jsonObject.getString("datas"));
                            channels = convertChannel.parseData(jsonObject.getString("channels"));
                            addHeaderView(mChannelController.setChannelList(channels));

                            onDataSuccessReceived(pageIndex, items);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }

    @Override
    protected MultiTypeAdapter getAdapter() {
        return new MultiTypeAdapter(getItems()) {
            @NonNull
            @Override
            public Class onFlattenClass(@NonNull Item item) {
                NewsItem newsItem = (NewsItem) item;
                switch (newsItem.getDocType()) {
                    case 1:
                        return DefaultNewsItem.class;
                    case 2:
                        return SubjectNewsItem.class;
                    case 3:
                        return TextNewsItem.class;
                    default:
                        return DefaultNewsItem.class;
                }
            }
        };
    }
}
