package com.gary.myspring.core;

import com.gary.myspring.annotation.After;
import com.gary.myspring.annotation.Aspect;
import com.gary.myspring.annotation.Before;
import com.gary.myspring.annotation.ThrowException;
import com.gary.util.PackageScanner;

import java.lang.reflect.Method;

/**
 * describe:扫描拦截器
 *
 * @author gary
 * @date 2019/01/17
 */
public class IntercepterScan {
    public static void intercepterScan(String packageName) {
        new PackageScanner() {
            @Override
            public void dealClass(Class<?> klass) {
                if (!klass.isAnnotationPresent(Aspect.class)) {
                    return ;
                }

                try {
                    Object object = klass.newInstance();
                    Method[] methods = klass.getDeclaredMethods();
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(Before.class)) {
                            Before before = method.getAnnotation(Before.class);
                            dealBeforeIntercepter(klass, object, method, before);
                        } else if (method.isAnnotationPresent(After.class)) {
                            After after = method.getAnnotation(After.class);
                            dealAfterIntercepter(klass, object, method, after);
                        } else if (method.isAnnotationPresent(ThrowException.class)) {
                            ThrowException throwException = method.getAnnotation(ThrowException.class);
                            dealThrowExceptionIntercepter(klass, object, method, throwException);

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.packageScan(packageName);
    }

    private static void dealThrowExceptionIntercepter(Class<?> klass, Object object, Method method, ThrowException throwException) throws Exception {
        Class<?> intercepterReturnType = method.getReturnType();
        if (!intercepterReturnType.equals(void.class)) {
            //TODO 抛出异常 拦截器返回值只能是void
        }
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length != 1 || Throwable.class.isAssignableFrom(parameterTypes[0])) {
            //TODO 抛出异常 拦截器参数只能是Throwable或者Throwable子类
        }
        Class<?> targetKlass = throwException.klass();
        Method targetMethod = targetKlass.getMethod(throwException.method(), throwException.parameterTypes());

        IntercepterMethodDefinition imd = new IntercepterMethodDefinition(klass, method, object);
        IntercepterTargetDefinition itd = new IntercepterTargetDefinition(targetKlass, targetMethod);

        IntercepterFactory intercepterFactory = new IntercepterFactory();
        intercepterFactory.addExceptionIntercepter(itd, imd);
    }

    private static void dealAfterIntercepter(Class<?> klass, Object object, Method method, After after) throws Exception {
        Class<?> intercepterReturnType = method.getReturnType();
        Class<?> targetKlass = after.klass();
        Method targetMethod = targetKlass.getMethod(after.method(), after.parameterTypes());
        Class<?> targetReturnType = targetMethod.getReturnType();

        if (!intercepterReturnType.equals(targetReturnType)) {
            //TODO 抛出异常 后置拦截器返回值类型与被拦截方法返回值类型不同
        }

        IntercepterMethodDefinition imd = new IntercepterMethodDefinition(klass, method, object);
        IntercepterTargetDefinition itd = new IntercepterTargetDefinition(targetKlass, targetMethod);

        IntercepterFactory intercepterFactory = new IntercepterFactory();
        intercepterFactory.addAfterIntercepter(itd, imd);
    }

    private static void dealBeforeIntercepter(Class<?> klass, Object object, Method method, Before before) throws Exception {
        Class<?> returnType = method.getReturnType();
        if (!returnType.equals(boolean.class)) {
            //TODO 抛出异常 前置拦截返回值类型只能是boolean
        }
        Class<?> targetKlass = before.klass();
        Method targetMethod = targetKlass.getMethod(before.method(), method.getParameterTypes());

        IntercepterMethodDefinition imd = new IntercepterMethodDefinition(klass, method, object);
        IntercepterTargetDefinition itd = new IntercepterTargetDefinition(targetKlass, targetMethod);

        IntercepterFactory intercepterFactory = new IntercepterFactory();
        intercepterFactory.addBeforeIntercepter(itd, imd);
    }
}
