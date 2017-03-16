package com.dn.dubbo.demo.version;

import com.dn.dubbo.demo.api.IHelloWorldService;

/**
 * Created by parker on 2016/12/25.
 */
public class HelloWorldImplVersion1 implements IHelloWorldService {

    public String sayHello() {
        return "我的版本号：version1.0";
    }
}
