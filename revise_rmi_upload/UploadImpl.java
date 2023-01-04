package revise_rmi_upload;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UploadImpl extends UnicastRemoteObject implements IUpload{
	BufferedInputStream bis;
	
	protected UploadImpl() throws RemoteException {
		super();
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	public void openSourceFile(String sf) throws RemoteException {
		try {
			bis = new BufferedInputStream(new FileInputStream(sf));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public byte[] readData() throws RemoteException, IOException {
		byte[] buffer = new byte[1024 * 100];
		int data;
		data = bis.read(buffer);
		if(data == -1) {
			return null;
		}
		if(data== buffer.length) {
			return buffer;
		} else {
			byte[] temp = new byte[data];
			System.arraycopy(buffer, 0, temp, 0, data);
			return temp;
		}
		
	}

	@Override
	public void closeConnection() throws RemoteException {
		
		
	}
	
}
