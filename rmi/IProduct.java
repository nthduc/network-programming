package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IProduct extends Remote{
	String getDescription() throws RemoteException;
}
