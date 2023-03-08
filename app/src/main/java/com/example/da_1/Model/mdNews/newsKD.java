package com.example.da_1.Model.mdNews;

public class newsKD {
    private String title;
    private String link;
    private String pubdate;
    private String img;

    public newsKD(String title, String pubdate, String img) {
        this.title = title;
        this.pubdate = pubdate;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }
}
