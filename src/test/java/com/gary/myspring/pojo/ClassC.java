package com.gary.myspring.pojo;


import com.gary.myspring.annotation.Autowired;
import com.gary.myspring.annotation.Component;
import com.gary.myspring.dao.StudentDao;

@Component
public class ClassC {
	@Autowired
	private ClassA classa;

	@Autowired
	private StudentDao studentDao;
	
	public ClassC() {
	}

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public ClassA getClassa() {
		return classa;
	}

	public void setClassa(ClassA classa) {
		this.classa = classa;
	}


}
