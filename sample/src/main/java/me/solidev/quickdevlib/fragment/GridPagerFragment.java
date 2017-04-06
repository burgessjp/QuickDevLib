package me.solidev.quickdevlib.fragment;

import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import me.solidev.library.module.list.BaseFragment;
import me.solidev.library.ui.widget.gridpager.GridPagerItem;
import me.solidev.library.ui.widget.gridpager.GridPagerView;
import me.solidev.library.utils.ToastUtil;
import me.solidev.quickdevlib.R;

/**
 * Created by _SOLID
 * Date:2016/10/12
 * Time:14:06
 * Desc:
 */

public class GridPagerFragment extends BaseFragment {

    private GridPagerView mGridPagerView;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_grid_pager;
    }

    @Override
    protected void setUpView() {
        mGridPagerView = $(R.id.gridPager);
        mGridPagerView.setIndicatorSelectedBackground(ContextCompat.getColor(getContext(), R.color.orange));
        mGridPagerView.setRows(2);
        mGridPagerView.setColumns(4);
        mGridPagerView.setOnItemClickListener(new GridPagerView.OnItemClickListener<GridItemExample>() {
            @Override
            public void onItemClick(GridItemExample item, int position) {
                ToastUtil.getInstance().showShortToast("Item Click:" + position + "|type:" + item.type);
            }
        });
    }

    @Override
    protected void setUpData() {
        mGridPagerView.setItems(getItems());
    }


    public List<GridPagerItem> getItems() {
        List<GridPagerItem> items = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            final int n = i;
            GridItemExample item = new GridItemExample();
            item.imageUrl = "http://g.nxnews.net/xx/24181/images/P020160825622352690911.png";
            item.title = "校园";
            item.type = "type" + i;
            items.add(item);
        }
        return items;
    }

    class GridItemExample implements GridPagerItem {
        public String imageUrl;
        public String title;
        public String type;

        @Override
        public String getImageUrl() {
            return imageUrl;
        }

        @Override
        public String getTitle() {
            return title;
        }
    }
}
