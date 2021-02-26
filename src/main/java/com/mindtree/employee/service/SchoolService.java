package com.mindtree.employee.service;

import com.mindtree.employee.entity.Classroom;
import com.mindtree.employee.entity.Student;
import com.mindtree.employee.exception.ClassroomServiceException;
import com.mindtree.employee.exception.StudentServiceException;

public interface SchoolService {

	String addClassroom(Classroom classroom) throws ClassroomServiceException;

	String addStudent(Student student, int classId) throws StudentServiceException;

	Student getDetailsById(int studentId) throws StudentServiceException;

	Student updateStudentAgeById(int studentId, int age) throws StudentServiceException;

}
