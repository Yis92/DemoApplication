package com.zsw.demoapplication.http.entity;

import com.zsw.demoapplication.http.base.CommonResponse;

import java.util.List;

/**
 * Created by liu on 16/12/14.
 */

public class IndexVideoTitleResp extends CommonResponse<List<IndexVideoTitleResp>> {

    private String coTitle;//标题
    private String coUrl;//跳转地址
    private String co_desc;//内容描述
    private String coImgUrl;//图片地址

    public String getCoTitle() {
        return coTitle;
    }

    public void setCoTitle(String coTitle) {
        this.coTitle = coTitle;
    }

    public String getCoUrl() {
        return coUrl;
    }

    public void setCoUrl(String coUrl) {
        this.coUrl = coUrl;
    }

    public String getCo_desc() {
        return co_desc;
    }

    public void setCo_desc(String co_desc) {
        this.co_desc = co_desc;
    }

    public String getCoImgUrl() {
        return coImgUrl;
    }

    public void setCoImgUrl(String coImgUrl) {
        this.coImgUrl = coImgUrl;
    }
}
