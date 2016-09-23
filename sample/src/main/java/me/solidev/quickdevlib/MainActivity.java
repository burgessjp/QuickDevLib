package me.solidev.quickdevlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.MultiTypePool;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MultiTypePool.register(NewsItem.class, new NewsItemViewProvider());


        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setAdapter(new MultiTypeAdapter(getMockData()));
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
