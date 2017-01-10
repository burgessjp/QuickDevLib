package me.solidev.quickdevlib.provider;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.drakeet.multitype.ItemViewProvider;
import me.solidev.library.ui.widget.banner.BannerController;
import me.solidev.quickdevlib.R;
import me.solidev.quickdevlib.entity.AppBannerItem;
import me.solidev.quickdevlib.entity.Banners;

/**
 * Created by _SOLID
 * Date:2017/1/10
 * Time:16:13
 * Desc:
 */
public class BannerItemViewProvider
        extends ItemViewProvider<Banners, BannerItemViewProvider.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        BannerController banner = new BannerController(inflater.getContext());
        return new ViewHolder(banner);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Banners banners) {
        holder.banner.setBannerList(banners.banners);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        BannerController banner;

        ViewHolder(BannerController banner) {
            super(banner.getView());
            this.banner = banner;
        }
    }
}