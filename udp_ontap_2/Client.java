package udp_ontap_2;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
	DatagramPacket packet;
	DatagramSocket socket;
	byte[] buffer = new byte[1024];
	
	public Client() throws SocketException, UnknownHostException {
		packet = new DatagramPacket(new byte[0], 0);
		packet.setAddress(InetAddress.getLocalHost());
		packet.setPort(2000);
		socket = new DatagramSocket();
		
	
	}
	
	public void run() throws IOException {
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		String line;
		
		while(true) {
			line = userIn.readLine();
			if(line.equalsIgnoreCase("QUIT")) {
				break;
			}
			
			sendResponse(line);
			getRequest();
		}
	}

	private void sendResponse(String line) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeUTF(line.toString());
		dos.writeUTF(".");
		dos.flush();
		byte[] data = baos.toByteArray();
		packet.setData(data);
		packet.setLength(data.length);
		socket.send(packet);
		
	}

	private void getRequest() throws IOException {
		packet.setData(buffer);
		packet.setLength(buffer.length);
		socket.receive(packet);
		ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
		DataInputStream dis = new DataInputStream(bais);
		String line;
		while((line = dis.readUTF()) != "-1") {
			System.out.println(line);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		Client client = new Client();
		client.run();
	}
}
