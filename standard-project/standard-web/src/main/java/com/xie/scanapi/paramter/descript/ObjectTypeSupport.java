package com.xie.scanapi.paramter.descript;

import com.xie.java.common.annotation.Descript;
import com.xie.scanapi.Class2JsonUtils;
import com.xie.scanapi.parse.ParamtersInfo;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;

import static com.xie.scanapi.Class2JsonUtils.parseFieldDescript;

/**
 * Created by xieyang on 17/8/20.
 */
public class ObjectTypeSupport implements DescriptSupport{

    @Override
    public boolean support(ParamtersInfo paramtersInfo) {
        return paramtersInfo.pType==ParamtersInfo.OBJ;
    }

    @Override
    public StringBuffer getDescript(ParamtersInfo pInfo, String[] parameterNames,int index) {
        StringBuffer sb = new StringBuffer();
        Class clz = (Class) pInfo.getParamsType();
        parseFieldDescript(clz,sb,pInfo.hashAnnotationRequetBody());
        return sb;
    }
}
