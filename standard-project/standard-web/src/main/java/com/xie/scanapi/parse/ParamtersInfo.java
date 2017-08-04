package com.xie.scanapi.parse;

import java.lang.reflect.Type;

/**
 * 参数信息
 */
public class ParamtersInfo implements IInfo {

    private Type[] actualTypeArguments;

    private Type rawType;

    //是否为泛型
    private boolean isGener = false;

    public Type[] getActualTypeArguments() {
        return actualTypeArguments;
    }

    public void setActualTypeArguments(Type[] actualTypeArguments) {
        this.actualTypeArguments = actualTypeArguments;
    }

    public Type getRawType() {
        return rawType;
    }

    public void setRawType(Type rawType) {
        this.rawType = rawType;
    }

    public boolean isGener() {
        return isGener;
    }

    public void setGener(boolean gener) {
        isGener = gener;
    }

    @Override
    public void parse() {

    }
}
