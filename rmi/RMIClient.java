package rmi;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry reg = LocateRegistry.getRegistry("127.0.0.1",123456);
		
		Remote r1 = reg.lookup("CA");
		IProduct iproduct = (IProduct) r1;
	}
}
