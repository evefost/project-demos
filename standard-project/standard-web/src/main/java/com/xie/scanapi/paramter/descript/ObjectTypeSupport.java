package com.xie.scanapi.paramter.descript;

import com.xie.scanapi.parse.ParamtersInfo;

import static com.xie.scanapi.Class2JsonUtils.parseFieldsDescript;

/**
 * Created by xieyang on 17/8/20.
 */
public class ObjectTypeSupport extends DescriptSupport implements IDescriptSupport {


    public ObjectTypeSupport(Class descriptAnno) {
        super(descriptAnno);
    }

    @Override
    public StringBuffer getDescript(ParamtersInfo pInfo, String[] parameterNames, int index) {
        StringBuffer sb = new StringBuffer();
        Class clz = (Class) pInfo.getParamsType();
        parseFieldsDescript(clz,descriptAnno,sb,pInfo.hashAnnotationRequetBody());
        return sb;
    }

    @Override
    public boolean support(ParamtersInfo paramtersInfo) {
        return paramtersInfo.pType==ParamtersInfo.OBJ;
    }


}
