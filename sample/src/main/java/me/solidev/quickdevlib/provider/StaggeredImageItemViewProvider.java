package me.solidev.quickdevlib.provider;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

import me.solidev.library.imageloader.ImageLoader;
import me.solidev.library.ui.adapter.ItemViewProvider;
import me.solidev.library.ui.adapter.ViewHolder;
import me.solidev.library.utils.ConstUtils;
import me.solidev.library.utils.ConvertUtils;
import me.solidev.quickdevlib.R;
import me.solidev.quickdevlib.entity.ImageItem;

/**
 * Created by _SOLID
 * Date:2016/10/8
 * Time:13:29
 * Desc:
 */

public class StaggeredImageItemViewProvider extends ItemViewProvider<ImageItem> {
    @Override
    protected int getItemViewLayoutId() {
        return R.layout.item_image;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ImageItem imageItem) {
        holder.setText(R.id.tv_title, imageItem.getImageTitle());
        Random r = new Random();
        int height = ConvertUtils.dp2px(holder.getContext(), 100 + r.nextInt(100));
        ImageView iv_img = holder.getImageView(R.id.iv_img);
        ViewGroup.LayoutParams layoutParams = iv_img.getLayoutParams();
        layoutParams.height = height;
        iv_img.setLayoutParams(layoutParams);
        ImageLoader.displayImage(holder.getImageView(R.id.iv_img), imageItem.getImageUrl());
    }
}
