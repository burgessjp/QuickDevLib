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
