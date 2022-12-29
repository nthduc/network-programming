package udp_infor_student_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
	public static void main(String[] args) throws IOException {
		DatagramSocket socket;
		DatagramPacket packet;
		InetAddress address;
		int port = 2222;
		byte[] buffer = new byte[1024];
		
		socket = new DatagramSocket();
		address = InetAddress.getByName("localhost");
		packet = new DatagramPacket(buffer, buffer.length,address,port);
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		String line;
		
		while((line = userIn.readLine()) != null) {
			byte[] data = line.getBytes();
			packet.setData(data);
			packet.setLength(data.length);
			socket.send(packet);
			if(line.equalsIgnoreCase("end")) break;
			
			packet.setData(buffer);
			packet.setLength(buffer.length);
			socket.receive(packet);
			String receive = new String(packet.getData(),0,packet.getLength());
			System.out.println(receive);
		}
		socket.close();
	}
}
