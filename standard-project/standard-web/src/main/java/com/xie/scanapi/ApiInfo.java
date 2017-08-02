package com.xie.scanapi;

import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 接口信息
 */
public class ApiInfo {

    private Method method;

    private Annotation annotation;


    /**
     * 接口路径
     */
    private String path;

    /**
     * 接口支持的请求方式
     */
    private String[] supportMethods;

    /**
     * 接口参数信息
     */
    private ParamtersInfo paramtersInfo;

    /**
     * 接口返回信息
     */
    private ResponeInfo responeInfo;


    public ApiInfo(String path, Annotation annotation, Method method) {
        this.path = path;
        this.annotation = annotation;
        this.method = method;
        parse();

    }

    private void parse() {
        parseSupportMethods();


    }


    private void parseSupportMethods() {

        if (annotation instanceof RequestMapping) {
            RequestMapping mapping = (RequestMapping) annotation;
            RequestMethod[] methods = mapping.method();
            if (methods == null || methods.length == 0) {
                methods = RequestMethod.values();
            }
            supportMethods = new String[methods.length];
            for (int i = 0; i < methods.length; i++) {
                supportMethods[i] = methods[i].toString();
            }
        } else if (annotation instanceof GetMapping) {
            supportMethods = new String[]{"GET"};
        } else if (annotation instanceof PostMapping) {
            supportMethods = new String[]{"POST"};
        } else if (annotation instanceof PutMapping) {
            supportMethods = new String[]{"PUT"};
        } else if (annotation instanceof DeleteMapping) {
            supportMethods = new String[]{"DELETE"};
        } else {
            //todo not suport

        }
    }


}