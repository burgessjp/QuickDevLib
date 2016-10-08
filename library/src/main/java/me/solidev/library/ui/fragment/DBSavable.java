package me.solidev.library.ui.fragment;

import me.solidev.library.ui.adapter.Item;

/**
 * Created by _SOLID
 * Date:2016/9/30
 * Time:13:44
 * Desc:数据保存接口
 */

public interface DBSavable<E extends Item> {

    boolean isExist(E e);

    void restore(E e);

}
