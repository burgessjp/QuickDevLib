package me.solidev.quickdevlib.provider;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import me.solidev.library.adapter.ItemViewProvider;
import me.solidev.library.adapter.ViewHolder;
import me.solidev.library.imageloader.ImageLoader;
import me.solidev.quickdevlib.R;
import me.solidev.quickdevlib.entity.Channel;

/**
 * Created by _SOLID
 * Date:2016/9/29
 * Time:16:06
 * Desc:
 */

public class ChannelItemViewProvider extends ItemViewProvider<Channel> {

    @Override
    protected int getItemViewLayoutId() {
        return R.layout.item_channel;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Channel channel) {
        holder.setText(R.id.tv_title, channel.getChannelTitle());
        ImageLoader.displayImage(holder.getImageView(R.id.iv_icon), channel.getChannelIcon());
    }
}
