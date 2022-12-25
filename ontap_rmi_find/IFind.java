package ontap_rmi_find;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IFind {
	ArrayList<Student> findByName(String name) throws RemoteException;
	ArrayList<Student> findByAge(int age) throws RemoteException;
	ArrayList<Student> findByScore(double score) throws RemoteException;
}
