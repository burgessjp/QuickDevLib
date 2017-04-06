package me.solidev.quickdevlib.fragment.subscribe;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import me.solidev.library.ui.widget.subscribeview.AbsSubscribePopWindow;
import me.solidev.quickdevlib.fragment.subscribe.database.SubscribeDao;

/**
 * Created by _SOLID
 * Date:2016/10/18
 * Time:11:00
 * Desc:
 */

public class MySubscribePopWindow extends AbsSubscribePopWindow<MyChannel1> {

    private SubscribeDao dao = new SubscribeDao();
    private Realm realm;

    public MySubscribePopWindow(Context context) {
        super(context);
        RealmConfiguration realmConfig = new RealmConfiguration
                .Builder(context)
                .build();
        if (realm == null) {
            realm = Realm.getInstance(realmConfig);
            realm.beginTransaction();
        }
    }

    public MySubscribePopWindow(Context context, String category) {
        super(context, category);
    }

    @Override
    public List<MyChannel1> getAllItemInDB(String category) {
        return realm.where(MyChannel1.class).findAll();
    }

    @Override
    public MyChannel1 getItemInDB(String category, String title) {
        return realm.where(MyChannel1.class).equalTo("title", title).findFirst();
    }

    @Override
    public void deleteItemInDB(final MyChannel1 item) {
        realm.copyToRealm(item).deleteFromRealm();
    }

    @Override
    public void addItemInDB(final MyChannel1 item) {
        realm.copyToRealm(item);
    }

    @Override
    public void updateItemInDB(final MyChannel1 item) {
        realm.copyToRealmOrUpdate(item);
    }

    @Override
    public void onDismiss() {
        super.onDismiss();
        realm.commitTransaction();
        realm = null;
    }
}
