package com.leech.viewinjectutils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by CL on 2016/6/6.
 * ViewInject类添加在Field上
 * @Retention用来声明该注解生效的生命周期，有三个枚举值可供选择：
 * 1. RetentionPolicy.SOURCE 注释只保留在源码上面，编译成 class 的时候自动被编译器抹除
 * 2. RetentionPolicy.CLASS 注释只保留到字节码上面，VM 加载字节码时自动抹除
 * 3. RetentionPolicy.RUNTIME 注释永久保留，可以被 VM 加载时加载到内存中
 * 我们需要在VM运行时对Field进行反射操作，因此RetentionPolicy必须为RUNTIME
 *
 *  @Target用于指定该注解可以声明在哪些成员上面，常见的值有 FIELD 和 Method
 *  由于我们的当前注解类是想声明在 Filed 上面,因此这里设置为 ElementType.FIELD。
 *  注意：如果@Target 值不设置，则默认可以添加到任何元素上，不推荐这么写。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ViewInject {
    /**
     * 定义该注解的参数名和参数类型，value是所有注解类的默认参数名
     * 在这里value是指被注解view的id
     * */
    int value();
}
