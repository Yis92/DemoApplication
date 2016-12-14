package com.zsw.demoapplication.http;

import android.text.TextUtils;

/**
 * Created by liu on 16/12/14.
 */

public  class  HttpConstant {

    private static final String BASE_URL;

    static {

        BASE_URL = "http://139.196.166.34:7230/shserver/";
    }

    /**
     * 1.1	首页栏目
     */
    public static  final  String MENU = BASE_URL+"/menu/goMenuList.do";

    /**
     * 1.2	视频标题
     */
    public static  final  String VIDEO_TITLE = BASE_URL+"/menu/goMenuDetailList.do";
}
