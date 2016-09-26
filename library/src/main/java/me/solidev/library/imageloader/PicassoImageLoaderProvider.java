package me.solidev.library.imageloader;

import android.content.Context;

import com.squareup.picasso.Picasso;

import me.solidev.library.BaseApp;
import me.solidev.library.R;
import me.solidev.library.SettingManager;

/**
 * Created by _SOLID
 * Date:2016/5/13
 * Time:10:27
 */
public class PicassoImageLoaderProvider implements IImageLoaderProvider {
    @Override
    public void loadImage(ImageRequest request) {
        if (!SettingManager.getOnlyWifiLoadImg(BaseApp.getInstance())) {
            Picasso.with(BaseApp.getInstance()).load(request.getUrl()).placeholder(request.getPlaceHolder()).into(request.getImageView());
        } else {
            request.getImageView().setImageResource(R.drawable.default_load_img);
        }
    }

    @Override
    public void loadImage(Context context, ImageRequest request) {
        if (!SettingManager.getOnlyWifiLoadImg(BaseApp.getInstance())) {
            Picasso.with(context).load(request.getUrl()).placeholder(request.getPlaceHolder()).into(request.getImageView());
        } else {
            request.getImageView().setImageResource(R.drawable.default_load_img);
        }
    }
}
