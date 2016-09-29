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

public class DefaultNewsItemViewProvider extends ItemViewProvider<NewsItem, DefaultNewsItemViewProvider.NewsHolder> {

    @NonNull
    @Override
    protected NewsHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_news_default, parent, false);
        return new NewsHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull NewsHolder holder, @NonNull NewsItem item) {
        holder.tv_title.setText(item.getTitle());
        holder.tv_content.setText(item.getContent());
        holder.tv_date.setText(item.getDate());

        ImageLoader.displayImage(holder.iv_img, item.getImages().get(0));
    }

    static class NewsHolder extends RecyclerView.ViewHolder {

        private TextView tv_title;
        private TextView tv_content;
        private TextView tv_date;
        private ImageView iv_img;

        public NewsHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
        }
    }

}
