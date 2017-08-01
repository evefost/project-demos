package com.xie.vo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface Descript {

    String value() default "";

    boolean required() default true;

    String message() default "暂无参数说明";


}
