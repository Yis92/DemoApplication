package com.zsw.demoapplication.http.entity;

import com.zsw.demoapplication.http.base.CommonResponse;

import java.util.List;

/**
 * Created by liu on 16/12/14.
 */

public class IndexChannelResp extends CommonResponse<List<IndexChannelResp>> {

    private String seName;
    private int id;

    public String getSeName() {
        return seName;
    }

    public void setSeName(String seName) {
        this.seName = seName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
