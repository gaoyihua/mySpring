package com.gary.myspring.core;

import com.gary.myspring.exception.BeanAlreadyDefineException;

import java.util.HashMap;
import java.util.Map;

/**
 * describe:代理Bean工厂
 *
 * @author gary
 * @date 2019/01/16
 */
public class ProxyBeanFactory {
    private static final Map<String, MyProxy> BEAN_MAP;
    private static final Map<String, String> BEAN_NAME_MAP;
    static {
        BEAN_MAP = new HashMap<>();
        BEAN_NAME_MAP = new HashMap<>();
    }

    public ProxyBeanFactory() {
    }

    protected void addBeanName(String baenName, String className) {
        String orgclassName = BEAN_NAME_MAP.get(baenName);
        if (orgclassName != null) {
            //TODO BeanName重复 解决方法：1.抛出异常
        }
        BEAN_NAME_MAP.put(baenName, className);
    }

    protected String getBaenClassName(String beanName) {
        return BEAN_NAME_MAP.get(beanName);
    }

    public MyProxy getMyProxy(String className) {
        return BEAN_MAP.get(className);
    }

    protected boolean creteCGLibProxy(Object object) throws Exception {
        return cglibProxy(object, object.getClass());
    }

    protected boolean creteCGLibProxy(Class<?> klass) throws Exception {
        return cglibProxy(klass.newInstance(), klass);
    }

    protected boolean createJDKproxy(Object object) throws Exception {
        return jdkProxy(object, object.getClass());
    }

    protected boolean createJDKproxy(Class<?> klass) throws Exception {
        return jdkProxy(klass.newInstance(), klass);
    }

    private void judge(String className) throws BeanAlreadyDefineException {
        MyProxy myProxy = BEAN_MAP.get(className);
        if (myProxy != null) {
            throw new BeanAlreadyDefineException();
        }
    }

    private boolean jdkProxy(Object object, Class<?> klass) throws BeanAlreadyDefineException {
        String className = klass.getName();
        judge(className);
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.getJDKProxy(object, klass);
        BEAN_MAP.put(className, proxyFactory.getMyProxy());

        return true;
    }

    private boolean cglibProxy(Object object, Class<?> klass) throws BeanAlreadyDefineException {
        String className = klass.getName();
        judge(className);
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.getCGLIBProxy(object, klass);
        BEAN_MAP.put(className, proxyFactory.getMyProxy());

        return true;
    }

}
