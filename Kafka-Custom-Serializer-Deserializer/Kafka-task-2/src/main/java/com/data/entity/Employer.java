package com.data.entity;

import java.time.LocalDate;

import com.data.custom.DeserilaizerJson;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Employer {
	private int employeeId;
	  private String empName;
	  @JsonDeserialize(using=DeserilaizerJson.class)
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
	public Employer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	@Override
	public String toString() {
		return "Employer [employeeId=" + employeeId + ", empName=" + empName + ", brithday=" + brithday + "]";
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
