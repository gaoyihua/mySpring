package com.gary.myspring.pojo;


import com.gary.myspring.annotation.Autowired;
import com.gary.myspring.annotation.Component;
import com.gary.myspring.dao.StudentDao;

@Component
public class ClassA {
	@Autowired
	private ClassB classb;
	
	public ClassA() {
	}

	public ClassB getClassb() {
		return classb;
	}

	public void setClassb(ClassB classb) {
		this.classb = classb;
	}

	@Override
	public String toString() {
		return "ClassA:";
	}

}
