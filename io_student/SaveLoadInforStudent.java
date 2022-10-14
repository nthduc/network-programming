package io_student;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SaveLoadInforStudent {
	
	public void save(String path, ArrayList<Student> listStudents) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(path));
		dos.writeInt(listStudents.size());
		
		for (Student student : listStudents) {
			dos.writeInt(student.getStudentId());
			dos.writeUTF(student.getFullName());
			dos.writeInt(student.getListSubjects().size());
			
			for (Subject subject : student.getListSubjects()) {
				dos.writeInt(subject.getSubjectId());
				dos.writeUTF(subject.getSubjectName());
				dos.writeInt(subject.getCredits());
			}
		}
		
		dos.close();
	}
	// LOAD
	public ArrayList<Student> load(String path) throws IOException {
		ArrayList<Student> listStudents = new ArrayList<>();
		File file = new File(path);
		if(!file.exists()) return null;
		DataInputStream dis = new DataInputStream(new FileInputStream(path));
		
		int numberStudent = dis.readInt();
		for(int i = 0 ; i < numberStudent; i++) { 
			int studentId = dis.readInt();
			String fullName = dis.readUTF();
			ArrayList<Subject> listSubject = new ArrayList<>();
			
			int numbersSubject = dis.readInt();
			for(int j = 0; j < numbersSubject; j++) {
				int subjectId = dis.readInt();
				String subjectName = dis.readUTF();
				int creadit = dis.readInt();
				Subject subject = new Subject(subjectId, subjectName, creadit);
				listSubject.add(subject);
			}
			 
			Student st = new Student(studentId, fullName, listSubject);
			listStudents.add(st);
		}
		dis.close();
		return listStudents;
	}
	
	
	
	public static void main(String[] args) throws IOException {
		Subject s1 = new Subject(123, "Networking Program", 4);
		Subject s2 = new Subject(234, "Programing Basic", 4);
		Subject s3 = new Subject(345, "OOP", 4);
		
		ArrayList<Subject> listSubjects1 = new ArrayList<Subject>();
		listSubjects1.add(s1);
		listSubjects1.add(s3);
		
		ArrayList<Subject> listSubjects2 = new ArrayList<Subject>();
		listSubjects2.add(s1);
		listSubjects2.add(s2);
		
		ArrayList<Student> listStudent = new ArrayList<Student>();
		Student st1 = new Student(1, "Nguyen Duc", listSubjects1);
		Student st2 = new Student(2, "Nguyen van A",listSubjects2);
		
		listStudent.add(st1);
		listStudent.add(st2);
		
		new SaveLoadInforStudent().save("D:\\logs\\LTM\\data.txt", listStudent);
		new SaveLoadInforStudent().load("D:\\logs\\LTM\\data.txt");
	}
}
