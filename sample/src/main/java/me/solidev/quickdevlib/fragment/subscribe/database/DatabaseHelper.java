package me.solidev.quickdevlib.fragment.subscribe.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import me.solidev.quickdevlib.R;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private Context mContext;

    public DatabaseHelper(Context context, String dbName) {
        super(context, dbName, null, 1);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            String[] tb = mContext.getResources().getStringArray(R.array.db_tb);
            for (int i = 0; i < tb.length; i++) {
                Class clazz = Class.forName(tb[i]);
                TableUtils.createTable(connectionSource, clazz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            String[] tb = mContext.getResources().getStringArray(R.array.db_tb);
            for (int i = 0; i < tb.length; i++) {
                Class clazz = Class.forName(tb[i]);
                TableUtils.dropTable(connectionSource, clazz, true);
            }
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
        mContext = null;
    }
}
