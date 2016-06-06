package com.leech.viewinjectutils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by CL on 2016/6/6.
 * 用于添加到方法上，在方法上指定 Button 的 id，也可以指定多个 id，也就是支持绑定多个 Button
 * 当点击 Button 时通过反射调用方法
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ClickInject {
    /**
     * 参数类型为int[] 目的在于让一个方法可以同时绑定多个Button
     * */
    int[] value();
}
