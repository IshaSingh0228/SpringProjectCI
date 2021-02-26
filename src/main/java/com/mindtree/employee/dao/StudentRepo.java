package com.mindtree.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.employee.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}
