package io_student;

import java.util.List;

public class Student {
	private int studentId;
	private String fullName;
	private List<Subject> listSubjects;

	public Student() {
	}

	public Student(int studentId, String fullName, List<Subject> listSubjects) {
		this.studentId = studentId;
		this.fullName = fullName;
		this.listSubjects = listSubjects;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<Subject> getListSubjects() {
		return listSubjects;
	}

	public void setListSubjects(List<Subject> listSubjects) {
		this.listSubjects = listSubjects;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", fullName=" + fullName + ", listSubjects=" + listSubjects + "]";
	}

}
