package me.solidev.quickdevlib;

import me.solidev.library.BaseApp;
import me.solidev.quickdevlib.fragment.subscribe.database.DatabaseHelper;

/**
 * Created by _SOLID
 * Date:2016/9/26
 * Time:14:40
 */

public class SampleApp extends BaseApp {

    public DatabaseHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiTypeInstaller.install();
        dbHelper = new DatabaseHelper(this, "SampleApp.db");
    }
}
