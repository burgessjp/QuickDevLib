package me.solidev.quickdevlib.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.solidev.library.ui.fragment.AbListFragment;
import me.solidev.library.ui.recyclerview.GridDecoration;
import me.solidev.library.ui.recyclerview.ClassTitleDecoration;
import me.solidev.quickdevlib.entity.image_type.GridImageItem;

/**
 * Created by _SOLID
 * Date:2016/9/30
 * Time:14:07
 * Desc:网格列表实现
 */

public class GridListFragment extends AbListFragment<GridImageItem> {
    @Override
    public void loadData(int pageIndex) {
        if (pageIndex > 5) {
            onDataSuccessReceived(pageIndex, new ArrayList<GridImageItem>());
            return;
        }
        List<GridImageItem> list = getMockData();
        onDataSuccessReceived(pageIndex, list);
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

    @Override
    protected void addItemDecoration(RecyclerView recyclerView) {
        recyclerView.addItemDecoration(new GridDecoration(getContext()));
    }
}
