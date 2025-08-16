package com.abhi.service;

import com.abhi.model.Department;

public interface DepartmentService {

	void saveDepartment(Department department);

	Boolean checkExistence(int did);

	void updateEmplCount(int did,int no);

	Department getDepartment(int did);

	
}
