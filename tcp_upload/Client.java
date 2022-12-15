package tcp_upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		String srcFile = "D\\logs\\LTM\\tcp.txt";
		Socket socket = new Socket("127.0.0.1",2000);
		OutputStream netOut = new BufferedOutputStream(socket.getOutputStream());
		
		int data;
		InputStream fis = new BufferedInputStream(new FileInputStream(srcFile));
		
		while((data = fis.read()) != -1) {
			netOut.write(data);
		}
		netOut.flush();
		fis.close();
		socket.close();
		
	}
}
