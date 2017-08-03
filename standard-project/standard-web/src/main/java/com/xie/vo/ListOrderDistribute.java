package com.xie.vo;


import java.util.List;

/**
 * 线下订单分发
 */
@Descript("线下订单分发")
public class ListOrderDistribute {

    @Descript(required =true,message = "订单集合")
    private List<OrderDistribute> list;

    User user;


}
