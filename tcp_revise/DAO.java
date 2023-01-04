package tcp_revise;

import java.util.ArrayList;

import udp_revise.Student;
import udp_revise.User;

public class DAO {
	public static  ArrayList<User> listUsers;
	public static  ArrayList<Student> listStudents;
	
	public DAO(ArrayList<User> listUsers, ArrayList<Student> listStudents) {
		this.listUsers = listUsers;
		this.listStudents = listStudents;
	}
	
	public DAO() {
		listStudents = new ArrayList<Student>();
		listStudents.add(new Student(1, "abc", 20, 8.0));
		listStudents.add(new Student(2, "xyz", 19, 9.0));
		
		listUsers = new ArrayList<User>();
		listUsers.add(new User("abc", "123456"));
		listUsers.add(new User("xyz", "123456"));
	}
	
	public static boolean checkUsername(String param) {
		for(User user :listUsers) {
			if(user.getUsername().equalsIgnoreCase(param)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean login(String userLogin, String param) {
		for(User user : listUsers) {
			if(user.getUsername().equals(userLogin) && user.getPassword().equals(param)){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Student> findById(String param){
		ArrayList<Student> result = new ArrayList<Student>();
		int stId = Integer.parseInt(param);
		for (Student student : listStudents) {
			if(student.getId() == stId) {
				result.add(student);
			}
		}
		return result;
	}
}
