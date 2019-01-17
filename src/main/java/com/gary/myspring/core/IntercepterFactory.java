package com.gary.myspring.core;

import java.util.HashMap;
import java.util.Map;
/**
 * describe:拦截器工厂
 *
 * @author gary
 * @date 2019/1/17
 */
public class IntercepterFactory {
	private static final Map<IntercepterTargetDefinition, Chain> beforeMap;
	private static final Map<IntercepterTargetDefinition, Chain> afterMap;
	private static final Map<IntercepterTargetDefinition, Chain> exceptionMap;
	
	static {
		beforeMap = new HashMap<>();
		afterMap = new HashMap<>();
		exceptionMap = new HashMap<>();
	}
	
	protected IntercepterFactory() {
	}
	
	private void addIntercepter(Map<IntercepterTargetDefinition, Chain> map,
			IntercepterTargetDefinition interTarget,
			IntercepterMethodDefinition intercepter) {
		Chain chain = map.get(interTarget);
		if (chain == null) {
			synchronized (IntercepterFactory.class) {
				if (chain == null) {
					chain = new Chain(intercepter);
					map.put(interTarget, chain);
				}
			}
			return ;
		}
		chain.setNextChain(new Chain(intercepter));
	}
	
	protected void addBeforeIntercepter(IntercepterTargetDefinition interTarget,
										IntercepterMethodDefinition intercepter) {
		addIntercepter(beforeMap, interTarget, intercepter);
	}
	
	protected void addAfterIntercepter(IntercepterTargetDefinition interTarget,
									   IntercepterMethodDefinition intercepter) {
		addIntercepter(afterMap, interTarget, intercepter);
	}
	
	protected void addExceptionIntercepter(IntercepterTargetDefinition interTarget,
										   IntercepterMethodDefinition intercepter) {
		addIntercepter(exceptionMap, interTarget, intercepter);
	}
	
	protected Chain getBeforeIntercepterList(
			IntercepterTargetDefinition interTarget) {
		return beforeMap.get(interTarget);
	}
	
	protected Chain getAfterIntercepterList(
			IntercepterTargetDefinition interTarget) {
		return afterMap.get(interTarget);
	}
	
	protected Chain getExceptionIntercepterList(
			IntercepterTargetDefinition interTarget) {
		return exceptionMap.get(interTarget);
	}
	
}
