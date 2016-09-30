package me.solidev.library.utils;

import android.content.Context;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import me.solidev.library.constant.AppConstant;

/**
 * Created by _SOLID
 * Date:2016/9/28
 * Time:11:28
 * Desc:文件处理工具类
 */

public class FileUtil {

    public static File getCacheDir(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cacheDir = context.getExternalCacheDir();
            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {
                return cacheDir;
            }
        }
        return context.getCacheDir();
    }

    //region getString
    public static String getString(Context context, String url) {
        if (url.startsWith(AppConstant.URL_RAW)) {
            return getStringFromRaw(context, url);
        }
        return "";
    }


    /**
     * @param context
     * @param url     raw://example
     * @return
     */
    private static String getStringFromRaw(Context context, String url) {
        String result = "";
        String name = url.substring(AppConstant.URL_RAW.length());
        int rawId = context.getResources().getIdentifier(name, "raw", context.getPackageName());
        if (rawId != 0) {
            InputStream inputStream = context.getResources().openRawResource(rawId);
            try {
                result = new String(getBytesFromStream(inputStream));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static byte[] getBytesFromStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024 * 1024];
        int readLen;
        while ((readLen = is.read(buf)) >= 0) {
            baos.write(buf, 0, readLen);
        }
        baos.close();

        return baos.toByteArray();
    }
    //endregion

    /**
     * 关闭IO
     *
     * @param closeables closeable
     */
    public static void closeIO(Closeable... closeables) {
        if (closeables == null) return;
        try {
            for (Closeable closeable : closeables) {
                if (closeable != null) {
                    closeable.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
