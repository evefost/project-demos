package com.xie.scanapi.parse;

import com.xie.java.common.response.ResponseDataVo;
import com.xie.scanapi.Class2JsonUtils;
import com.xie.scanapi.mappingResolver.MappingResolver;
import com.xie.scanapi.mappingResolver.ResolverSupport;
import com.xie.vo.Descript;
import com.xie.vo.SimpleUser;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 接口信息
 */
public class ApiInfo implements IInfo {

    private Method method;

    private Annotation annotation;


    /**
     * 接口描述
     */
    private String description;

    /**
     * 接口路径
     */
    private String path;

    /**
     * 接口支持的请求方式
     */
    private String[] supportMethods;

    /**
     * 接口参数信息
     */
    private ParamtersInfo[] paramtersInfos;

    /**
     * 接口返回信息
     */
    private ResponeInfo responeInfo;


    public ResponeInfo getResponeInfo() {
        return responeInfo;
    }

    public void setResponeInfo(ResponeInfo responeInfo) {
        this.responeInfo = responeInfo;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String[] getSupportMethods() {
        return supportMethods;
    }

    public void setSupportMethods(String[] supportMethods) {
        this.supportMethods = supportMethods;
    }

    public ParamtersInfo[] getParamtersInfos() {
        return paramtersInfos;
    }

    public void setParamtersInfos(ParamtersInfo[] paramtersInfos) {
        this.paramtersInfos = paramtersInfos;
    }

    public ResolverSupport getResolverSupport() {
        return resolverSupport;
    }

    public void setResolverSupport(ResolverSupport resolverSupport) {
        this.resolverSupport = resolverSupport;
    }

    private ResolverSupport resolverSupport;

    public ApiInfo(String path, Annotation annotation, Method method, ResolverSupport resolverSupport) {
        this.path = path;
        this.annotation = annotation;
        this.method = method;
        this.resolverSupport = resolverSupport;
        parse();

    }

    @Override
    public void parse() {
        System.out.println("\n");

        Descript des = method.getAnnotation(Descript.class);
        if (des != null) {
            description = des.value();
            System.out.println("接口路径描述:" + description);
        } else {
            description = "未知";
        }

        System.out.println("接口路径:" + path);


        MappingResolver resoler = resolverSupport.getResoler(annotation);
        supportMethods = resoler.getSupportMethods(annotation);
        System.out.println("***请求方式：** ");
        for(String m:supportMethods){
            System.out.print(" - "+m);
        }
        System.out.println("");

        //处理泛型参数
        Type[] gTypes = method.getGenericParameterTypes();
        if (gTypes != null && gTypes.length > 0) {
            paramtersInfos = new ParamtersInfo[gTypes.length];
            int i = 0;
            ParamtersInfo pInfo = null;
            for (Type type : gTypes) {

                if (type instanceof ParameterizedType) {
                    ParameterizedType paramType = (ParameterizedType) type;
                    Type[] actualTypeArguments = paramType.getActualTypeArguments();
                    Type rawType = paramType.getRawType();
                    pInfo = new ParamtersInfo(resolverSupport, rawType, actualTypeArguments);
                } else {
                    pInfo = new ParamtersInfo(resolverSupport, type);
                }
                paramtersInfos[i] = pInfo;
                i++;
            }
        }
        //参数名称列表

        //参数注解处理
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (parameterAnnotations != null && parameterAnnotations.length > 0) {
            for (Annotation[] annotations : parameterAnnotations) {
                for (Annotation annotation : annotations) {
                    //System.out.println(annotation);
                }
            }
        }

        Type genericReturnType = method.getGenericReturnType();
        System.out.println();
        System.out.println("返回参数类型 start =======");
        if(genericReturnType instanceof ParameterizedType){
            ParameterizedType paramType = (ParameterizedType) genericReturnType;
            Type[] actualTypeArguments = paramType.getActualTypeArguments();
            Type rawType = paramType.getRawType();
            ParamtersInfo pInfo = new ParamtersInfo(resolverSupport, rawType, actualTypeArguments);
        }else {
            ParamtersInfo pInfo = new ParamtersInfo(resolverSupport, genericReturnType);
        }
        System.out.println("返回参数类型 end =======");
        resoler.printApiDoc(this);
        //megerParams();
    }

    /**
     * 合并参数
     */
    private void megerParams(){
        int parseType = 0;
        if(paramtersInfos != null && paramtersInfos.length>0){
            for(ParamtersInfo pI:paramtersInfos){
                parseType = parseType|pI.pType;
            }
        }
        System.out.println("类型:"+parseType);
    }


}
