package com.dn.dubbo.demo.provider;

import com.dn.dubbo.demo.api.IHelloWorldService;

/**
 * Created by kkxmo on 2016/12/25.
 */
public class HelloWorldServiceImpl implements IHelloWorldService {

    public String sayHello() {
        return "我是中文：你好";
    }
}
