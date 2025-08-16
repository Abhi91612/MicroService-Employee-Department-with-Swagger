package com.abhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.model.Department;
import com.abhi.service.DepartmentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("department")
public class DepartmentController {
	@Autowired private DepartmentService departmentService;
	
	@PostMapping("add")
	public ResponseEntity<Department> addDepartment(@RequestBody Department department){
		departmentService.saveDepartment(department);
		return ResponseEntity.status(HttpStatus.CREATED).body(department);
	}
	@GetMapping("byId/{did}")
	public ResponseEntity<Boolean> checkExistenceOfDepartment (@PathVariable int did){
		Boolean check=departmentService.checkExistence(did);
		return ResponseEntity.ok(check);
	}
	@PutMapping("update/employee-count/increase/{did}")
	public ResponseEntity<Boolean> increaseEmployeeCount(@PathVariable int did){
		departmentService.updateEmplCount(did,1);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	@PutMapping("update/employee-count/decrease/{did}")
	public ResponseEntity<Boolean> decreaseEmployeeCount(@PathVariable int did){
		departmentService.updateEmplCount(did,-1);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("detail/department/byId/{did}")
	public ResponseEntity<Department> getDepartmentDetailById(@PathVariable int did){
	
		return ResponseEntity.ok(departmentService.getDepartment(did));
	}
	@PutMapping("update/employee-count")
	public ResponseEntity<Boolean> updateEmployeeCount(@RequestParam int oldDid,@RequestParam int newDid){
	
		departmentService.updateEmplCount(oldDid,-1);
		departmentService.updateEmplCount(newDid,1);
		return ResponseEntity.ok(true);
	}
	
}
