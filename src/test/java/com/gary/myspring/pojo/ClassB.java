package com.gary.myspring.pojo;


import com.gary.myspring.annotation.Autowired;
import com.gary.myspring.annotation.Component;
import com.gary.myspring.dao.StudentDao;

@Component
public class ClassB {
	@Autowired
	private ClassC classc;

	@Autowired
	private StudentDao studentDao;

	public ClassB() {
	}

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public ClassC getClassc() {
		return classc;
	}

	public void setClassc(ClassC classc) {
		this.classc = classc;
	}

	@Override
	public String toString() {
		return "ClassB:";
	}

}
