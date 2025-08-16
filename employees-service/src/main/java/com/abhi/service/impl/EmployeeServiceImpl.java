package com.abhi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.abhi.dto.EmployeeDto;
import com.abhi.model.Employee;
import com.abhi.repository.EmployeeRepository;
import com.abhi.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired private EmployeeRepository employeeRepository;
	//@Autowired private DepartmentRepository departmentRepository;
	
	@Autowired @Lazy private ModelMapper modelMapper;
	@Autowired  @Lazy private RestTemplate restTemplate;

	@Override
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
		//departmentRepository.updatTotalEmployee(employee.getDepartment().getDid());
		//restTemplate.getForObject("http://localhost:8080/department/update/employee-count/"+employee.getDeptId(),Boolean.class);
		restTemplate.put("http://localhost:8080/department/update/employee-count/increase/"+employee.getDeptId(), null);
	}

	@Override
	public Employee getRecord(int eid) {
		
		return employeeRepository.findById(eid).orElse(null);
	}

	@Override
	public EmployeeDto getRecordDto(int eid) {
		Employee employee=employeeRepository.findById(eid).orElse(null);
		EmployeeDto employeeDto=modelMapper.map(employee, EmployeeDto.class);
		employeeDto.setDepartmentId(employee.getDeptId());
		return employeeDto;
	}

	@Override
	public void deleteRecord(int eid,int did) { 
		 employeeRepository.deleteById(eid);
		restTemplate.put("http://localhost:8080/department/update/employee-count/decrease/"+did, null);
		
	}

	@Override
	public void updateDepartmentById(int ndid, Employee employee) {
		int odid=employee.getDeptId();
		employee.setDeptId(ndid);
		employeeRepository.save(employee);
		restTemplate.put("http://localhost:8080/department/update/employee-count?oldDid="+odid+"&newDid="+ndid, employee);
		
	}
}
