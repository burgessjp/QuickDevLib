
package me.solidev.quickdevlib;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.solidev.library.adapter.MultiTypeAdapter;
import me.solidev.quickdevlib.entity.Channel;

/**
 * Created by _SOLID
 * Date:2016/8/25
 * Time:13:18
 */
public class ChannelController {

    private Context context;
    private RecyclerView mRecyclerView;
    private MultiTypeAdapter mAdapter;


    public ChannelController(Context ctx) {
        context = ctx;
        ViewGroup.LayoutParams layoutParams = new GridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mRecyclerView = (RecyclerView) LayoutInflater.from(ctx).inflate(R.layout.layout_list, null);
        mRecyclerView.setLayoutParams(layoutParams);

        mRecyclerView.setLayoutManager(new GridLayoutManager(ctx, 4));
    }

    public View setChannelList(List<Channel> channelList) {
        mAdapter = new MultiTypeAdapter(channelList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setVisibility(mAdapter.getItemCount() <= 0 ? View.GONE : View.VISIBLE);
        return mRecyclerView;
    }


}
