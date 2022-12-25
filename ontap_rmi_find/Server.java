package ontap_rmi_find;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	public static void main(String[] args) throws RemoteException {
		Registry reg = LocateRegistry.createRegistry(2222);
		FindImpl findImpl = new FindImpl();
		System.out.println("Server is running...");
		
	}
}
