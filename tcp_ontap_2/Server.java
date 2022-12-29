package tcp_ontap_2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket();
		
		while(true) {
			Socket socket = ss.accept();
			ServerProcess sp = new ServerProcess(socket);
			sp.start();
		}
	}
}
