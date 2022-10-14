package io_student;

public class Subject {
	private int subjectId;
	private String subjectName;
	private int credits;
	public Subject(int subjectId, String subjectName, int credits) {
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.credits = credits; // tin chi
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subjectName=" + subjectName + ", credits=" + credits + "]";
	}
	
	
}
