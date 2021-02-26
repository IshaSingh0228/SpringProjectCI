package com.mindtree.employee.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int studentId;

	private String studentName;
	private int age;

	@OneToOne
	private Classroom classroom;

	public Student() {
		super();
	}

	public Student(String studentName, int age) {
		super();
		this.studentName = studentName;
		this.age = age;
	}

	public Student(int studentId, String studentName, int age, Classroom classroom) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.age = age;
		this.classroom = classroom;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	

}
