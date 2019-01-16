package com.gary.myspring.core;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * describe:带有Bean注解的方法的描述
 *
 * @author gary
 * @date 2019/01/16
 */
public class BeanMethodDefinition {
    private Method method;
    private Class<?> returnType;
    private Parameter[] parameters;
    private Object object;
    private String name;

    public BeanMethodDefinition() {
    }

    public BeanMethodDefinition(Method method, Class<?> returnType, Parameter[] parameters, Object object, String name) {
        this.method = method;
        this.returnType = returnType;
        this.parameters = parameters;
        this.object = object;
        this.name = name;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

    public Parameter[] getParameters() {
        return parameters;
    }

    public void setParameters(Parameter[] parameters) {
        this.parameters = parameters;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
