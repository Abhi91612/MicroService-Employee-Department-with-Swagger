package com.abhi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
