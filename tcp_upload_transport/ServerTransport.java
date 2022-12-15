package tcp_upload_transport;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTransport {
	public static final	int PORT = 12345;
	public static void main(String[] args) throws IOException {
		ServerSocket ss  = new ServerSocket(PORT);
		while(true) {
			Socket s = new Socket();
			Thread t = new Transport(s);
			t.start();
		}
	}
}
