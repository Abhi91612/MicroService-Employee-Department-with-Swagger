package com.abhi.dto;


public class Department {

	
	private int did;
	private String deptName;
	private String deptHeadName;
	private int totalEmployees;
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptHeadName() {
		return deptHeadName;
	}
	public void setDeptHeadName(String deptHeadName) {
		this.deptHeadName = deptHeadName;
	}
	public int getTotalEmployees() {
		return totalEmployees;
	}
	public void setTotalEmployees(int totalEmployees) {
		this.totalEmployees = totalEmployees;
	}
		 
}
