package com.crud.blog.boot2jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.blog.boot2jpa.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
