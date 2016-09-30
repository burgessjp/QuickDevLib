package me.solidev.quickdevlib.fragment;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import me.solidev.library.fragment.AbListFragment;
import me.solidev.quickdevlib.entity.NewsItem;

/**
 * Created by _SOLID
 * Date:2016/9/30
 * Time:14:07
 * Desc:最简单的一种列表实现
 */

public class SimpleListFragment extends AbListFragment<NewsItem> {
    @Override
    public void loadData(int pageIndex) {
        List<NewsItem> list = getMockData();

        onDataSuccessReceived(pageIndex, list);
    }

    @NonNull
    private List<NewsItem> getMockData() {
        List<NewsItem> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            NewsItem item = new NewsItem();
            item.setTitle("title:" + i);
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
