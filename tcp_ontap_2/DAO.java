package tcp_ontap_2;

import java.util.ArrayList;

public class DAO {
	public ArrayList<Student> listStudents;
	public ArrayList<User> listUsers;

	public DAO(ArrayList<Student> listStudents, ArrayList<User> listUsers) {

		this.listStudents = listStudents;
		this.listUsers = listUsers;
	}

	public DAO() {
		listStudents = new ArrayList<Student>();
		listStudents.add(new Student(1, "abc", 20, 9.0));
		listStudents.add(new Student(2, "xyz", 8, 8.0));

		listUsers = new ArrayList<User>();
		listUsers.add(new User("abc", "123456"));
		listUsers.add(new User("xyz", "123456"));
	}

	public boolean checkUserName(String param) {
		for (User user : listUsers) {
			if (user.getUsername().equals(param)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean login(String userLogin,String param) {
		for(User user : listUsers) {
			if(user.getUsername().equals(userLogin) && user.getPassword().equals(param)) {
				return true;			}
		}
		return false;
	}
	
	public ArrayList<Student> findByID(String param){
		ArrayList<Student> result = new ArrayList<Student>();
		int idStr = Integer.parseInt(param);
		for (Student student : listStudents) {
			if(student.getId() == idStr) {
				result.add(student);
			}
		}
		return result;
	}

}
