package com.zsw.demoapplication.entity;

/**
 * @author zeng
 * @date 2016/12/9
 * @Description:
 */

public class NewsContent {
    private String title;
    private String url;

    public NewsContent(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
