package com.xie.scanapi;

import com.xie.scanapi.mappingResolver.MappingResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/2.
 */
public class ControllerInfo {

    private Class clz;
    private Annotation controllerAnn;
    private boolean isRest;
    private String[] rootPaths;

    private String firstRoot = "";
    private Map<String, ApiInfo> apiInfoMap = new HashMap<>();


    private  Map<String, MappingResolver> mappingResolverMap;


    public ControllerInfo(Class clz, Annotation annotation, Map<String, MappingResolver> resolverMap) {
        this.clz = clz;
        this.controllerAnn = annotation;
        this.mappingResolverMap = resolverMap;
        pares();
    }

    private void pares() {
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
        paresMethods();
    }

    private void paresMethods() {
        Method[] declaredMethods = this.clz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            for (Annotation annotation : declaredAnnotations) {
                MappingResolver resolver = getResoler(annotation);
                if(resolver == null){
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
                    ApiInfo apiInfo = new ApiInfo(path, annotation, method);
                    apiInfoMap.put(path, apiInfo);
                }
            }
        }
    }

    private MappingResolver getResoler(Annotation annotation) {
        for (Map.Entry<String, MappingResolver> entry : mappingResolverMap.entrySet()) {
            MappingResolver resolver = entry.getValue();
            if (resolver.support(annotation.getClass())) {
                return resolver;
            }
        }
        return null;
    }


}
