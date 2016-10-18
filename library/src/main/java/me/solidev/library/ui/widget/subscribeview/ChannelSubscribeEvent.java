
package me.solidev.library.ui.widget.subscribeview;

/**
 * Created by _SOLID
 * Date:2016/8/10
 * Time:10:59
 * Desc:用于订阅事件的处理
 */
public class ChannelSubscribeEvent {

    public static final int TYPE_ON_MY_CHANNEL_ITEM_CLICK = 0X000001;
    public static final int TYPE_ON_BACK_CLICK = 0X000002;
    public static final int TYPE_ON_DISMISS = 0X000003;

    int type;
    int position;

    public ChannelSubscribeEvent(int type) {
        this.type = type;
    }

    public ChannelSubscribeEvent(int type, int position) {
        this.type = type;
        this.position = position;
    }

    public int getType() {
        return type;
    }

    public int getPosition() {
        return position;
    }
}
