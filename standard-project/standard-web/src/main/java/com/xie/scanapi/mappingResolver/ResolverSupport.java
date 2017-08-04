package com.xie.scanapi.mappingResolver;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/4.
 */
public class ResolverSupport {

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
}
