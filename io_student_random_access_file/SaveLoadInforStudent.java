package io_student_random_access_file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class SaveLoadInforStudent {
	
	public void save(String path, ArrayList<Student> listStudents) throws IOException {
		long postion;
		
		RandomAccessFile raf = new RandomAccessFile(path, "rw");
		raf.write(listStudents.size());
		
		for(int i = 0; i < listStudents.size(); i++) {
			raf.writeInt(0);
		}
		raf.writeLong(0);
		
		Student student;
		for(int i = 0 ; i < listStudents.size(); i++) {
			postion = raf.getFilePointer(); //vi tri cua sinh vien
			student = listStudents.get(i);
			student.save(raf);
		}
		raf.close();
	}
	
	public Student loadStudent(String path, int index) throws IOException {
		long position = 0;
		Student student;
		index--;
		RandomAccessFile raf = new RandomAccessFile(path, "rw");
		
		raf.seek(4+position*8);
		position = raf.readLong();
		
		raf.seek(position);
		student = new Student();
		student.read(raf);
		
		raf.close();
		return student;
	}
	
	public void updateStudent(String path, Student student, int index) throws IOException {
		long position = 0;
		
		RandomAccessFile raf = new RandomAccessFile(path, "rw");
		raf.writeLong(position);
		position = raf.getFilePointer();
		student.save(raf);
		
		raf.close();
		
	}
	
	public static void main(String[] args) throws IOException {
		ArrayList<Student> los = new ArrayList<Student>();
		String path = "D:\\logs\\LTM\\raf.txt";
		
		los.add(new Student(1, "NTD", 1000, 8.0));
		los.add(new Student(2, "ABC", 2000, 9.0));
		los.add(new Student(3, "XYZ", 3000, 9.9));
		new SaveLoadInforStudent().save(path, los);
		new SaveLoadInforStudent().loadStudent(path, 1);
	}
}
