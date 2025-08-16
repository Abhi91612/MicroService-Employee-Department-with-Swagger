package com.abhi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhi.model.Department;
import com.abhi.repository.DepartmentRepository;
import com.abhi.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired private DepartmentRepository departmentRepository;

	@Override
	public void saveDepartment(Department department) {
	departmentRepository.save(department);
		
	}

	@Override
	public Boolean checkExistence(int did) {
		
		return departmentRepository.existsById(did);
	}

	@Override
	public void updateEmplCount(int did, int no) {
		departmentRepository.updatTotalEmployee(did,no);
			
	}

	@Override
	public Department getDepartment(int did) {
		
		return departmentRepository.findById(did).orElse(null);
	}
}
