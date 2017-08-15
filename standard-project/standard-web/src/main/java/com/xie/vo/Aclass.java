package com.xie.vo;

import com.xie.java.common.annotation.Descript;

/**
 * Created by Administrator on 2017/8/5.
 */
public class Aclass {


    @Descript(required = true,message = "类id")
    private Long aclassId;

    @Descript(message = "类名")
    private String aclassName;

}
