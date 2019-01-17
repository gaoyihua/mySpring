package com.gary.myspring.test;

import com.gary.myspring.core.BeanFactory;
import com.gary.myspring.core.IntercepterScan;
import com.gary.myspring.pojo.ClassA;
import com.gary.myspring.service.StudentService;

/**
 * describe:IOC测试
 *
 * @author gary
 * @date 2019/01/16
 */
public class TestForAOP {
    public static void main(String[] args) {
        BeanFactory.scanPackage("com.gary.myspring");
        IntercepterScan.intercepterScan("com.gary.myspring");
        StudentService studentService = BeanFactory.getBean(StudentService.class);
        studentService.getStudentById("123");
    }
}
