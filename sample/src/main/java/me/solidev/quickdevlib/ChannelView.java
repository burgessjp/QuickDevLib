
package me.solidev.quickdevlib;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.solidev.library.ui.adapter.MultiTypeAdapter;
import me.solidev.quickdevlib.entity.Channel;

/**
 * Created by _SOLID
 * Date:2016/8/25
 * Time:13:18
 * Desc:频道列表View
 */
public class ChannelView {

    private RecyclerView mRecyclerView;


    public ChannelView(Context ctx) {
        ViewGroup.LayoutParams layoutParams = new GridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mRecyclerView = (RecyclerView) LayoutInflater.from(ctx).inflate(R.layout.layout_list, null);
        mRecyclerView.setLayoutParams(layoutParams);

        mRecyclerView.setLayoutManager(new GridLayoutManager(ctx, 4));
    }

    public View setChannelList(List<Channel> channelList) {
        mRecyclerView.setAdapter(new MultiTypeAdapter(channelList));
        mRecyclerView.setVisibility(channelList.size() <= 0 ? View.GONE : View.VISIBLE);
        return mRecyclerView;
    }


}
