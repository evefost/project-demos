package com.xie.scanapi.paramter.descript;

import com.xie.scanapi.parse.ParamtersInfo;

/**
 * Created by xieyang on 17/8/20.
 */
public abstract class DescriptSupport {

    protected Class descriptAnno;

    public DescriptSupport(Class descriptAnno) {
        this.descriptAnno = descriptAnno;
    }


}
