package com.gary.myspring.core;

import com.gary.myspring.annotation.Autowired;
import com.gary.myspring.annotation.Bean;
import com.gary.myspring.annotation.Component;
import com.gary.util.PackageScanner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

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
        List<BeanMethodDefinition> multiParaMethodList = new ArrayList<>();

        new PackageScanner() {
            @Override
            public void dealClass(Class<?> klass) {
                if (!klass.isAnnotationPresent(Component.class)) {
                    return;
                }

                Component component = klass.getAnnotation(Component.class);
                String name = component.name().trim();

                try {
                    createBean(proxyBeanFactory, klass, null, name);

                    String className = klass.getName();
                    MyProxy myProxy = proxyBeanFactory.getMyProxy(className);
                    Object object = myProxy.getObject();

                    Method[] methods = klass.getDeclaredMethods();
                    for (Method method : methods) {
                        if (!method.isAnnotationPresent(Bean.class)) {
                            continue;
                        }
                        invokeBeanMethod(proxyBeanFactory, object, method, multiParaMethodList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.packageScan(packageName);

        for (BeanMethodDefinition beanMethodDefinition : multiParaMethodList) {
            Method method = beanMethodDefinition.getMethod();
            Class<?> returnType = beanMethodDefinition.getReturnType();
            Parameter[] parameters = beanMethodDefinition.getParameters();
            Object object = beanMethodDefinition.getObject();
            String name = beanMethodDefinition.getName();

            try {
                Object result = invokeMultiParaMethod(proxyBeanFactory, method, parameters, object);
                createBean(proxyBeanFactory, returnType, result, name);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private static Object invokeMultiParaMethod(ProxyBeanFactory proxyBeanFactory, Method method, Parameter[] parameters, Object object) throws Exception {
        int paraCount = parameters.length;

        Object[] args = new Object[paraCount];
        for (int index = 0; index < paraCount; index++) {
            Parameter parameter = parameters[index];
            Class<?> parameterType = parameter.getType();
            String className = parameterType.getName();
            MyProxy myProxy = proxyBeanFactory.getMyProxy(className);
            Object obj = myProxy.getObject();
            if (obj != null) {
                args[index] = obj;
            }
        }

        return method.invoke(object, args);
    }

    private static void invokeBeanMethod(ProxyBeanFactory proxyBeanFactory, Object object, Method method, List<BeanMethodDefinition> multiParaMethodList) throws Exception {
        Class<?> returnType = method.getReturnType();
        if (returnType.equals(void.class)) {
            return;
        }
        Bean bean = method.getAnnotation(Bean.class);
        String name = bean.name().trim();

        Parameter[] parameters = method.getParameters();
        if (parameters.length <= 0) {
            Object result = method.invoke(object);
            createBean(proxyBeanFactory, returnType, result, name);
        } else {
            multiParaMethodList.add(new BeanMethodDefinition(method, returnType, parameters, object, name));
        }
    }

    private static boolean createBean(ProxyBeanFactory proxyBeanFactory, Class<?> klass, Object object, String name) throws Exception {
        //TODO 选择使用jdk代理或者cglib代理
        //因为如果用jdk代理需要类实现接口，
        //而使用CGLib的话可以不实现接口，主要是对指定的类生成一个子类，覆盖其中方法
        // 覆盖其中的方法实现增强，但是因为采用的是继承，所以类或者方法不要声明成final
        //所以暂时都使用CGLib动态代理
        boolean flag = false;
        if (object != null) {
            flag = proxyBeanFactory.creteCGLibProxy(object);
        } else if (klass != null) {
            flag = proxyBeanFactory.creteCGLibProxy(klass);
        } else {
            return false;
        }
        if (name.length() > 0) {
            proxyBeanFactory.addBeanName(name, klass.getName());
        }
        System.out.println("创建bean：" + klass.getName());
        return flag;
    }

    private static void injectBean(ProxyBeanFactory proxyBeanFactory, Class<?> klass, Object object) {
        Field[] fields = klass.getDeclaredFields();
        MyProxy selfProxy = proxyBeanFactory.getMyProxy(klass.getName());
        selfProxy.setInjected(true);
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Autowired.class)) {
                continue;
            }

            Class<?> beanClass = field.getType();
            MyProxy fieldProxy = proxyBeanFactory.getMyProxy(beanClass.getName());
            Object obj = fieldProxy.getObject();

            if (!fieldProxy.isInjected()) {
                injectBean(proxyBeanFactory, beanClass, obj);
            }

            field.setAccessible(true);
            try {
                field.set(object, obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> T getBean(Class<?> klass) {
        ProxyBeanFactory proxyBeanFactory = new ProxyBeanFactory();
        MyProxy myProxy = proxyBeanFactory.getMyProxy(klass.getName());

        if (!myProxy.isInjected()) {
            injectBean(proxyBeanFactory, klass, myProxy.getObject());
        }
        return myProxy.getProxy();
    }
}
