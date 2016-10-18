package me.solidev.quickdevlib.fragment.subscribe;

import android.content.Context;

import java.util.List;

import me.solidev.library.ui.widget.subscribeview.AbsSubscribePopWindow;
import me.solidev.quickdevlib.fragment.subscribe.database.SubscribeDao;

/**
 * Created by _SOLID
 * Date:2016/10/18
 * Time:11:00
 * Desc:
 */

public class MySubscribePopWindow extends AbsSubscribePopWindow<MyChannel> {

    private SubscribeDao dao = new SubscribeDao();

    public MySubscribePopWindow(Context context) {
        super(context);
    }

    public MySubscribePopWindow(Context context, String category) {
        super(context, category);
    }

    @Override
    public List<MyChannel> getAllItemInDB(String category) {
       return dao.getAll(category);
    }

    @Override
    public MyChannel getItemInDB(String category, String title) {
        return dao.getItem(category, title);
    }

    @Override
    public void deleteItemInDB(MyChannel item) {
        dao.delete(item);
    }

    @Override
    public void addItemInDB(MyChannel item) {
        dao.add(item);
    }

    @Override
    public void updateItemInDB(MyChannel item) {
        dao.update(item);
    }


}
