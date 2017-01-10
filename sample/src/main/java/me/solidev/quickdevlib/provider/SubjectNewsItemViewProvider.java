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
import me.solidev.quickdevlib.entity.NewsItem;


/**
 * Created by _SOLID
 * Date:2016/9/23
 * Time:13:11
 */

public class SubjectNewsItemViewProvider extends ItemViewProvider<NewsItem, SubjectNewsItemViewProvider.ViewHolder> {


    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_news_subject, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull NewsItem item) {
        holder.tv_title.setText(item.getTitle());
        ImageLoader.displayImage(holder.iv_img, item.getImages().get(0));
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
