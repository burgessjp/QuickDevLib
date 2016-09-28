package me.solidev.quickdevlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.solidev.library.adapter.Item;
import me.solidev.library.adapter.MultiTypeAdapter;
import me.solidev.library.adapter.MultiTypePool;
import me.solidev.library.widget.pulltorefresh.PullToRefresh;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PullToRefresh mPtr;
    private MultiTypeAdapter mAdapter;
    List<NewsItem> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MultiTypePool.register(NewsItem.class, new NewsItemViewProvider());

        mDatas = new ArrayList<>();
        mDatas.addAll(getMockData());

        mAdapter = new MultiTypeAdapter(mDatas);
        mPtr = (PullToRefresh) findViewById(R.id.ptr);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setAdapter(mAdapter);
        mPtr.setListener(
                new PullToRefresh.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mPtr.onFinishLoading();
                    }

                    @Override
                    public void onLoadMore() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mDatas.addAll(getMockData());
                        mAdapter.notifyDataSetChanged();
                        mPtr.onFinishLoading();
                    }
                }
        );
    }

    private List<NewsItem> getMockData() {
        List<NewsItem> itemList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            NewsItem item = new NewsItem("title" + i, "content" + i, R.mipmap.ic_launcher);
            itemList.add(item);
        }
        return itemList;

    }
}
