package com.xie.scanapi;

import com.xie.scanapi.mappingResolver.MappingResolver;
import com.xie.scanapi.mappingResolver.ResolverSupport;
import com.xie.scanapi.paramter.descript.DescriptSupport;
import com.xie.scanapi.parse.ControllerInfo;
import com.xie.web.controller.AjaxController;
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

    public static boolean forJs = false;
    public  static boolean withDes = true;

    private static Map<String, MappingResolver> mappingResolverMap = new HashMap<>();

    public static Map<String, DescriptSupport> paramterSupports = new HashMap<>();

    public static void main(String[] args) throws Exception {


       // scanPagkageArr("com.xie", null);
        scanPagkage("com.xie", AjaxController.class);
        //scanPagkage("com.xie");

    }


    public static void scanPagkage(String packageName, Class controllerClzs) throws InstantiationException, IllegalAccessException {
        Class[] classes = new Class[]{controllerClzs};
        scanPagkageArr(packageName, classes);

    }


    public static void scanPagkageArr(String packageName, Class<?>[] controllerClzs) throws InstantiationException, IllegalAccessException {
        List<ControllerInfo> controllerInfos = new ArrayList<>();
        List<Class<?>> classes = ClassScanUtil.getClasses(packageName);
        loadMappingResolers(classes);
        loadParamterSupports(classes);
        ResolverSupport support = new ResolverSupport(mappingResolverMap);
        if (controllerClzs != null && controllerClzs.length > 0) {
            for (Class clz : controllerClzs) {
                findControllers(controllerInfos, support, clz);
            }
        } else {
            for (Class clz : classes) {
                findControllers(controllerInfos, support, clz);
            }
        }

    }

    private static void findControllers(List<ControllerInfo> controllerInfos, ResolverSupport support, Class clz) {
        Annotation annotation = clz.getAnnotation(RestController.class);
        if (annotation != null) {
            controllerInfos.add(new ControllerInfo(clz, annotation, support));
        } else {
            annotation = clz.getAnnotation(Controller.class);
            if (annotation != null) {
                controllerInfos.add(new ControllerInfo(clz, annotation, support));
            }
        }
    }


    private static void loadMappingResolers(List<Class<?>> classes) throws IllegalAccessException, InstantiationException {
        for (Class clz : classes) {
            Class[] interfaces = clz.getInterfaces();
            if (interfaces != null && interfaces.length > 0) {
                for (Class inte : interfaces) {
                    if (inte.isAssignableFrom(MappingResolver.class)) {
                        mappingResolverMap.put(clz.getSimpleName(), (MappingResolver) clz.newInstance());
                    }
                }
            }
        }

    }

    private static void loadParamterSupports(List<Class<?>> classes) throws IllegalAccessException, InstantiationException {
        for (Class clz : classes) {
            Class[] interfaces = clz.getInterfaces();
            if (interfaces != null && interfaces.length > 0) {
                for (Class inte : interfaces) {
                    if (inte.isAssignableFrom(DescriptSupport.class)) {
                        paramterSupports.put(clz.getSimpleName(), (DescriptSupport) clz.newInstance());
                    }
                }
            }
        }

    }


}
