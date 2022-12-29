package udp_ontap_2;

import java.util.ArrayList;

public class DAO {
	public ArrayList<User> listUsers;
	public ArrayList<Student> listStudents;
	
	public DAO(ArrayList<User> listUsers, ArrayList<Student> listStudents) {
		this.listUsers = listUsers;
		this.listStudents = listStudents;
	}
	public DAO() {
		listStudents = new ArrayList<Student>();
		listStudents.add(new Student(1, "abc", 20, 9.0));
		listStudents.add(new Student(2, "xyz", 20, 8.0));
		
		listUsers = new ArrayList<User>();
		listUsers.add(new User("sbc", "123456"));
		listUsers.add(new User("xyz", "123456"));
	}
	
	public boolean checkUserName(String param) {
		for(User user : listUsers) {
			if(user.getUsername().equals(param)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean login(String userLogin, String param) {
		for(User user : listUsers) {
			if(user.getUsername().equals(userLogin) && user.getPassword().equals(param)) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Student> findById(String param){
		ArrayList<Student> result = new ArrayList<Student>();
		int idSt = Integer.parseInt(param);
		for (Student student : result) {
			if(student.getId() == idSt) {
				result.add(student);
			}
		}
		return result;
	}
	
	
}
