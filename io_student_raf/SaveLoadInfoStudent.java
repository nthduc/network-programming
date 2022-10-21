package io_student_raf;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class SaveLoadInfoStudent {
	public void save(ArrayList<Student> listStudents, String path) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(path, "rw");
		raf.writeInt(listStudents.size());
		
		for(int i = 0 ; i < listStudents.size(); i++) {
			raf.writeLong(0);
		}
		ArrayList<Long> positions = new ArrayList<Long>();
		for (Student student : listStudents) {
			positions.add(raf.getFilePointer());
			raf.writeInt(student.getId());
			raf.writeUTF(student.getName());
			raf.writeInt(student.getYear());
			raf.writeDouble(student.getScore());
		}
		raf.seek(4);
		for (Long position : positions) {
			raf.writeLong(position);
		}
		raf.close();
	}
	
	public Student readStudent(String path, int index) throws IOException{
		Student st1 = null;
		RandomAccessFile raf = new RandomAccessFile(path, "rw");
		Long position = (long) (4+8*index);
		raf.seek(position);
		Long position_index = raf.readLong();
		raf.seek(position_index);
		st1 = new Student(raf.readInt(), raf.readUTF(), raf.readInt(), raf.readDouble());
		
		raf.close();
		return st1;
		
	}
	
	public static void main(String[] args) throws IOException {
		Student s1 = new Student(1, "NTD", 2000, 8.5);
		Student s2 = new Student(2, "Nguyen Van A", 2001, 9.5);
		Student s3 = new Student(3, "Tran Thi B", 2002, 6.5);
		ArrayList<Student> listStudents = new ArrayList<Student>();
		listStudents.add(s1);listStudents.add(s2);listStudents.add(s3);
		String path = "D:\\logs\\LTM\\raf1.txt";
		new SaveLoadInfoStudent().save(listStudents, path);
		new SaveLoadInfoStudent().readStudent(path, 2);
		
	}
}
