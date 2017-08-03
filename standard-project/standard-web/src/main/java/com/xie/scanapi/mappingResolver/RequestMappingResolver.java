package com.xie.scanapi.mappingResolver;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * Created by xieyang on 17/8/3.
 */
public class RequestMappingResolver implements MappingResolver{

    @Override
    public boolean support(Class type) {
        return RequestMapping.class.isAssignableFrom(type);
    }

    @Override
    public String[]  getValue(Annotation annotation) {
        RequestMapping mapping = (RequestMapping) annotation;
        return mapping.value();
    }
}
