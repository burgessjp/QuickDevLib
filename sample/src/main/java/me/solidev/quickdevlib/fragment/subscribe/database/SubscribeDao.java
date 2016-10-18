package me.solidev.quickdevlib.fragment.subscribe.database;

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import me.solidev.quickdevlib.fragment.subscribe.MyChannel;

/**
 * Created by _SOLID
 * Date:2016/10/18
 * Time:14:22
 * Desc:
 */

public class SubscribeDao extends BaseDao<MyChannel> {


    public MyChannel getItem(String category, String title) {
        try {
            QueryBuilder builder = daoOpe.queryBuilder();
            builder.where().eq("title", title).and().eq("category", category);
            return (MyChannel) builder.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<MyChannel> getAll(String category) {
        try {
            QueryBuilder builder = daoOpe.queryBuilder();
            builder.where().eq("category", category);
            return builder.query();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
