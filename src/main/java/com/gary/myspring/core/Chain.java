package com.gary.myspring.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * describe:拦截器链
 *
 * @author gary
 * @date 2019/01/17
 */
public class Chain implements IChain{
    protected Chain nextChain;
    protected IntercepterMethodDefinition intercepterMethodDefinition;

    public Chain(IntercepterMethodDefinition intercepterMethodDefinition) {
        this.intercepterMethodDefinition = intercepterMethodDefinition;
    }

    public IntercepterMethodDefinition getIntercepterMethodDefinition() {
        return intercepterMethodDefinition;
    }

    public void setNextChain(Chain nextChain) {

        if (this.nextChain != null) {
            this.nextChain.setNextChain(nextChain);
            return ;
        }
        this.nextChain = nextChain;
    }

    public Chain getNextChain() {
        return nextChain;
    }

    public boolean hasNext() {
        if (nextChain == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean doBefore(Class<?> klass, Method method, Object[] args) {
        boolean result = false;
        try {
            result = (boolean)intercepterMethodDefinition.getMethod().invoke(intercepterMethodDefinition.getObject(), args);
            if (result == false) {
                return result;
            }
            if (this.nextChain != null) {
                return this.nextChain.doBefore(klass, method, args);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Object doAfter(Class<?> klass, Method method, Object result) {
        Object res = null;
        try {
            res = intercepterMethodDefinition.getMethod().invoke(intercepterMethodDefinition.getObject(), result);
            if (this.nextChain != null) {
                res = this.nextChain.doAfter(klass, method, result);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public void doDealException(Class<?> klass, Method method, Throwable throwable) {
        try {
            intercepterMethodDefinition.getMethod().invoke(intercepterMethodDefinition.getObject(), throwable);
            if (this.nextChain != null) {
                this.nextChain.doDealException(klass, method, throwable);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
