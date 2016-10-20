package me.solidev.quickdevlib.fragment.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.solidev.library.rx.TransformUtils;
import me.solidev.library.ui.fragment.AbsListFragment;
import me.solidev.library.ui.recyclerview.GridDecoration;
import me.solidev.quickdevlib.entity.image_type.GridImageItem;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by _SOLID
 * Date:2016/9/30
 * Time:14:07
 * Desc:网格列表实现
 */

public class GridListFragment extends AbsListFragment<GridImageItem> {

    @Override
    protected void customConfig() {
        addItemDecoration(new GridDecoration(getContext()));
    }

    @Override
    public void loadData(final int pageIndex) {
        if (pageIndex > 5) {
            onDataSuccessReceived(pageIndex, new ArrayList<GridImageItem>());
            return;
        }
        Observable
                .create(new Observable.OnSubscribe<List<GridImageItem>>() {
                    @Override
                    public void call(Subscriber<? super List<GridImageItem>> subscriber) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        subscriber.onNext(getMockData());
                    }
                })
                .compose(TransformUtils.<List<GridImageItem>>defaultSchedulers())
                .subscribe(new Subscriber<List<GridImageItem>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<GridImageItem> gridImageItems) {
                        onDataSuccessReceived(pageIndex, gridImageItems);
                    }
                });

    }

    @NonNull
    private List<GridImageItem> getMockData() {
        List<GridImageItem> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GridImageItem item = new GridImageItem();
            item.setImageTitle("title" + i);
            item.setImageUrl("http://upload-images.jianshu.io/upload_images/323199-42040f8641132827.jpg");
            list.add(item);
        }
        return list;
    }

    @NonNull
    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getContext(), 3);
    }


}
