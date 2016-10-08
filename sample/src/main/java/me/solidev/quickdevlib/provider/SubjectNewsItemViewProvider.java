package me.solidev.quickdevlib.provider;

import android.support.annotation.NonNull;

import me.solidev.library.ui.adapter.ItemViewProvider;
import me.solidev.library.ui.adapter.ViewHolder;
import me.solidev.library.imageloader.ImageLoader;
import me.solidev.quickdevlib.R;
import me.solidev.quickdevlib.entity.NewsItem;


/**
 * Created by _SOLID
 * Date:2016/9/23
 * Time:13:11
 */

public class SubjectNewsItemViewProvider extends ItemViewProvider<NewsItem> {

    @Override
    protected int getItemViewLayoutId() {
        return R.layout.item_news_subject;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull NewsItem item) {
        holder.setText(R.id.tv_title, item.getTitle());
        ImageLoader.displayImage(holder.getImageView(R.id.iv_img), item.getImages().get(0));
    }

}
