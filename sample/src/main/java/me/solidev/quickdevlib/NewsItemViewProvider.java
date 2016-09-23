package me.solidev.quickdevlib;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by _SOLID
 * Date:2016/9/23
 * Time:13:11
 */

public class NewsItemViewProvider extends ItemViewProvider<NewsItem, NewsItemViewProvider.NewsHolder> {

    @NonNull
    @Override
    protected NewsHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_news, parent, false);
        return new NewsHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull NewsHolder holder, @NonNull NewsItem newsItem) {
        holder.title.setText(newsItem.title);
        holder.content.setText(newsItem.content);
        holder.img.setImageResource(newsItem.imgId);
    }

    static class NewsHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView content;
        private ImageView img;

        public NewsHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            content = (TextView) itemView.findViewById(R.id.tv_content);
            img = (ImageView) itemView.findViewById(R.id.iv_img);
        }
    }

}
