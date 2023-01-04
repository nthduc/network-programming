package rmi_revise;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public interface ISearch extends Remote {
	public boolean checkUserName(String param) throws RemoteException, SQLException;
	public boolean login(String userLogin, String param) throws RemoteException;
	public ArrayList<Student> findById(String param) throws RemoteException;
}
