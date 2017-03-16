package com.dn.dubbo.demo.group;

import com.dn.dubbo.demo.api.IHelloWorldService;

/**
 * Created by parker on 2016/12/25.
 */
public class ChinaHelloWorldImpl implements IHelloWorldService {

    public String sayHello() {
        return "我是中文的: 你好";
    }
}
