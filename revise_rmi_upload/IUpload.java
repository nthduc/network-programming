package revise_rmi_upload;

import java.io.IOException;
import java.rmi.RemoteException;

public interface IUpload {
	void openSourceFile(String sf) throws RemoteException;
	byte[] readData() throws RemoteException, IOException;
	void closeConnection() throws RemoteException;
}
