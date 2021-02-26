package com.mindtree.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.employee.entity.Classroom;
import com.mindtree.employee.entity.Student;
import com.mindtree.employee.exception.ClassroomServiceException;
import com.mindtree.employee.exception.StudentServiceException;
import com.mindtree.employee.service.SchoolServiceImpl;

@RestController
public class Controller {

	@Autowired
	public SchoolServiceImpl service;

	@PostMapping("/classroom")
	public ResponseEntity<?> addClassroom(@RequestBody Classroom classroom) {
		String msg="";
		try {
			return new ResponseEntity<>(service.addClassroom(classroom), HttpStatus.CREATED);
		} catch (ClassroomServiceException e) {
			msg = e.getLocalizedMessage();
		}
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/student/{classId}")
	public ResponseEntity<?> addStudent(@RequestBody Student student, @PathVariable Integer classId) {
		String msg="";
		try {
			return new ResponseEntity<>(service.addStudent(student, classId), HttpStatus.CREATED);
		} catch (StudentServiceException e) {
			msg = e.getLocalizedMessage();
		}
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/student/{stuId}")
	public ResponseEntity<?> getStudentDetails(@PathVariable Integer stuId) {
		String msg="";
		try {
			return new ResponseEntity<>(service.getDetailsById(stuId), HttpStatus.OK);
		} catch (StudentServiceException e) {
			msg = e.getLocalizedMessage();
		}
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/student/{stuId}")
	public ResponseEntity<?> updateStudentAge(@RequestBody Integer age, @PathVariable Integer stuId) {
		String msg="";
		try {
			return new ResponseEntity<>(service.updateStudentAgeById(stuId, age), HttpStatus.OK);

		} catch (StudentServiceException e) {
			msg = e.getLocalizedMessage();
		}
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
	}
}
