package com.xie.scanapi.paramter.descript;

import com.xie.java.common.annotation.Descript;
import com.xie.scanapi.Class2JsonUtils;
import com.xie.scanapi.parse.ParamtersInfo;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by xieyang on 17/8/20.
 */
public class BaseTypeSupport implements DescriptSupport{

    @Override
    public boolean support(ParamtersInfo paramtersInfo) {
        return paramtersInfo.pType==ParamtersInfo.BASE;
    }

    @Override
    public StringBuffer getDescript(ParamtersInfo pInfo, String[] parameterNames,int index) {
        StringBuffer sb = new StringBuffer();
        String name = parameterNames[index];
        Class<?> clz = (Class<?>) pInfo.getParamsType();
        Descript annotation = clz.getAnnotation(Descript.class);
        if (annotation != null) {
            boolean require = annotation.required();
            sb.append("|").append(name).append("|" + (require ? "是" : "否"));
        } else {
            sb.append("|").append(name).append("| 未知");
        }
        sb.append("|").append(clz.getSimpleName().toLowerCase());
        if (annotation != null) {
            sb.append("|" + annotation.message() + "|\n");
        } else {
            sb.append("|暂无参数说明|\n");
        }
        return sb;
    }
}
