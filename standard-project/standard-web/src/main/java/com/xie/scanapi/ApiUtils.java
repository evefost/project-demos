package com.xie.scanapi;

import com.xie.vo.ParamDes;
import com.xie.vo.User;

import java.lang.reflect.Field;

/**
 * Created by xieyang on 17/7/31.
 */
public class ApiUtils {


    public static void main(String[] args) throws Exception {

//        System.out.println(isBase(int.class));
//        System.out.println(isBase(char.class));
//        System.out.println(isBase(long.class));
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
        //System.out.println( new String[2].getClass().isArray());
        //System.out.println(isWrapClass(User.class));

        StringBuffer sb = generateApiJsonForm(User.class, 1, true);
        sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",") + 1, "");
        System.out.println(sb.toString());
        StringBuffer stringBuffer = generateApiParamDescript(User.class);
        System.out.println(stringBuffer.toString());
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


    public static StringBuffer generateApiJsonForm(Class clz, int loop, boolean forJs) {

        StringBuffer sb = new StringBuffer();
        String className = clz.getSimpleName();
        for (int i = 1; i < loop; i++) {
            sb.append("\t");
        }
        if (!forJs) {
            sb.append("\"");
        }
        sb.append(className.substring(0, 1).toLowerCase())
                .append(className.substring(1, className.length()));
        if (!forJs) {
            sb.append("\"");
        }
        sb.append(":{\n");

        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            Class<?> type = field.getType();
            if (isBase(type)) {
                for (int i = 0; i < loop; i++) {
                    sb.append("\t");
                }
                if (!forJs) {
                    sb.append("\"");
                }
                sb.append(name);
                if (!forJs) {
                    sb.append("\"");
                }
                sb.append(':');
                if (!forJs) {
                    sb.append("\"");
                }
                sb.append("undefined");
                if (!forJs) {
                    sb.append("\"");
                }
                sb.append(",");
                if (forJs) {
                    sb.append("  //类型:").append(type.getSimpleName().toLowerCase()).append("\n");
                }

                sb.append("\n");
            } else {
                loop++;
                StringBuffer json = generateApiJsonForm(type, loop, forJs);
                sb.append(json);
            }

            //sb.replace( sb.lastIndexOf(","), sb.lastIndexOf(",")+1,"");
        }
        for (int i = 1; i < loop; i++) {
            sb.append("\t");
        }
        sb.append("}");
        sb.append("\n");

        return sb;

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
            ParamDes annotation = field.getAnnotation(ParamDes.class);
            if(annotation != null){
                boolean require = annotation.required();
                sb.append("|").append(name).append("|"+(require?"是": "否"));
            }else {
                sb.append("|").append(name).append("| 未知");
            }

            sb.append("|").append(type.getSimpleName().toLowerCase());
            if(annotation != null){
                sb.append("|"+annotation.descript()+"|\n");
            }else {
                sb.append("|暂无参数说明|\n");
            }
        }
        return sb;

    }


}
