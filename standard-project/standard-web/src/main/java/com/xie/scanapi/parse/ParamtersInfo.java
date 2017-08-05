package com.xie.scanapi.parse;

import com.xie.scanapi.Class2JsonUtils;
import com.xie.scanapi.ClassHelper;
import com.xie.scanapi.mappingResolver.ResolverSupport;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

/**
 * 参数信息
 */
public class ParamtersInfo implements IInfo {

    private ResolverSupport resolverSupport;

    private Type[] actualTypeArguments;

    private Type rawType;

    private Map<String,Class> actualTypeMap = new HashMap<>();

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
            printGicAgrInfo();
            System.out.println( "字段参数===========================");

        } else {
            Class clz = (Class) rawType;
            if (ClassHelper.isBaseClass(clz)) {
                //基本类型

            } else {
                System.out.println("=======" + rawType.toString());
                String s = Class2JsonUtils.generateApiJsonForm((Class) rawType, true, true);
                System.out.println("方法参数名:"+s);
            }
        }

    }


    private void printGicAgrInfo(){
        System.out.println("打印泛型－实参");
        //泛型－实参
        //参数变量
        TypeVariable[] typeParameters = ((Class) rawType).getTypeParameters();
        for(int i=0;i<actualTypeArguments.length;i++){
            System.out.println("泛型:"+typeParameters[i]+"==实参:"+actualTypeArguments[i]);
            actualTypeMap.put(typeParameters[i].getName(), (Class) actualTypeArguments[i]);
        }
        Field[] declaredFields = ((Class) rawType).getDeclaredFields();
        for(Field field:declaredFields){
            System.out.println("字段 name:"+field.getName()+"对应泛参名"+field.getGenericType().toString()+"对应的实参:");
        }
    }
}
