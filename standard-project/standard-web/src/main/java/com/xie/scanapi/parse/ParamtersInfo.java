package com.xie.scanapi.parse;

import com.xie.scanapi.Class2JsonUtils;
import com.xie.scanapi.ClassHelper;
import com.xie.scanapi.mappingResolver.ResolverSupport;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
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

    private Type[] actualTypeArguments;

    private Type rawType;

    private Map<String, Class> actualTypeMap = new HashMap<>();

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
    public StringBuffer parse() {
        if (isGener) {
            pType = GIC_OBJ;
            //有泛型参数
            //printGicAgrInfo();
            TypeVariable[] typeParameters = ((Class) rawType).getTypeParameters();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < actualTypeArguments.length; i++) {
                Type ar = actualTypeArguments[i];
                if (ar instanceof ParameterizedTypeImpl) {
                    ParameterizedTypeImpl tar = (ParameterizedTypeImpl) ar;
                    ParamtersInfo pInfo = new ParamtersInfo(resolverSupport, tar.getRawType(), tar.getActualTypeArguments());
                   return pInfo.parse();
                } else {
                    actualTypeMap.put(typeParameters[i].getName(), (Class) actualTypeArguments[i]);
                }
            }

            sb.append("``` \n");
            StringBuffer rs = Class2JsonUtils.generateApiJsonForm((Class) rawType, false, true, actualTypeMap);
            sb.append(rs);
            sb.append("\n``` ");
            return sb;
        } else {
            Class clz = (Class) rawType;
            if (ClassHelper.isBaseClass(clz)) {
                pType = BASE;
                StringBuffer sb = new StringBuffer();
                return sb;
            } else {
                pType = OBJ;
                StringBuffer sb = new StringBuffer();
                sb.append("``` \n");
                StringBuffer jsonDes = Class2JsonUtils.generateApiJsonForm((Class) rawType, false, true);
                sb.append(jsonDes);
                sb.append("\n``` ");
                return sb;
            }
        }

    }

    @Override
    public StringBuffer getParemsDescription() {
        return Class2JsonUtils.generateApiParamDescript((Class) rawType);
    }


    private void printGicAgrInfo() {
        System.out.println("打印泛型－实参");
        //泛型－实参
        //参数变量
        TypeVariable[] typeParameters = ((Class) rawType).getTypeParameters();
        for (int i = 0; i < actualTypeArguments.length; i++) {
            System.out.println("类泛型参数变量:" + typeParameters[i] + "  ==实参:" + actualTypeArguments[i]);
            actualTypeMap.put(typeParameters[i].getName(), (Class) actualTypeArguments[i]);
        }
        Field[] declaredFields = ((Class) rawType).getDeclaredFields();
        for (Field field : declaredFields) {

            String generType = field.getGenericType().toString();
            Class actuTypeClass = actualTypeMap.get(generType);
            if (actuTypeClass != null) {
                System.out.println("类泛参变量:field name:" + field.getName() + "  对应泛参名:" + generType + "  对应的实参:" + actuTypeClass.getName());
            } else {
                System.out.println("非字类泛参变量:field name:" + field.getName() + "  实参类型:" + generType);
            }
        }
    }
}
