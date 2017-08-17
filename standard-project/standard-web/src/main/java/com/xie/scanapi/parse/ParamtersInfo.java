package com.xie.scanapi.parse;

import com.xie.scanapi.Class2JsonUtils;
import com.xie.scanapi.mappingResolver.ResolverSupport;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 参数信息
 */
public class ParamtersInfo implements IInfo {

    public static final int BASE = 1;//1

    public static final int OBJ = 2;//10

    public static final int GIC_OBJ = 4;//100

    public int pType = 1;

    private ResolverSupport resolverSupport;


    private Type paramsType;

    private Map<String, Class> actualTypeMap = new HashMap<>();

    //是否为泛型
    private boolean isGener = false;

    public ParamtersInfo(ResolverSupport resolverSupport, Type paramType) {
        this.paramsType = paramType;
        this.resolverSupport = resolverSupport;

    }


    @Override
    public StringBuffer parse() {
        StringBuffer sb = new StringBuffer();
        if(paramsType instanceof Class){
            StringBuffer jsonDes = Class2JsonUtils.generateApiParamDescript((Class) paramsType);
            sb.append(jsonDes);

        }
        sb.append("\n``` \n");
        StringBuffer rs = Class2JsonUtils.generateApiJsonForm(paramsType, false, true);
        sb.append(rs);
        sb.append("\n``` ");
        return sb;
    }




}
