package com.abhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.abhi.dto.Department;
import com.abhi.dto.EmployeeDto;
import com.abhi.model.Employee;
import com.abhi.service.EmployeeService;
import com.abhi.exceptions.DepartmentNotFoundException;
import com.abhi.exceptions.EmployeeNotFoundException;

@RestController
@RequestMapping("employee")
public class EmployeeController {

	@Autowired private EmployeeService employeeService;
	@Autowired @Lazy private RestTemplate restTemplate;

    @PostMapping("add")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) 
	{
		int deptId=employee.getDeptId();
		boolean isDepartmentExist=restTemplate.getForObject("http://localhost:8080/department/byId/"+deptId, Boolean.class);
		if(!isDepartmentExist) {
			throw new DepartmentNotFoundException("Entered Department id "+deptId+" is not exist please try another !  ");
			
		}
		employeeService.saveEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(employee);
	}
	@GetMapping("detail/byid")
	public ResponseEntity<EmployeeDto> getEmployee(@RequestParam int eid){
		EmployeeDto employeeDto= employeeService.getRecordDto(eid);
		if (employeeDto==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(employeeDto);
	}
	@GetMapping("detail/department/byeid/{eid}")
	public ResponseEntity<Department> getDetailOfDepartmentByEid(@PathVariable int eid) {
		
		Employee employee=employeeService.getRecord(eid);
		if(employee ==null) {
			throw new EmployeeNotFoundException("Employee does not Exist With eid "+eid);
		}
		int did=employee.getDeptId();
		Department departmentDto=restTemplate.getForObject("http://localhost:8080/department/detail/department/byId/"+did, Department.class);
		return ResponseEntity.status(HttpStatus.FOUND).body(departmentDto);
		
	}
	
	@DeleteMapping("delete/byid/{eid}")
	public ResponseEntity<Employee> deleteRecordById(@PathVariable int eid){
		
		Employee employee=employeeService.getRecord(eid);
		if(employee ==null) {
			throw new EmployeeNotFoundException("Employee does not Exist With eid "+eid);
		}
		int did=employee.getDeptId();
		employeeService.deleteRecord(eid,did);
		return ResponseEntity.ok(employee);
		
	}
	@PatchMapping("change/department/byid")
	public ResponseEntity<Employee> changeDepartmentById(@RequestParam int did, @RequestParam int eid){
		Employee employee=employeeService.getRecord(eid);
		if(employee ==null) {
			throw new EmployeeNotFoundException("Employee does not Exist With eid "+eid);
		}
		boolean isDepartmentExist=restTemplate.getForObject("http://localhost:8080/department/byId/"+did, Boolean.class);
		if(!isDepartmentExist) {
			throw new DepartmentNotFoundException("Entered Department id "+did+" is not exist please try another !  ");
			
		}
		employeeService.updateDepartmentById(did,employee);
		return ResponseEntity.ok(employee);
	}
	
}
