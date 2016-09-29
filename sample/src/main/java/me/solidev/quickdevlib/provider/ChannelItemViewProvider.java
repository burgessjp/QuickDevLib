package me.solidev.quickdevlib.provider;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import me.solidev.library.adapter.ItemViewProvider;
import me.solidev.library.imageloader.ImageLoader;
import me.solidev.quickdevlib.R;
import me.solidev.quickdevlib.entity.Channel;

/**
 * Created by _SOLID
 * Date:2016/9/29
 * Time:16:06
 * Desc:
 */

public class ChannelItemViewProvider extends ItemViewProvider<Channel, ChannelItemViewProvider.ChannelHolder> {

    @NonNull
    @Override
    protected ChannelHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_channel, parent, false);
        return new ChannelHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ChannelHolder holder, @NonNull Channel channel) {
        holder.tv_title.setText(channel.getChannelTitle());
        ImageLoader.displayImage(holder.iv_icon, channel.getChannelIcon());
    }

    static class ChannelHolder extends RecyclerView.ViewHolder {

        private TextView tv_title;
        private ImageView iv_icon;

        public ChannelHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
        }
    }
}
