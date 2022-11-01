package saveloadstudent;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveLoadInfoStudent {
	
	public void save(ArrayList<Student> listStudents, String path) throws IOException, FileNotFoundException {
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
		pw.write(listStudents.size());
		
		for (Student student : listStudents) {
			pw.write(student.getId());
			pw.write(student.getName());
			pw.write(student.getYear());
			pw.write((int) student.getScore());
		}
		
		pw.close();
	}
	
	public void load(String path) throws IOException, FileNotFoundException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
		
		String line = br.readLine();
		while(line != null) {
			System.out.println(line);
			line = br.readLine();
			
		}
		
		br.close();
	}
	
	public static void main(String[] args) throws IOException {
		Student s1 = new Student(1, "abc", 1922, 8.5);
		Student s2 = new Student(2, "xyz", 1901, 7.5);
		ArrayList<Student> listStudents = new ArrayList<Student>();
		
		listStudents.add(s1);
		listStudents.add(s2);
		
		String path = "D:\\logs\\LTM\\st.txt";
//		new SaveLoadInfoStudent().save(listStudents, path);
		new SaveLoadInfoStudent().load(path);
		
	}
}
