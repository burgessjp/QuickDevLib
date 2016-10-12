package me.solidev.quickdevlib.fragment;

import java.util.ArrayList;
import java.util.List;

import me.solidev.library.ui.fragment.BaseFragment;
import me.solidev.library.ui.widget.gridpager.GridPagerItem;
import me.solidev.library.ui.widget.gridpager.GridPagerView;
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
        mGridPagerView.setRows(3);
        mGridPagerView.setColumns(3);
    }

    @Override
    protected void setUpData() {
        mGridPagerView.setItems(getItems());
    }



    public List<GridPagerItem> getItems() {
        List<GridPagerItem> items = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            final int n = i;
            items.add(new GridPagerItem() {
                @Override
                public String getImageUrl() {
                    return "http://g.nxnews.net/zw/images/P020160825394278758084.png";
                }

                @Override
                public String getTitle() {
                    return "title" + n;
                }
            });
        }
        return items;
    }
}
