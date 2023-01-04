package rmi_revise;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException {
		Registry reg = LocateRegistry.createRegistry(12345);
		SearchImpl dao = new SearchImpl("IS");
		reg.bind("IS", dao);
	}
}
