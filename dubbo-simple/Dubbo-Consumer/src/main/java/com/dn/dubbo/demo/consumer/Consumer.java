package com.dn.dubbo.demo.consumer;

import com.alibaba.dubbo.rpc.RpcContext;
import com.dn.dubbo.demo.api.IHelloWorldService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by parker on 2016/12/25.
 */
public class Consumer {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        IHelloWorldService helloService = (IHelloWorldService) context.getBean("demoService");
        System.out.println("同步返回结果：" + helloService.sayHello());
        Future<String> str = RpcContext.getContext().getFuture();

        System.out.println("异步返回结果：" + str.get());

    }
}
