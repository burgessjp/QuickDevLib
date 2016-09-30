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
import me.solidev.library.utils.ToastUtil;
import me.solidev.quickdevlib.R;
import me.solidev.quickdevlib.entity.NewsItem;


/**
 * Created by _SOLID
 * Date:2016/9/23
 * Time:13:11
 */

public class DefaultNewsItemViewProvider extends ItemViewProvider<NewsItem> {

    @Override
    protected int getItemViewLayoutId() {
        return R.layout.item_news_default;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull final NewsItem item) {
        holder.setText(R.id.tv_title, item.getTitle());
        holder.setText(R.id.tv_content, item.getContent());
        holder.setText(R.id.tv_date, item.getDate());

        ImageLoader.displayImage(holder.getImageView(R.id.iv_img), item.getImages().get(0));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.getInstance().showShortToast("DefaultNewsItem:" + item.getTitle());
            }
        });
    }

}
