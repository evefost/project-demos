package com.xie.scanapi.mappingResolver;

import com.xie.scanapi.parse.ApiInfo;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.lang.annotation.Annotation;

/**
 * Created by xieyang on 17/8/3.
 */
public class DeleteMappingResolver implements MappingResolver {

    @Override
    public boolean support(Class type) {
        return DeleteMapping.class.isAssignableFrom(type);
    }

    @Override
    public String[] getValue(Annotation annotation) {
        DeleteMapping mapping = (DeleteMapping) annotation;
        return mapping.value();
    }

    @Override
    public String[] getSupportMethods(Annotation annotation) {
        return  new String[]{"DELETE"};
    }

    @Override
    public String printApiDoc(ApiInfo apiInfo) {
        return null;
    }
}
