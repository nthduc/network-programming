package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
	public static void main(String[] args) throws RemoteException {
		Registry reg = LocateRegistry.createRegistry(123456);
		
		ProductImpl canon, nikon;
		canon = new ProductImpl("canon eos 1000", "may anh canon");
		nikon = new ProductImpl("nikon eos 1000", "may anh nikon");
		
		reg.rebind("CA", nikon);
		reg.rebind("CA", canon);
	}
}
