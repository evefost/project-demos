package com.xie.scanapi.parse;

import com.xie.scanapi.mappingResolver.MappingResolver;
import com.xie.scanapi.mappingResolver.ResolverSupport;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.synth.SynthConstants;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * 接口信息
 */
public class ApiInfo implements IInfo{

    private Method method;

    private Annotation annotation;


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

    private ResolverSupport resolverSupport;

    public ApiInfo(String path, Annotation annotation, Method method,ResolverSupport resolverSupport) {
        this.path = path;
        this.annotation = annotation;
        this.method = method;
        this.resolverSupport = resolverSupport;
        parse();

    }

    @Override
    public void parse() {
        parseSupportMethods();
        parseParamters();


    }


    private void parseSupportMethods() {
        MappingResolver resoler = resolverSupport.getResoler(annotation);
        supportMethods =  resoler.getSupportMethods(annotation);
    }

    private void parseParamters() {

        //Class<?>[] parameterTypes = method.getParameterTypes();
        Type[] gTypes = method.getGenericParameterTypes();
        if (gTypes != null && gTypes.length > 0) {
            paramtersInfos = new ParamtersInfo[gTypes.length];
            int i = 0;
            ParamtersInfo pInfo = null;
            for (Type type : gTypes) {
                pInfo = new ParamtersInfo();
                if (type instanceof ParameterizedType) {
                    ParameterizedType paramType = (ParameterizedType) type;
                    Type[] actualTypeArguments = paramType.getActualTypeArguments();
                    Type rawType = paramType.getRawType();
                    pInfo.setActualTypeArguments(actualTypeArguments);
                    pInfo.setRawType(rawType);
                    pInfo.setGener(true);
                } else {
                    pInfo.setRawType(type);
                    pInfo.setGener(false);
                }
                paramtersInfos[i] = pInfo;
                i++;
            }
        }
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if(parameterAnnotations != null && parameterAnnotations.length>0){
            for(Annotation[] annotations:parameterAnnotations){
                for(Annotation annotation:annotations){
                    System.out.println(annotation);
                }
            }
        }
    }


}
