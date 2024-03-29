package com.xie.scanapi.mappingResolver;

import com.xie.scanapi.parse.ApiInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;

/**
 * Created by xieyang on 17/8/3.
 */
public class GetMappingResolver implements MappingResolver {

    @Override
    public boolean support(Class type) {
        return GetMapping.class.isAssignableFrom(type);
    }

    @Override
    public String[] getValue(Annotation annotation) {
        GetMapping mapping = (GetMapping) annotation;
        return mapping.value();
    }

    @Override
    public String[] getSupportMethods(Annotation annotation) {
        return   new String[]{"GET"};
    }

    @Override
    public String printApiDoc(ApiInfo apiInfo) {
        return null;
    }
}
