package me.solidev.quickdevlib.provider;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import me.drakeet.multitype.ItemViewProvider;
import me.solidev.library.ui.adapter.ViewHolder;
import me.solidev.quickdevlib.R;
import me.solidev.quickdevlib.entity.NewsItem;


/**
 * Created by _SOLID
 * Date:2016/9/23
 * Time:13:11
 */

public class TextNewsItemViewProvider extends ItemViewProvider<NewsItem, TextNewsItemViewProvider.ViewHolder> {


    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_news_text, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull NewsItem item) {
        holder.tv_title.setText(item.getTitle());
        holder.tv_content.setText(item.getContent());
        holder.tv_date.setText(item.getDate());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_content;
        TextView tv_date;
        ImageView iv_img;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
        }
    }

}
