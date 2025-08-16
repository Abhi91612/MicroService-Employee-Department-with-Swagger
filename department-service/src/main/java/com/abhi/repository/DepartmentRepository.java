package com.abhi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abhi.model.Department;

import jakarta.transaction.Transactional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	@Transactional
	@Modifying
	@Query("update Department set  totalEmployees  = totalEmployees + :arg1  where did =:arg2")
	void updatTotalEmployee(@Param("arg2") int i, @Param("arg1") int no);

}
