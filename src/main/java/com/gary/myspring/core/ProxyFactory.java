package com.gary.myspring.core;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * describe:代理工厂：产生cglib代理或者jdk代理
 *
 * @author gary
 * @date 2019/01/16
 */
public class ProxyFactory {
    private MyProxy myProxy;

    public ProxyFactory() {
    }

    public MyProxy getMyProxy() {
        return myProxy;
    }

    <T> T getCGLIBProxy(Object object, Class<?> klass) {
        T proxy = cglibProxy(object, klass);
        myProxy = new MyProxy();
        myProxy.setProxy(proxy);
        myProxy.setObject(object);
        return proxy;
    }

    <T> T getJDKProxy(Object object, Class<?> klass) {
        T proxy = jdkProxy(object, klass);
        myProxy = new MyProxy();
        myProxy.setProxy(proxy);
        myProxy.setObject(object);
        return proxy;
    }

    @SuppressWarnings("unchecked")
    <T> T cglibProxy(Object object, Class<?> klass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(klass);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                return doInvoke(klass, object, method, objects);
            }
        });
        return (T) enhancer.create();
    }

    Object doInvoke(Class<?> klass, Object object, Method method, Object[] args) {
        Object result = null;
        //前置
        if (myProxy.doBefore(klass, method, args) == false) {
            return null;
        }
        try {
            result = method.invoke(object, args);
            //后置
            myProxy.doAfter(klass, method, result);
        } catch (Throwable e) {
            //异常拦
            myProxy.doDealException(klass, method, e);
            e.printStackTrace();
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    <T> T jdkProxy(Object object, Class<?> klass) {
        return (T) Proxy.newProxyInstance(
                klass.getClassLoader(),
                klass.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return doInvoke(klass, object, method, args);
                    }
                }

        );
    }
}
