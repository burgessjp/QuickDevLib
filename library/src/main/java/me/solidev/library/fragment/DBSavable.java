package me.solidev.library.fragment;

import me.solidev.library.adapter.Item;

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
