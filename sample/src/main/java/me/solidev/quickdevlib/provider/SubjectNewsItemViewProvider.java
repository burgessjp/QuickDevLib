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
import me.solidev.quickdevlib.entity.NewsItem;


/**
 * Created by _SOLID
 * Date:2016/9/23
 * Time:13:11
 */

public class SubjectNewsItemViewProvider extends ItemViewProvider<NewsItem, SubjectNewsItemViewProvider.NewsHolder> {

    @NonNull
    @Override
    protected NewsHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_news_subject, parent, false);
        return new NewsHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull NewsHolder holder, @NonNull NewsItem item) {
        holder.tv_title.setText(item.getTitle());
        ImageLoader.displayImage(holder.iv_img, item.getImages().get(0));
    }

    static class NewsHolder extends RecyclerView.ViewHolder {

        private TextView tv_title;
        private ImageView iv_img;

        public NewsHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
        }
    }

}
