package com.gary.myspring.core;

/**
 * describe:拦截器链
 *
 * @author gary
 * @date 2019/01/17
 */
public class Chain{
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
}
