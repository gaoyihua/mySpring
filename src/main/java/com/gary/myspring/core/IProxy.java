package com.gary.myspring.core;

import java.lang.reflect.Method;

/**
 * describe:代理接口
 *
 * @author gary
 * @date 2019/01/16
 */
public interface IProxy {
    /**
     * 方法执行前执行
     * @param method
     * @param args 方法参数
     * @return
     */
    boolean doBefore(Class<?> klass, Method method, Object[] args);

    /**
     * 方法执行后执行
     * @param method
     * @param result 方法返回结果
     * @return
     */
    Object doAfter(Class<?> klass, Method method, Object result);

    /**
     * 方法发生异常后执行
     * @param method
     * @param e 异常
     */
    void doDealException(Class<?> klass, Method method, Throwable e);

}
