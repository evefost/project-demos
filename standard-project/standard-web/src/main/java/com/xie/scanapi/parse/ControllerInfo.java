package com.xie.scanapi.parse;

import com.xie.scanapi.mappingResolver.MappingResolver;
import com.xie.scanapi.mappingResolver.ResolverSupport;
import com.xie.scanapi.parse.ApiInfo;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/2.
 */
public class ControllerInfo implements IInfo {

    private Class clz;
    private Annotation controllerAnn;
    private String[] rootPaths;

    private String firstRoot = "";

    private Map<String, ApiInfo> apiInfoMap = new HashMap<>();


    private ResolverSupport resolverSupport;


    public ControllerInfo(Class clz, Annotation annotation, ResolverSupport resolverSupport) {
        this.clz = clz;
        this.controllerAnn = annotation;
        this.resolverSupport = resolverSupport;
        parse();
    }


    @Override
    public void parse() {
        RequestMapping clzReqMaping = (RequestMapping) clz.getAnnotation(RequestMapping.class);
        if (clzReqMaping != null) {
            rootPaths = clzReqMaping.value();
            if (rootPaths != null && rootPaths.length > 0) {
                firstRoot = rootPaths[0];
                if (!firstRoot.startsWith("/")) {
                    firstRoot = "/" + firstRoot;
                }
            }
        }
        //解释路径及method映射
        Method[] declaredMethods = this.clz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            for (Annotation annotation : declaredAnnotations) {
                MappingResolver resolver = resolverSupport.getResoler(annotation);
                if (resolver == null) {
                    continue;
                }
                String[] paths = resolver.getValue(annotation);
                if (paths != null && paths.length > 0) {
                    String path = null;
                    if (paths[0].startsWith("/")) {
                        path = firstRoot + paths[0];
                    } else {
                        path = firstRoot + "/" + paths[0];
                    }
                    ApiInfo apiInfo = new ApiInfo(path, annotation, method, resolverSupport);
                    apiInfoMap.put(path, apiInfo);
                }
            }
        }
    }
}
