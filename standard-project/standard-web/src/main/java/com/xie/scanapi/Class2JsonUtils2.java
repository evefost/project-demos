package com.xie.scanapi;

import com.xie.java.common.annotation.Descript;
import com.xie.vo.Inner;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

import static com.xie.scanapi.ClassHelper.*;

/**
 * Created by xieyang on 17/7/31.
 */
public class Class2JsonUtils2 {


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

//        User s= new User();
//        Student student = new Student();
//        student.setStudentCources("aaaa");
//        student.setStudentName("xieang");
//        Student student2 = new Student();
//        student2.setStudentCources("aaaa222");
//        student2.setStudentName("xieang2222");
//        Set<Student> students = new HashSet<>();
//        students.add(student);
//        students.add(student2);
//        s.setStudents(students);

        //Descript annotation = User.class.getAnnotation(Descript.class);


        StringBuffer sb = generateApiJsonForm(Inner.class, true, true);
        System.out.println(sb);
//        StringBuffer stringBuffer = generateApiParamDescript(User.class);
//        System.out.println(stringBuffer.toString());

//        Field[] declaredFields = User.class.getDeclaredFields();
//        for (Field f : declaredFields) {
//            f.setAccessible(true);
//            Class<?>[] parameterizedType = getParameterizedType(f);
//            if (parameterizedType != null && parameterizedType.length > 0) {
//                for (Class clz : parameterizedType) {
//                    System.out.println(clz.getSimpleName());
//                }
//            }
//        }


    }


    public static StringBuffer generateApiJsonForm(Class clz, boolean forJs, boolean withDescript) {
        return generateApiJsonForm(clz, forJs, withDescript, null);
    }

    public static StringBuffer generateApiJsonForm(Class clz, boolean forJs, boolean withDescript, Map<String, Class> actualTypeMap) {
        String classDes = "";
        Annotation annotation = clz.getAnnotation(Descript.class);
        if (annotation != null) {
            Descript descript = (Descript) annotation;
            classDes = appendDescript(withDescript, descript, clz, "").toString();
        }
        StringBuffer sb = generateApiJsonForm(clz, 0, forJs, withDescript, classDes, actualTypeMap);
        return sb;
    }

    private static StringBuffer generateApiJsonForm(Class clz, int loop, boolean forJs, boolean withDescript, String objDes, Map<String, Class> actualTypeMap) {

        loop++;
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < loop; i++) {
            sb.append("\t");
        }
        sb.append("{").append(objDes).append("\n");
        Class superclass = clz.getSuperclass();
        Field[] supperFields = new Field[]{};
        if (superclass != null) {
            supperFields = superclass.getDeclaredFields();
        }

        Field[] childFields = clz.getDeclaredFields();

        Field[] fields = new Field[supperFields.length + childFields.length];
        for (int n = 0; n < fields.length; n++) {
            if (n < supperFields.length) {
                fields[n] = supperFields[n];
            } else {
                fields[n] = childFields[n - supperFields.length];
            }

        }
        int c = 0;
        for (Field field : fields) {
            c++;
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
            if (isBaseClass(type)) {
                if (forJs) {
                    sb.append("undefined");
                } else {
                    sb.append(getDefaultValueByClassType(type));
                }
                if (c < fields.length) {
                    sb.append(",");
                }
                sb.append(appendDescript(withDescript, field.getAnnotation(Descript.class), type, ""));
                sb.append("\n");
            } else {
                if (isList(type)) {
                    Class pClass = null;
                    if (actualTypeMap != null && actualTypeMap.size() > 0) {
                        String gegerType = ((ParameterizedTypeImpl) field.getGenericType()).getActualTypeArguments()[0].toString();
                        pClass = actualTypeMap.get(gegerType);
                    }
                    if (pClass == null) {
                        pClass = getParameterizedClass(field);
                    }
                    sb.append("[");
                    if (pClass == null) {
                        sb.append(" //数组没有泛型参数,没法解释到实际参数型 ]");
                    } else {
                        if (isBaseClass(pClass)) {
                            sb.append(getDefaultValueByClassType(pClass)).append(',').append(getDefaultValueByClassType(pClass));
                            sb.append("]");
                            String paramterClass = pClass.getSimpleName().toLowerCase();
                            sb.append(appendDescript(withDescript, field.getAnnotation(Descript.class), type, paramterClass));
                        } else {
                            String paramterClass = pClass.getSimpleName().toLowerCase();
                            sb.append(appendDescript(withDescript, field.getAnnotation(Descript.class), type, paramterClass));
                            sb.append("\n");
                            StringBuffer json = generateApiJsonForm(pClass, loop, forJs, withDescript, "", null);
                            sb.append(json);
                            sb.append("]");
                        }
                    }
                } else {
                    String tObjdes = appendDescript(withDescript, field.getAnnotation(Descript.class), type, "").toString();
                    StringBuffer json = null;
                    //获取泛参名称
                    if (actualTypeMap != null && actualTypeMap.size() > 0) {
                        String generType = field.getGenericType().toString();
                        //获取实参
                        Class aClass = actualTypeMap.get(generType);
                        if (aClass != null) {
                            tObjdes = appendDescript(withDescript, field.getAnnotation(Descript.class), aClass, "").toString();
                            json = generateApiJsonForm(aClass, loop, forJs, withDescript, tObjdes, actualTypeMap);
                        } else {
                            json = generateApiJsonForm(type, loop, forJs, withDescript, tObjdes, actualTypeMap);
                        }
                    } else {
                        json = generateApiJsonForm(type, loop, forJs, withDescript, tObjdes, null);

                    }
                    sb.append(json);

                }
                if (c < fields.length) {
                    sb.append(",");
                }
                sb.append("\n");

            }
        }
        for (int i = 1; i < loop; i++) {
            sb.append("\t");
        }
        sb.append("}");
        loop--;
        return sb;

    }

    private static StringBuffer appendDescript(boolean withDescript, Descript annotation, Class type, String paramterClass) {
        StringBuffer sb = new StringBuffer();
        if (!withDescript) {
            return sb;
        }
        sb.append(" //");
        if (annotation != null) {
            String message = isEmpty(annotation.value()) ? annotation.message() : annotation.value();
            sb.append(message);
        }
        sb.append(" 类型:").append(type.getSimpleName().toLowerCase());
        if (!isEmpty(paramterClass)) {
            sb.append("<" + paramterClass + ">");
        }
        if (annotation != null) {
            boolean require = annotation.required();
            sb.append(" 必选(" + (require ? "是)" : "否)"));
        } else {
            sb.append(" 必选(未知)");
        }
        return sb;

    }


    private static boolean isEmpty(String str) {
        if (str == null || str.trim().length() == 0 || "".equals(str)) {
            return true;
        }
        return false;
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
