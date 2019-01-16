package com.gary.myspring.core;

import com.gary.myspring.annotation.Component;
import com.gary.util.PackageScanner;

/**
 * describe:Bean工厂
 *
 * @author gary
 * @date 2019/01/15
 */
public class BeanFactory {

    public BeanFactory() {
    }

    public static void scanPackage(String packageName) {
        ProxyBeanFactory proxyBeanFactory = new ProxyBeanFactory();

        new PackageScanner() {
            @Override
            public void dealClass(Class<?> klass) {
                if (!klass.isAnnotationPresent(Component.class)) {
                    return ;
                }

                Component component = klass.getAnnotation(Component.class);
                String name = component.name().trim();

                try {
                    createBean(proxyBeanFactory, klass, null, name);

                    //TODO 处理带有@Bean注解的方法
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.packageScan(packageName);
    }

    private static boolean createBean(ProxyBeanFactory proxyBeanFactory, Class<?> klass, Object object, String name) throws Exception {
        //TODO 选择使用jdk代理或者cglib代理
        boolean flag = false;
        if (object != null) {
            flag = proxyBeanFactory.creteCGLibProxy(object);
        } else if (klass != null) {
            flag = proxyBeanFactory.creteCGLibProxy(klass);
        } else {
            return false;
        }
        if (name.length() > 0) {
            // TODO 带有name的Bean
        }
        return flag;
    }

    public static <T> T getBean(Class<?> klass) {
        ProxyBeanFactory proxyBeanFactory = new ProxyBeanFactory();
        MyProxy myProxy = proxyBeanFactory.getMyProxy(klass.getName());

        if (myProxy.isInjected()) {
            //TODO 注入
        }
        T proxy = myProxy.getProxy();
        return proxy;
    }
}
