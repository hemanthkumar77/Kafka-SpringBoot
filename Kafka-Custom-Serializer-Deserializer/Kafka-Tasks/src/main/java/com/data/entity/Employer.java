package com.data.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import CustomClass.CustomLocalDateSerializer;

public class Employer {
  private int employeeId;
  private String empName;
  @JsonSerialize(using=CustomLocalDateSerializer.class)
  private LocalDate brithday;
public int getEmployeeId() {
	return employeeId;
}
public void setEmployeeId(int employeeId) {
	this.employeeId = employeeId;
}
public String getEmpName() {
	return empName;
}
public void setEmpName(String empName) {
	this.empName = empName;
}
public LocalDate getBrithday() {
	return brithday;
}
public void setBrithday(LocalDate brithday) {
	this.brithday = brithday;
}
public Employer(int employeeId, String empName, LocalDate brithday) {
	super();
	this.employeeId = employeeId;
	this.empName = empName;
	this.brithday = brithday;
}
  
  
}
