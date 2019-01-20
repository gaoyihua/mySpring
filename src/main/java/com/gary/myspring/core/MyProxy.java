package com.gary.myspring.core;

import java.lang.reflect.Method;

/**
 * describe:代理
 *
 * @author gary
 * @date 2019/01/16
 */
public class MyProxy implements IProxy {
    private Object object;
    private Object proxy;
    private boolean isInjected;

    @Override
    public boolean doBefore(Class<?> klass, Method method, Object[] args) {
        IntercepterFactory intercepterFactory = new IntercepterFactory();
        IntercepterTargetDefinition itd = new IntercepterTargetDefinition(klass, method);
        Chain chain = intercepterFactory.getBeforeIntercepterList(itd);
        return chain.doBefore(klass, method, args);
//        if (chain == null) {
//            return true;
//        }
//        boolean first = true;
//        do {
//            if (!first) {
//                if (chain.getNextChain() != null) {
//                    chain = chain.getNextChain();
//                }
//            }
//            IntercepterMethodDefinition methodDefination = chain.getIntercepterMethodDefinition();
//            Method intercepterMethod = methodDefination.getMethod();
//            Object intercepterObject = methodDefination.getObject();
//            boolean result = false;
//            try {
//                result = (Boolean) intercepterMethod.invoke(intercepterObject, args);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            if (!result) {
//                return false;
//            }
//            first = false;
//        } while (chain.hasNext());
    }

    @Override
    public Object doAfter(Class<?> klass, Method method, Object result) {
        IntercepterFactory intercepterFactory = new IntercepterFactory();
        IntercepterTargetDefinition itd = new IntercepterTargetDefinition(klass, method);
        Chain chain = intercepterFactory.getAfterIntercepterList(itd);
//        if (chain == null) {
//            return result;
//        }
//
//        boolean first = true;
//        do {
//            if (!first) {
//                if (chain.getNextChain() != null) {
//                    chain = chain.getNextChain();
//                }
//            }
//            IntercepterMethodDefinition methodDefination = chain.getIntercepterMethodDefinition();
//            Method intercepterMethod = methodDefination.getMethod();
//            Object intercepterObject = methodDefination.getObject();
//            try {
//                result = intercepterMethod.invoke(intercepterObject, result);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            first = false;
//        } while (chain.hasNext());

        return chain.doAfter(klass, method, result);
    }

    @Override
    public void doDealException(Class<?> klass, Method method, Throwable throwable) {
        IntercepterFactory intercepterFactory = new IntercepterFactory();
        IntercepterTargetDefinition itd = new IntercepterTargetDefinition(klass, method);
        Chain chain = intercepterFactory.getExceptionIntercepterList(itd);
//        if (chain == null) {
//            return ;
//        }
//
//        boolean first = true;
//        do {
//            if (!first) {
//                if (chain.getNextChain() != null) {
//                    chain = chain.getNextChain();
//                }
//            }
//            IntercepterMethodDefinition methodDefination = chain.getIntercepterMethodDefinition();
//            Method intercepterMethod = methodDefination.getMethod();
//            Object intercepterObject = methodDefination.getObject();
//            try {
//                intercepterMethod.invoke(intercepterObject, throwable);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            first = false;
//        } while (chain.hasNext());
        chain.doDealException(klass, method, throwable);
    }

    public boolean isInjected() {
        return isInjected;
    }

    public void setInjected(boolean injected) {
        isInjected = injected;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    <T> MyProxy setProxy(T proxy) {
        this.proxy = proxy;
        return this;
    }

    <T> T getProxy() {
        return (T)proxy;
    }
}
