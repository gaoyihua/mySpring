package com.gary.myspring.core;

import java.lang.reflect.Method;
/**
 * describe:
 *
 * @author gary
 * @date 2019/1/17
 */
public class IntercepterMethodDefinition {
	private Class<?> klass;
	private Method method;
	private Object object;
	
	IntercepterMethodDefinition() {
	}

	IntercepterMethodDefinition(Class<?> klass, Method method, Object object) {
		this.klass = klass;
		this.method = method;
		this.object = object;
	}

	Class<?> getKlass() {
		return klass;
	}

	void setKlass(Class<?> klass) {
		this.klass = klass;
	}

	Method getMethod() {
		return method;
	}

	void setMethod(Method method) {
		this.method = method;
	}

	Object getObject() {
		return object;
	}

	void setObject(Object object) {
		this.object = object;
	}

}
