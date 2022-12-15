package tcp_upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		String destFile = "D\\logs\\LTM\\tcp.txt";
		ServerSocket s = new ServerSocket(3000);
		Socket socket = s.accept();
		InputStream netIn = new BufferedInputStream(socket.getInputStream());
		
		int data;
		OutputStream fos = new BufferedOutputStream(new FileOutputStream(destFile));
		
		while((data = netIn.read()) != -1) {
			fos.write(data);
		}
		
		fos.close();
		socket.close();
	}
}
