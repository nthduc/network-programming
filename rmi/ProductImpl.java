package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ProductImpl extends UnicastRemoteObject implements IProduct{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String desc;
	
	protected ProductImpl(String name, String desc) throws RemoteException {
		super();
		this.name = name;
		this.desc = desc;
	}
	
	@Override
	public String getDescription() {
		return desc;
	}
	
}
