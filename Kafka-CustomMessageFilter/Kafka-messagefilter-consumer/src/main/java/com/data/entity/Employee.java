package com.data.entity;

public class Employee {

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", age=" + age + "]";
	}
	private int empId;
	private int age;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Employee(int empId, int age) {
		super();
		this.empId = empId;
		this.age = age;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
}
