package com.gary.myspring.test;

import com.gary.myspring.core.BeanFactory;
import com.gary.myspring.dao.StudentDao;

/**
 * describe:
 *
 * @author gary
 * @date 2019/01/16
 */
public class TestForIOC {
    public static void main(String[] args) {
        BeanFactory.scanPackage("com.gary.myspring.dao");
        StudentDao studentDao = BeanFactory.getBean(StudentDao.class);
        System.out.println(studentDao.getStudentById("123"));
    }
}
