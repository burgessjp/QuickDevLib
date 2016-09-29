package me.solidev.library.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by _SOLID
 * Date:2016/9/29
 * Time:10:37
 * Desc:
 */

public class ToastUtil {
    private Context mContext;
    private static ToastUtil mInstance;
    private Toast mToast;

    public static ToastUtil getInstance() {
        return mInstance;
    }

    public static void initialize(Context ctx) {
        mInstance = new ToastUtil(ctx);
    }

    private ToastUtil(Context ctx) {
        mContext = ctx;
    }

    public void showShortToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(text);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }

    public void showLongToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(text);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.show();
    }
}
