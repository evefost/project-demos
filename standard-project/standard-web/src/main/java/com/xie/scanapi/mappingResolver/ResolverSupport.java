package com.xie.scanapi.mappingResolver;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/4.
 */
public class ResolverSupport {

    public static final LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();


    private Map<String, MappingResolver> mappingResolverMap;

    public ResolverSupport(Map<String, MappingResolver> mappingResolverMap) {
        this.mappingResolverMap = mappingResolverMap;
    }

    public MappingResolver getResoler(Annotation annotation) {
        for (Map.Entry<String, MappingResolver> entry : mappingResolverMap.entrySet()) {
            MappingResolver resolver = entry.getValue();
            if (resolver.support(annotation.getClass())) {
                return resolver;
            }
        }
        return null;
    }

    /**
     * 获取方法参数名称
     * @param method
     * @return
     */
    public static String[] getParameterNames(Method method) {
        return parameterNameDiscoverer.getParameterNames(method);
    }
}
