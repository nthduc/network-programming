package udp_revise;

import java.util.ArrayList;

public class DAO {
	public static ArrayList<Student> listStudents;
	public static ArrayList<User> listUsers;
	
	public DAO(ArrayList<Student> listStudents, ArrayList<User> listUsers) {
		this.listStudents = listStudents;
		this.listUsers = listUsers;
	}
	
	public DAO() {
		listStudents = new ArrayList<Student>();
		listStudents.add(new Student(1, "abc", 20, 8.0));
		listStudents.add(new Student(2, "xyz", 19, 9.0));
		
		listUsers = new ArrayList<User>();
		listUsers.add(new User("abc", "123456"));
		listUsers.add(new User("xyz", "123456"));
	}
	
	public static boolean checkUserName(String param) {
		for (User user : listUsers) {
			if(user.getUsername().equals(param)) {
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
	
	public static ArrayList<Student> findById(String param){
		ArrayList<Student> result = new ArrayList<Student>();
		int stId = Integer.parseInt(param);
		for(Student student : listStudents) {
			if(student.getId() == stId) {
				result.add(student);
				break;
			}
		}
		return result;
	}
}
