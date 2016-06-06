package com.leech.viewinjectutils;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by CL on 2016/6/6.
 * <p/>
 * 支持UI的绑定和对View注册多个点击事件
 */
public class ViewUtils {
    public static void inject(final Activity activity) {
        //初始化控件然后赋值给对应的Field
        initView(activity);

        //给View设置点击点击事件
        initEvent(activity);


    }

    private static void initEvent(final Activity activity) {
        /**
         * 获取所有方法，包括私有方法
         * */
        Method[] methods = activity.getClass().getDeclaredMethods();

        /**
         * 遍历所有方法
         * */

        for (int i = 0; i < methods.length; i++) {
            final Method method = methods[i];
            //设置暴力破解
            method.setAccessible(true);
            ClickInject annotation = method.getAnnotation(ClickInject.class);
            if (annotation != null) {
                /**
                 * 获取注解中的数据,给多个View设置的点击事件
                 * */
                int[] ids = annotation.value();
                //遍历所有的ids
                for (int j = 0; j < ids.length; j++) {
                    int id = ids[j];
                    //获取控件
                    final View view = activity.findViewById(id);
                    //给控件设置点击监听事件
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                //通过反射调用用户指定的方法
                                method.invoke(activity, view);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }

    }

    private static void initView(Activity activity) {
        /**
         * 通过反射获取activity的所有Field,在获取Field的时候一定要用getDeclaredFields,从而保证可以获取到私有属性
         * 因为只有该方法可以获取到任何修饰符修饰的所有的字段，包括private
         * */
        Field[] fields = activity.getClass().getDeclaredFields();

        /**
         * 可能有多个Field,需要遍历
         * */
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            //暴力反射，保证可以访问到私有Field
            field.setAccessible(true);
            //获取Field上面的注解
            ViewInject annotation = field.getAnnotation(ViewInject.class);
            //一定要进行非空判断，因为不是所有的Field上都有我们需要的注解
            if (annotation != null) {
                //获取注解中的值
                int id = annotation.value();
                //获取控件
                View view = activity.findViewById(id);
                try {
                    //将控件设置给Field
                    /**
                     * 参数一：Field所属对象，这里是activity
                     * 参数二：要给Field设置的值
                     * */
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
