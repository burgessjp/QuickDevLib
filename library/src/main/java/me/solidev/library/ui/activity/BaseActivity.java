package me.solidev.library.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by _SOLID
 * Date:2016/10/10
 * Time:17:12
 * Desc:
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
        setContentView(setLayoutResourceID());
        setUpView();
        setUpData();
    }

    protected abstract int setLayoutResourceID();

    protected abstract void setUpView();

    protected abstract void setUpData();

    /***
     * 用于在初始化View之前做一些事
     */
    protected void init(Bundle savedInstanceState) {

    }


    protected <T extends View> T $(int id) {
        return (T) super.findViewById(id);
    }


}
