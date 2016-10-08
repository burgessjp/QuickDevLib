package me.solidev.quickdevlib.entity;

import java.util.List;

import me.solidev.library.ui.adapter.Item;

/**
 * Created by _SOLID
 * Date:2016/9/29
 * Time:11:17
 * Desc:
 */

public class NewsItem implements Item {

    private int channelId;
    private int docId;
    private int docType;
    private int clickType;
    private String title;
    private String content;
    private String source;
    private SponsorBean sponsor;
    private String url;
    private String media;
    private String date;
    private List<String> images;

    private List<ImagesUrlBean> imagesUrl;

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public int getDocType() {
        return docType;
    }

    public void setDocType(int docType) {
        this.docType = docType;
    }

    public int getClickType() {
        return clickType;
    }

    public void setClickType(int clickType) {
        this.clickType = clickType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public SponsorBean getSponsor() {
        return sponsor;
    }

    public void setSponsor(SponsorBean sponsor) {
        this.sponsor = sponsor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<ImagesUrlBean> getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(List<ImagesUrlBean> imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public static class SponsorBean {
        private String nickname;
        private String avatar;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }

    public static class ImagesUrlBean {
        private int imageId;
        private String imageTitle;
        private String imageUrl;

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        public String getImageTitle() {
            return imageTitle;
        }

        public void setImageTitle(String imageTitle) {
            this.imageTitle = imageTitle;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
