package com.gary.myspring.intercepters;

import com.gary.myspring.annotation.After;
import com.gary.myspring.annotation.Aspect;
import com.gary.myspring.annotation.Before;
import com.gary.myspring.annotation.ThrowException;
import com.gary.myspring.pojo.Student;
import com.gary.myspring.service.StudentService;
/**
 * describe:拦截器
 *
 * @author gary
 * @date 2019/1/17
 */
@Aspect
public class StudentLogger {
	
	public StudentLogger() {
	}
	
	@Before(klass= StudentService.class, method="getStudentById")
	public boolean beforeStudentService1(String id) {
		System.out.println("1置前拦截到学号:" + id);
		return true;
	}

	@Before(klass= StudentService.class, method="getStudentById")
	public boolean beforeStudentService2(String id) {
		System.out.println("2置前拦截到学号:" + id);
		return true;
	}

	@After(klass= StudentService.class, method="getStudentById", parameterTypes = {String.class})
	public Student afterStudentService1(Student student) {
		System.out.println("1后置拦截到学生:" + student);
		return student;
	}

	@After(klass= StudentService.class, method="getStudentById", parameterTypes = {String.class})
	public Student afterStudentService2(Student student) {
		System.out.println("2后置拦截到学生:" + student);
		return student;
	}

	@ThrowException(klass=StudentService.class, method="getStudentById", parameterTypes = {String.class})
	public void exceptionStudentService(Throwable throwable) {
		System.out.println("异常拦截" + throwable);
	}


}
