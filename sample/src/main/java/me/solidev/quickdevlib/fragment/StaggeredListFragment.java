package me.solidev.quickdevlib.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import me.solidev.library.ui.fragment.AbsListFragment;
import me.solidev.quickdevlib.entity.image_type.StaggeredImageItem;

/**
 * Created by _SOLID
 * Date:2016/9/30
 * Time:14:07
 * Desc:瀑布列表实现
 */

public class StaggeredListFragment extends AbsListFragment<StaggeredImageItem> {
    @Override
    public void loadData(int pageIndex) {
        if (pageIndex > 3) {
            onDataSuccessReceived(pageIndex, new ArrayList<StaggeredImageItem>());
            return;
        }
        List<StaggeredImageItem> list = getMockData();
        onDataSuccessReceived(pageIndex, list);
    }

    private List<StaggeredImageItem> getMockData() {
        List<StaggeredImageItem> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            StaggeredImageItem item = new StaggeredImageItem();
            item.setImageTitle("title" + i + 1);
            item.setImageUrl("http://upload-images.jianshu.io/upload_images/323199-42040f8641132827.jpg");
            list.add(item);
        }
        return list;
    }

    @NonNull
    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }
}
