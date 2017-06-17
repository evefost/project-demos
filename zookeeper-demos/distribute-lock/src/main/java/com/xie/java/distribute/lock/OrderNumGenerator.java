package com.xie.java.distribute.lock;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xieyang on 6/17/17.
 */
public class OrderNumGenerator {

    private static int i = 0;

    public  String getOrderNumber() {

        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-");


        return "OOO"+ ++i;//sdf.format(new Date()) + ++i;
    }
}
