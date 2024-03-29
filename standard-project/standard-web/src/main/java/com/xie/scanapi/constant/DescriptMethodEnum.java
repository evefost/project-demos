package com.xie.scanapi.constant;

/**
 * Created by xieyang on 17/3/5.
 */
public enum DescriptMethodEnum {

    VALUE("value", "描述默认值"),
    MESSAGE("message", "描述"),
    REQUIRED("required", "是否为必须");

    private String methodName;
    private String descript;

    DescriptMethodEnum(String methodName, String descript) {
        this.methodName = methodName;
        this.descript = descript;
    }



    @Override
    public String toString() {
        return methodName;
    }
}
