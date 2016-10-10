package me.solidev.quickdevlib.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.solidev.library.rx.TransformUtils;
import me.solidev.library.ui.fragment.AbsListFragment;
import me.solidev.library.ui.recyclerview.ClassTitleDecoration;
import me.solidev.library.ui.recyclerview.LinearDecoration;
import me.solidev.quickdevlib.entity.NewsItem;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by _SOLID
 * Date:2016/9/30
 * Time:14:07
 * Desc:分类列表实现
 */

public class ClassTitleListFragment extends AbsListFragment<NewsItem> {


    @Override
    protected void customConfig() {
        addItemDecoration(new ClassTitleDecoration(getContext(), getItems()));
        addItemDecoration(new LinearDecoration(getContext(), RecyclerView.VERTICAL, 1));
        //disEnablePullUp();
    }

    @Override
    public void loadData(final int pageIndex) {
        if (pageIndex > 3) {
            onDataSuccessReceived(pageIndex, new ArrayList<NewsItem>());
            return;
        }
        Observable
                .create(new Observable.OnSubscribe<List<NewsItem>>() {
                    @Override
                    public void call(Subscriber<? super List<NewsItem>> subscriber) {
                        try {
                            Thread.sleep(1000);//模拟网络延迟
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        subscriber.onNext(getMockData(pageIndex));
                    }
                })
                .compose(TransformUtils.<List<NewsItem>>defaultSchedulers())
                .subscribe(new Subscriber<List<NewsItem>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<NewsItem> newsItems) {

                        onDataSuccessReceived(pageIndex, getMockData(pageIndex));
                    }
                });


    }

    @NonNull
    private List<NewsItem> getMockData(int pageIndex) {
        List<NewsItem> list = new ArrayList<>();
        for (int i = (pageIndex - 1) * 20; i < 20 * pageIndex; i++) {
            NewsItem item = new NewsItem();
            item.setTitle("类别" + i / 5);

            item.setContent("content:" + i);
            item.setDate("2016-09-0" + i);
            item.setDocType(1);
            ArrayList<String> imgs = new ArrayList<>();
            imgs.add("http://img4.imgtn.bdimg.com/it/u=1168399963,3251010511&fm=21&gp=0.jpg");

            item.setImages(imgs);
            list.add(item);
        }
        return list;
    }


}
