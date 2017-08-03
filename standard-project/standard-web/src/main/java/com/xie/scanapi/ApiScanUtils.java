package com.xie.scanapi;

import com.xie.scanapi.mappingResolver.MappingResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xieyang on 17/7/31.
 */
public class ApiScanUtils {

    private static Map<String, MappingResolver> mappingResolverMap =new HashMap<>();

    public static void main(String[] args) throws Exception {


        scanPagkage();

    }

    public static void scanPagkage() throws InstantiationException, IllegalAccessException {
        Package aPackage = ApiScanUtils.class.getPackage();
        String name = aPackage.getName();
        // String shortPack = name.substring(0,name.indexOf(","));
        List<ControllerInfo> controllerInfos = new ArrayList<>();
        List<Class<?>> classes = ClassScanUtil.getClasses("com.xie");
        instances(classes);
        for (Class clz : classes) {
            Annotation annotation = clz.getAnnotation(RestController.class);
            if (annotation != null) {
                System.out.println("controller类[" + clz.getName());
                controllerInfos.add(new ControllerInfo(clz, annotation,mappingResolverMap));
            } else {
                annotation = clz.getAnnotation(Controller.class);
                if (annotation != null) {
                    controllerInfos.add(new ControllerInfo(clz, annotation, mappingResolverMap));
                }
            }
        }
    }


    private static void instances(List<Class<?>> classes) throws IllegalAccessException, InstantiationException {
        for (Class clz : classes) {
            Class[] interfaces = clz.getInterfaces();
            if(interfaces != null && interfaces.length>0){
                for(Class inte:interfaces){
                    if(inte.isAssignableFrom(MappingResolver.class)){
                        mappingResolverMap.put(clz.getSimpleName(),(MappingResolver)clz.newInstance());
                    }
                }
            }
        }

    }




}
