package rmi_ontap_2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ISearch extends Remote{
	public boolean checkUserName(String param) throws RemoteException;
	public boolean login(String userLogin, String param) throws RemoteException;
	public ArrayList<Student> findByID(String param) throws RemoteException;
}
