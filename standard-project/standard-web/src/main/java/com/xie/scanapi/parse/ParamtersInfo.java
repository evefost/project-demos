package com.xie.scanapi.parse;

import com.xie.scanapi.Class2JsonUtils;
import com.xie.scanapi.ClassHelper;
import com.xie.scanapi.mappingResolver.ResolverSupport;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * 参数信息
 */
public class ParamtersInfo implements IInfo {

    private ResolverSupport resolverSupport;

    private Type[] actualTypeArguments;

    private Type rawType;

    //是否为泛型
    private boolean isGener = false;

    public ParamtersInfo(ResolverSupport resolverSupport, Type rawType) {
        this.rawType = rawType;
        this.resolverSupport = resolverSupport;
        parse();
    }


    public ParamtersInfo(ResolverSupport resolverSupport, Type rawType, Type[] actualTypeArguments) {
        this.resolverSupport = resolverSupport;
        this.rawType = rawType;
        this.actualTypeArguments = actualTypeArguments;
        this.isGener = true;

        parse();
    }


    @Override
    public void parse() {
        if (isGener) {
            //有泛型参数
            System.out.println("泛型参数：" + rawType.toString() + "\n 实参数组:");
            for(Type aType : actualTypeArguments){
                System.out.println( " 实参" + aType.toString());
            }

            Field[] declaredFields = ((Class) rawType).getDeclaredFields();
            for(Field field:declaredFields){
                System.out.println("字段 name:"+field.getName()+"对应泛参名"+field.getGenericType().toString());
            }

        } else {
            Class clz = (Class) rawType;
            if (ClassHelper.isBaseClass(clz)) {
                //基本类型

            } else {
                System.out.println("=======" + rawType.toString());
                String s = Class2JsonUtils.generateApiJsonForm((Class) rawType, true, true);
                System.out.println(s);
            }
        }

    }
}
