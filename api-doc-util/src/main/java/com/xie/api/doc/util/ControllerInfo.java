package com.xie.api.doc.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/2.
 */
public class ControllerInfo {

    private Class clz;
    private Annotation controllerAnn;
    private boolean isRest;
    private String[] rootPaths;
    private Map<String,Method> methods = new HashMap<>();

    public ControllerInfo(Class clz, Annotation annotation,boolean isRest){
        this.clz = clz;
        this.controllerAnn = annotation;
        this.isRest = isRest;
        pares();
    }

    private void pares(){
        if(isRest){
            RestController controller = (RestController) controllerAnn;
        }else {
            Controller controller = (Controller) controllerAnn;
        }
        RequestMapping  clzReqMaping = (RequestMapping) clz.getAnnotation(RequestMapping.class);
        if(clzReqMaping != null){
            rootPaths = clzReqMaping.value();
        }
    }

    private void paresMethods(){

    }

    public Class getClz() {
        return clz;
    }

    public void setClz(Class clz) {
        this.clz = clz;
    }

    public Annotation getControllerAnn() {
        return controllerAnn;
    }

    public void setControllerAnn(Annotation controllerAnn) {
        this.controllerAnn = controllerAnn;
    }

    public boolean isRest() {
        return isRest;
    }

    public void setRest(boolean rest) {
        isRest = rest;
    }

    public String[] getRootPaths() {
        return rootPaths;
    }

    public void setRootPaths(String[] rootPaths) {
        this.rootPaths = rootPaths;
    }

    public Map<String, Method> getMethods() {
        return methods;
    }

    public void setMethods(Map<String, Method> methods) {
        this.methods = methods;
    }
}
