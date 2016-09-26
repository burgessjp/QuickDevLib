package me.solidev.library;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;

import java.io.File;

import me.solidev.library.constant.AppConstant;
import me.solidev.library.utils.SpUtil;

/**
 * Created by _SOLID
 * Date:2016/9/26
 * Time:15:20
 * Desc:设置统一管理类
 */

public class SettingManager {

    //region 图片WIFI加载
    public static boolean getOnlyWifiLoadImg(Context ctx) {
        return SpUtil.getBoolean(ctx, AppConstant.ONLY_WIFI_LOAD_IMG, false);
    }

    public static void setOnlyWifiLoadImg(Context ctx, boolean isOn) {
        SpUtil.putBoolean(ctx, AppConstant.ONLY_WIFI_LOAD_IMG, isOn);
    }
    //endregion

    //region WebView字体大小
    public static int getWebViewFontSize(Context ctx) {
        return SpUtil.getInt(ctx, AppConstant.WEB_VIEW_FONT_SIZE, 2);
    }

    public static void setWebViewFontSize(Context ctx, int size) {
        SpUtil.putInt(ctx, AppConstant.WEB_VIEW_FONT_SIZE, size);
    }
    //endregion

    //region 缓存相关

    /**
     * 计算缓存大小
     *
     * @param listener
     */
    public static void countDirSizeTask(final CountDirSizeListener listener) {
        new Thread() {
            public void run() {
                final long result = getDirSize(BaseApp.getInstance().getCacheDir());
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(result);
                    }
                });
            }
        }.start();
    }

    /**
     * 清除缓存
     *
     * @param listener
     */
    public static void clearAppCache(final ClearCacheListener listener) {
        new Thread() {
            public void run() {
                clearFile(BaseApp.getInstance().getCacheDir());
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult();
                    }
                });
            }
        }.start();
    }

    private static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file); // 递归调用继续统计
            }
        }
        return dirSize;
    }

    private static void clearFile(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                clearFile(child);
            }
        } else {
            file.delete();
        }
    }

    public static String formatFileSize(long fileS) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }

        if (fileSizeString.startsWith(".")) {
            return "0B";
        }
        return fileSizeString;
    }

    public interface ClearCacheListener {
        void onResult();
    }

    public interface CountDirSizeListener {
        void onResult(long result);
    }
    //endregion

    //region App版本
    public static String getVersionName(Context ctx) {
        PackageManager packageManager = ctx.getPackageManager();
        try {
            PackageInfo packInfo = packageManager.getPackageInfo(ctx.getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getVersionCode(Context ctx) {
        PackageManager packageManager = ctx.getPackageManager();
        try {
            PackageInfo packInfo = packageManager.getPackageInfo(ctx.getPackageName(), 0);
            return packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }
    //endregion
}
