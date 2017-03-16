package com.dn.dubbo.demo;

import com.alibaba.dubbo.container.Main;

import java.io.IOException;

/**
 * 启动服务入口
 * Created by parker on 2016/12/25.
 */
public class Bootstrap {

    public static void main(String[] args) throws IOException {
        Main.main(args);

        System.in.read();
    }
}
