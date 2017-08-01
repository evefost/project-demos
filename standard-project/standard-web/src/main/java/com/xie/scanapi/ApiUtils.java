package com.xie.scanapi;

import com.xie.vo.Descript;
import com.xie.vo.User;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by xieyang on 17/7/31.
 */
public class ApiUtils {


    public static void main(String[] args) throws Exception {

//        System.out.println(isBase(int.class));
//        System.out.println(isBase(char.class));
//        System.out.println(isBase(long.class));
//        System.out.println(isBase(boolean.class));
//        System.out.println(isBase(Short.class));
//        System.out.println(isBase(Integer.class));
//        System.out.println(isBase(Long.class));
//        System.out.println(isBase(Float.class));
//        System.out.println(isBase(Double.class));
//        System.out.println(isBase(Boolean.class));
//        System.out.println(isBase(Character.class));
//        System.out.println(isBase(Byte.class));
//        System.out.println(isBase(String.class));
//        System.out.println(isBase(User.class));
//        System.out.println(new String[2].getClass().isArray());
        // System.out.println(isWrapClass(User.class));


        String sb = generateApiJsonForm(User.class, false,true);
        System.out.println(sb);
          StringBuffer stringBuffer = generateApiParamDescript(User.class);
         System.out.println(stringBuffer.toString());
        //getGic();
//        Field[] declaredFields = User.class.getDeclaredFields();
//        for(Field f:declaredFields){
//            f.setAccessible(true);
//            Class<?>[] parameterizedType = getParameterizedType(f);
//            if(parameterizedType != null && parameterizedType.length>0){
//                for(Class clz:parameterizedType){
//                    System.out.println(clz.getSimpleName());
//                }
//            }
//        }
    }

    public static Class<?>[] getParameterizedType(Field f) {
        // 获取f字段的通用类型
        Type fc = f.getGenericType(); // 关键的地方得到其Generic的类型
        // 如果不为空并且是泛型参数的类型
        if (fc != null && fc instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) fc;
            Type[] types = pt.getActualTypeArguments();
            if (types != null && types.length > 0) {
                Class<?>[] classes = new Class<?>[types.length];
                for (int i = 0; i < classes.length; i++) {
                    classes[i] = (Class<?>) types[i];
                }
                return classes;
            }
        }
        return null;
    }

    public static boolean isBase(Class clz) {
        try {
            if (clz.isPrimitive()) {
                return true;
            }
            if (clz.getSimpleName().equals("String")) {
                return true;
            }
            return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    public static String generateApiJsonForm(Class clz, boolean forJs, boolean withDescript) {
        StringBuffer sb = generateApiJsonForm(clz, 0, forJs, withDescript);
        sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",") + 1, "");
        return sb.toString();
    }

    private static StringBuffer generateApiJsonForm(Class clz, int loop, boolean forJs, boolean withDescript) {
        loop++;
        StringBuffer sb = new StringBuffer();
        sb.append("{\n");
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            Class<?> type = field.getType();
            for (int i = 0; i < loop; i++) {
                sb.append("\t");
            }
            if (forJs) {
                sb.append(name);
            } else {
                sb.append("\"").append(name).append("\"");
            }
            sb.append(':');
            if (isBase(type)) {
                if (forJs) {
                    sb.append("undefined,");
                } else {
                    sb.append(getDefaultValueByClassType(type) + ",");
                }

                if (withDescript) {
                    sb.append(" //");
                    Descript annotation = field.getAnnotation(Descript.class);
                    if (annotation != null) {
                        sb.append(annotation.message());
                    }
                    sb.append(" 类型:").append(type.getSimpleName().toLowerCase());
                    if (annotation != null) {
                        boolean require = annotation.required();
                        sb.append(" 必选(" + (require ? "是)" : "否)"));
                    } else {
                        sb.append(" 必选(未知)");
                    }
                }
                sb.append("\n");
            } else {
                loop++;
                StringBuffer json = generateApiJsonForm(type, loop, forJs, withDescript);
                sb.append(json);
            }
        }
        loop--;
        for (int i = 1; i < loop; i++) {
            sb.append("\t");
        }
        sb.append("}");
        sb.append("\n");
        return sb;

    }

    private static Object getDefaultValueByClassType(Class type) {
        String typeName = type.getSimpleName().toLowerCase();
        switch (typeName) {
            case "short":
            case "int":
            case "long":
                return 0;
            case "double":
            case "float":
                return 0.0;
            case "boolean":
                return false;
            case "byte":
                return "0";
            case "string":
                return "\"\"";
            case "char":
            case "character":
                return "0";
            default:
                break;

        }
        return null;
    }


    //    |参数名|必选|类型|说明|
//            |:----    |:---|:----- |-----   |
//            |searchKey |否  |string | 原密码    |
//            |roleId |否  |long | 角色id    |
//            |positionId |否  |long | 职位id    |
//            |departId |否 |long | 部门id    |
    public static StringBuffer generateApiParamDescript(Class clz) {

        StringBuffer sb = new StringBuffer();
        sb.append("|参数名|必选|类型|说明|\n").append("|:----    |:---|:----- |-----   |\n");
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> type = field.getType();
            String name = field.getName();
            Descript annotation = field.getAnnotation(Descript.class);
            if (annotation != null) {
                boolean require = annotation.required();
                sb.append("|").append(name).append("|" + (require ? "是" : "否"));
            } else {
                sb.append("|").append(name).append("| 未知");
            }
            sb.append("|").append(type.getSimpleName().toLowerCase());
            if (annotation != null) {
                sb.append("|" + annotation.message() + "|\n");
            } else {
                sb.append("|暂无参数说明|\n");
            }
        }
        return sb;

    }


}
