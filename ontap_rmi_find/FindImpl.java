package ontap_rmi_find;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class FindImpl extends UnicastRemoteObject implements IFind {

	ArrayList<Student> list;
	
	protected FindImpl() throws RemoteException {
		list = new ArrayList<Student>();
		Student s1 = new Student(1, "duc", 20, 8.0);
		Student s2 = new Student(2, "abc", 20, 8.0);
		Student s3 = new Student(3, "xyz", 20, 8.0);
		list.add(s1);
		list.add(s2);
		list.add(s3);
	}
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public ArrayList<Student> findByName(String name) throws RemoteException {
		ArrayList<Student> result = new ArrayList<Student>();
		for (Student student : list) {
			if(name.equalsIgnoreCase(student.getName())) {
				result.add(student);
			}
		}
		return result;
	}

	@Override
	public ArrayList<Student> findByAge(int age) throws RemoteException {
		ArrayList<Student> result = new ArrayList<Student>();
		for (Student student : list) {
			if(age == student.getAge()) {
				result.add(student);
			}
		}
		return result;
	}

	@Override
	public ArrayList<Student> findByScore(double score) throws RemoteException {
		ArrayList<Student> result = new ArrayList<Student>();
		for (Student student : list) {
			if(score == student.getScore()) {
				result.add(student);
			}
		}	
		
		return result;
	}
	
}
