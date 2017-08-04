package com.xie.vo;

import java.util.List;

/**
 * 线上分发订单分发
 */
public class OnlineOrderParams {

    @Descript(required = true, message = " 接单公司编号")
    private String companyCode;

    @Descript("描述")
    private String descript;

    @Descript(required = true,message = "订单明细,sku服务列表")
    private List<OrderParams> skuList;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public List<OrderParams> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<OrderParams> skuList) {
        this.skuList = skuList;
    }

    public static class OrderParams {


        @Descript(required = true, message = " 主订单编号")
        private String orderCode;

        @Descript(required = true, message = "委托服务 sku id")
        private Long did;


        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public Long getDid() {
            return did;
        }

        public void setDid(Long did) {
            this.did = did;
        }
    }

}
