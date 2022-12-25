package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
	public static void main(String[] args) throws IOException {
		int port = 2222;
		byte[] data = new byte[1024];
		DatagramSocket socket = new DatagramSocket(port);
		DatagramPacket packet = new DatagramPacket(data, data.length);
		System.out.println("welcome to server cho udp");
		
		while(true) {
			packet.setData(data);
			packet.setLength(data.length);
			socket.receive(packet);
			String receive = new String(packet.getData(),0,packet.getLength());
			if(receive.equalsIgnoreCase("end")) {
				receive = "byte client";
				byte[] dataSend = receive.getBytes();
				packet.setData(dataSend);
				packet.setLength(dataSend.length);
				socket.send(packet);
				socket.close();
				break;
			}
			else {
				System.out.println(receive);
				String response = "Server response" + receive;
				byte[] dataSend = response.getBytes();
				packet.setData(dataSend);
				packet.setLength(dataSend.length);
				socket.send(packet);
				
			}
		}
	}
}
