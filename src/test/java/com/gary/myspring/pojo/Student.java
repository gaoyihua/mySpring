package com.gary.myspring.pojo;
/**
 * describe:学生类
 *
 * @author gary
 * @date 2019/1/15
 */
public class Student {
	private String stuId;
	private String name;
	private String password;
	
	public Student() {
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return stuId + ":" + name + "(" + password + ")";
	}
	
}
