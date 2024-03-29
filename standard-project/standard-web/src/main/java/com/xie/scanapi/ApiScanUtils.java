package com.xie.scanapi;

import com.xie.java.common.annotation.Descript;
import com.xie.java.common.annotation.Descript2;
import com.xie.scanapi.constant.DescriptMethodEnum;
import com.xie.scanapi.mappingResolver.MappingResolver;
import com.xie.scanapi.mappingResolver.ResolverSupport;
import com.xie.scanapi.paramter.descript.DescriptSupport;
import com.xie.scanapi.paramter.descript.IDescriptSupport;
import com.xie.scanapi.parse.ControllerInfo;
import com.xie.vo.Params1;
import com.xie.vo.SimpleUser;
import com.xie.web.controller.AjaxController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xieyang on 17/7/31.
 */
public class ApiScanUtils {

    public static boolean forJs = true;
    public static boolean withDes = true;

    public   static Class annotationClz;

    private static Map<String, MappingResolver> mappingResolverMap = new HashMap<>();

    public static Map<String, IDescriptSupport> paramterSupports = new HashMap<>();

    public static void main(String[] args) throws Exception {
//
        String name = DescriptMethodEnum.VALUE.name();

        StringBuffer stringBuffer = Class2JsonUtils.generateApiJsonForm(Params1.class,Descript.class,true,true);
        System.out.println(stringBuffer);


//        Annotation annotation = SimpleUser.class.getAnnotation(annotationClz);
//        Object value = getAnnotationMethodReturn(annotation, "required");
//
//        System.out.println(value);

        // scanPagkageArr("com.xie", null);
         //scanPagkage("com.xie", AjaxController.class,Descript.class);
        //scanPagkage("com.xie");

    }


    public static void scanPagkage(String packageName, Class controllerClzs,Class annoClz) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class[] classes = new Class[]{controllerClzs};
        scanPagkageArr(packageName, classes,annoClz);

    }


    public static void scanPagkageArr(String packageName, Class<?>[] controllerClzs,Class annoClz) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        annotationClz = annoClz;
        List<ControllerInfo> controllerInfos = new ArrayList<>();
        List<Class<?>> classes = ClassScanUtil.getClasses(packageName);
        loadMappingResolers(classes);
        loadParamterSupports(classes);
        ResolverSupport support = new ResolverSupport(mappingResolverMap,annoClz);
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

    private static void loadParamterSupports(List<Class<?>> classes) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        for (Class clz : classes) {
            Class[] interfaces = clz.getInterfaces();
            if (interfaces != null && interfaces.length > 0) {
                for (Class inte : interfaces) {
                    if (inte.isAssignableFrom(IDescriptSupport.class)) {
                        Constructor constructor = clz.getConstructor(Class.class);
                        Object instance = constructor.newInstance(annotationClz);
                        paramterSupports.put(clz.getSimpleName(), (IDescriptSupport) instance);
                    }
                }
            }
        }

    }


}
