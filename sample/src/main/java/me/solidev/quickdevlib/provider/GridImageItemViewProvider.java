package me.solidev.quickdevlib.provider;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import me.drakeet.multitype.ItemViewProvider;
import me.solidev.library.module.imageloader.ImageLoader;
import me.solidev.quickdevlib.R;
import me.solidev.quickdevlib.entity.ImageItem;

/**
 * Created by _SOLID
 * Date:2016/10/8
 * Time:13:29
 * Desc:
 */

public class GridImageItemViewProvider extends ItemViewProvider<ImageItem, GridImageItemViewProvider.ViewHolder> {
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ImageItem imageItem) {
        holder.tv_title.setText(imageItem.getImageTitle());
        ImageLoader.displayImage(holder.iv_img, imageItem.getImageUrl());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        ImageView iv_img;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
        }
    }
}
