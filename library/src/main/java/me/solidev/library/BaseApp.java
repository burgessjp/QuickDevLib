package me.solidev.library;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import me.solidev.library.utils.ToastUtil;

/**
 * Created by _SOLID
 * Date:2016/9/26
 * Time:14:39
 */

public class BaseApp extends Application {
    private static BaseApp mInstance;

    public static BaseApp getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ToastUtil.initialize(this);
    }

    @Override
    public File getCacheDir() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cacheDir = getExternalCacheDir();
            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {
                return cacheDir;
            }
        }
        return super.getCacheDir();
    }
}
