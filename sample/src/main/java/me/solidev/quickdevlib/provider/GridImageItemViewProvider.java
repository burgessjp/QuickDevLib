package me.solidev.quickdevlib.provider;

import android.support.annotation.NonNull;

import me.solidev.library.imageloader.ImageLoader;
import me.solidev.library.ui.adapter.ItemViewProvider;
import me.solidev.library.ui.adapter.ViewHolder;
import me.solidev.quickdevlib.R;
import me.solidev.quickdevlib.entity.ImageItem;

/**
 * Created by _SOLID
 * Date:2016/10/8
 * Time:13:29
 * Desc:
 */

public class GridImageItemViewProvider extends ItemViewProvider<ImageItem> {
    @Override
    protected int getItemViewLayoutId() {
        return R.layout.item_image;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ImageItem imageItem) {
        holder.setText(R.id.tv_title, imageItem.getImageTitle());
        ImageLoader.displayImage(holder.getImageView(R.id.iv_img), imageItem.getImageUrl());
    }
}
