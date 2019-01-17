package com.gary.myspring.service;

import com.gary.myspring.annotation.Autowired;
import com.gary.myspring.annotation.Component;
import com.gary.myspring.dao.StudentDao;
import com.gary.myspring.pojo.Student;
/**
 * describe:测试Service
 *
 * @author gary
 * @date 2019/1/15
 */
@Component
public class StudentService {
	@Autowired
	private StudentDao studentDao;

	public StudentService() {
	}
	
	public Student getStudentById(String id) {
		System.out.println(this.getClass());
		//int a = 1 / 0;
		return studentDao.getStudentById("123");
	}
	
}
