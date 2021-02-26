package com.mindtree.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.employee.dao.ClassroomRepo;
import com.mindtree.employee.dao.StudentRepo;
import com.mindtree.employee.entity.Classroom;
import com.mindtree.employee.entity.Student;
import com.mindtree.employee.exception.ClassroomServiceException;
import com.mindtree.employee.exception.StudentServiceException;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private StudentRepo studentRepo;
	@Autowired
	private ClassroomRepo classroomRepo;
	
	@Override
	public String addClassroom(Classroom classroom) throws ClassroomServiceException {
		// TODO Auto-generated method stub
		try {
			classroomRepo.save(classroom);
			return "added class successfully";
		} catch (Exception e) {
			throw new ClassroomServiceException(e);
		}
	}

	@Override
	public String addStudent(Student student, int classId) throws StudentServiceException {
		// TODO Auto-generated method stub
		try {
			Classroom classroom = classroomRepo.findById(classId).get();
			classroom.setNoOfStudents(classroom.getNoOfStudents() + 1);
			student.setClassroom(classroom);
			//student.setAge(student.getAge());
			studentRepo.save(student);
			return "added student successfully";
		} catch (Exception e) {
			throw new StudentServiceException(e);
		}
	}

	@Override
	public Student getDetailsById(int studentId) throws StudentServiceException {
		// TODO Auto-generated method stub
		try {
			Student stu = studentRepo.findById(studentId).get();
			return stu;
		} catch (Exception e) {
			throw new StudentServiceException("StudentId Not Found");
		}
	}

	@Override
	public Student updateStudentAgeById(int studentId, int age) throws StudentServiceException {
		// TODO Auto-generated method stub
		try {
			Student stu = studentRepo.findById(studentId).get();
			stu.setAge(age);
			Student newStudent = studentRepo.save(stu);
			return newStudent;

		} catch (Exception e) {
			throw new StudentServiceException("StudentId Not Found");
		}

	}

}
