package com.xie.scanapi.mappingResolver;

import org.springframework.web.bind.annotation.PostMapping;

import java.lang.annotation.Annotation;

/**
 * Created by xieyang on 17/8/3.
 */
public class PostMappingResolver implements MappingResolver{

    @Override
    public boolean support(Class type) {
        return PostMapping.class.isAssignableFrom(type);
    }

    @Override
    public String[]  getValue(Annotation annotation) {
        PostMapping mapping = (PostMapping) annotation;
        return mapping.value();
    }
}
