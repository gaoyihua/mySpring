package com.gary.myspring.core;

import java.lang.reflect.Method;

public interface IChain {
    /**
     * 置前
     * @param klass
     * @param method
     * @param args
     * @return
     */
    public boolean doBefore(Class<?> klass, Method method, Object[] args);

    /**
     * 置后
     * @param klass
     * @param method
     * @param result
     * @return
     */
    public Object doAfter(Class<?> klass, Method method, Object result);

    /**
     * 异常拦截
     * @param klass
     * @param method
     * @param throwable
     */
    public void doDealException(Class<?> klass, Method method, Throwable throwable);
}
