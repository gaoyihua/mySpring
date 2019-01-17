package com.gary.myspring.core;

import java.lang.reflect.Method;
/**
 * describe:
 *
 * @author gary
 * @date 2019/1/17
 */
public class IntercepterTargetDefinition {
	private Class<?> klass;
	private Method method;
	
	public IntercepterTargetDefinition(Class<?> klass, Method method) {
		this.klass = klass;
		this.method = method;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((klass == null) ? 0 : klass.hashCode());
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntercepterTargetDefinition other = (IntercepterTargetDefinition) obj;
		if (klass == null) {
			if (other.klass != null)
				return false;
		} else if (!klass.equals(other.klass))
			return false;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		return true;
	}

}
