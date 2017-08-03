package com.xie.vo;

import java.util.List;

/**
 * 线下订单分发
 */
@Descript("线下订单分发")
public class OrderDistribute {

    @Descript(required = true,message = " 主订单编号")
    private String orderCode;

    @Descript(required = true,message = " 接单公司编号")
    private String companyCode;

    @Descript(required = true, message = "委托服务 sku id数组")
    private List<Long> dids;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public void setDids(List<Long> dids) {
        this.dids = dids;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public List<Long> getDids() {
        return dids;
    }


}
