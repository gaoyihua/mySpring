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
    public boolean doBefore(Method method, Object[] args) {
        //TODO 置前
        return true;
    }

    @Override
    public Object doAfter(Method method, Object result) {
        //TODO 置后
        return null;
    }

    @Override
    public void doDealException(Method method, Throwable e) {
        //TODO 异常时
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
