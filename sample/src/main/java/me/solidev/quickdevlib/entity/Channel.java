package me.solidev.quickdevlib.entity;

import me.solidev.library.ui.adapter.Item;

/**
 * Created by _SOLID
 * Date:2016/9/29
 * Time:15:45
 * Desc:
 */

public class Channel implements Item {


    private int clickType;
    private int channelId;
    private String channelTitle;
    private String channelIcon;
    private String channelUrl;

    public int getClickType() {
        return clickType;
    }

    public void setClickType(int clickType) {
        this.clickType = clickType;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getChannelIcon() {
        return channelIcon;
    }

    public void setChannelIcon(String channelIcon) {
        this.channelIcon = channelIcon;
    }

    public String getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl;
    }
}
