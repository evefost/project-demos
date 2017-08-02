package com.xie.scanapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/8/2.
 */
public class ControllerInfo {

    private Class clz;
    private Annotation controllerAnn;
    private boolean isRest;
    private String[] rootPaths;

    private String firstRoot="";

    private Map<String,ApiInfo> apiInfoMap = new HashMap<>();


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
            if(rootPaths != null && rootPaths.length>0){
                firstRoot = rootPaths[0];
                if(!firstRoot.startsWith("/")){
                   firstRoot="/"+firstRoot;
                }
            }
        }
        paresMethods();
    }

    private void paresMethods(){

        Method[] declaredMethods = this.clz.getDeclaredMethods();
        for(Method method:declaredMethods){
            Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            for (Annotation annotation:declaredAnnotations){
                String[] paths = null;
                if(annotation instanceof RequestMapping){
                    RequestMapping mapping = (RequestMapping) annotation;
                    paths = mapping.value();
                }else  if(annotation instanceof GetMapping){
                    GetMapping mapping = (GetMapping) annotation;
                    paths = mapping.value();
                }else  if(annotation instanceof PostMapping){
                    PostMapping mapping = (PostMapping) annotation;
                }else  if(annotation instanceof PutMapping){
                    PutMapping mapping = (PutMapping) annotation;
                    paths = mapping.value();
                }else  if(annotation instanceof DeleteMapping){
                    DeleteMapping mapping = (DeleteMapping) annotation;
                    paths = mapping.value();
                }else  {
                    //todo not suport

                }
                if(paths != null && paths.length>0){
                    String path =null;
                    if(paths[0].startsWith("/")){
                       path = firstRoot+paths[0];
                    }else {
                        path =firstRoot+"/"+paths[0];
                    }
                    ApiInfo apiInfo = new ApiInfo(path,annotation,method);
                    apiInfoMap.put(path,apiInfo);
                }
            }
        }
    }



}
