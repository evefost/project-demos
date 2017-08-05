package com.xie.scanapi;

import com.xie.scanapi.mappingResolver.MappingResolver;
import com.xie.scanapi.mappingResolver.ResolverSupport;
import com.xie.scanapi.parse.ControllerInfo;
import com.xie.web.controller.AjaxController;
import com.xie.web.controller.GicController;
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

    private static Map<String, MappingResolver> mappingResolverMap = new HashMap<>();

    public static void main(String[] args) throws Exception {


        scanPagkage("com.xie", new Class[]{GicController.class});

    }

    public static void scanPagkage(String packageName) throws InstantiationException, IllegalAccessException {
        scanPagkage(packageName, null);

    }

    public static void scanPagkage(String packageName, Class<?>[] controllerClzs) throws InstantiationException, IllegalAccessException {
        List<ControllerInfo> controllerInfos = new ArrayList<>();
        List<Class<?>> classes = ClassScanUtil.getClasses("com.xie");
        instances(classes);
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


    private static void instances(List<Class<?>> classes) throws IllegalAccessException, InstantiationException {
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


}
