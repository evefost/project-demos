package com.dn.dubbo.demo.group;

import com.dn.dubbo.demo.api.IHelloWorldService;

/**
 * Created by kkxmo on 2016/12/25.
 */
public class EnglishHelloWorldService implements IHelloWorldService {

    public String sayHello() {
        return "我是英文的：hello";
    }
}
