package com.example.da_1.Model.mdNews;

public class newsTT {
    private String title;
    private String link;
    private String pubdate;

    public newsTT(String title, String pubdate) {
        this.title = title;
        this.pubdate = pubdate;
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
