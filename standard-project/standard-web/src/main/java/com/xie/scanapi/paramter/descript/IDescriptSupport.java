package com.xie.scanapi.paramter.descript;

import com.xie.scanapi.parse.ParamtersInfo;

/**
 * Created by xieyang on 17/8/20.
 */
public interface IDescriptSupport {


     boolean support(ParamtersInfo pInfo);

      StringBuffer getDescript(ParamtersInfo pInfo, String[] parameterNames, int index);
}
