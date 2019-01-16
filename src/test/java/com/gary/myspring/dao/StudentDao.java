package com.gary.myspring.dao;

import com.gary.myspring.annotation.Component;
import com.gary.myspring.pojo.Student;
/**
 * describe:测试Dao
 *
 * @author gary
 * @date 2019/1/15
 */
@Component
public class StudentDao {

	public StudentDao() {
	}

	public Student getStudentById(String stuId) {
		Student student = new Student();
		student.setStuId(stuId);
		student.setName("张三");
		student.setPassword("123");
		return student;
	}
	
}
