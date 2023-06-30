package com.example.demo.model.entity;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(value= RetentionPolicy.RUNTIME)
@Documented
public @interface LogParam {
    boolean log() default true;
    String title() default "";
    //ParamEnum type() default ParamEnum.NONE;
    String dictType() default "";
    String dictValue() default "";
}