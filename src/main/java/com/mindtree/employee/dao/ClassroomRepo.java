package com.mindtree.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.employee.entity.Classroom;

public interface ClassroomRepo extends JpaRepository<Classroom, Integer>{

}
