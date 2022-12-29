package udp_ontap_2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Server {
	DatagramPacket packet;
	DatagramSocket socket;
	int port = 2000;
	byte[] buffer = new byte[1024];
	String userLogin;
	DAO dao;
	
	public Server() throws SocketException {
		socket = new DatagramSocket(port);
		packet = new DatagramPacket(buffer, buffer.length);
		dao = new DAO();
	}
	
	public void run() throws IOException {
		String command, param;
		boolean isLogin = false;
		String line;
		
		while(true) {
			line = getRequest();
			if(line.equals("") || line.equalsIgnoreCase("QUIT")) {
				break;
			}
			
			StringTokenizer token = new StringTokenizer(line);
			command = token.nextToken();
			if(token.hasMoreElements()) {
				param = token.nextToken();
			} else {
				sendResponse("param not data");
				continue;
			}
			
			if(command.equalsIgnoreCase("USER")) {
				if(dao.checkUserName(param)) {
					userLogin = param;
					sendResponse("OK" + param);
				} else {
					sendResponse("User not exits");
				}
			} else if(command.equalsIgnoreCase("PASS")) {
				if(userLogin == "") {
					sendResponse("User not found");
					continue;
				} else if(dao.login(userLogin, param)) {
					ArrayList<String> listMessage = new ArrayList<String>();
					listMessage.add("Hello" + userLogin);
					listMessage.add("Login Successfully!");
					sendResponse(listMessage);
					break;
				} else {
					sendResponse("Login Failed");
				}
			} else {
				sendResponse("command not exits");
			}
		}
		
		while(isLogin) {
			line = getRequest();
			if(line.equals("") || line.equalsIgnoreCase("QUIT")) {
				sendResponse("Good byte");
				break;
			}
			ArrayList<Student> listStudents = new ArrayList<Student>();
			StringTokenizer token = new StringTokenizer(line);
			command = token.nextToken();
			
			if(token.hasMoreElements()) {
				param = token.nextToken();
			} else {
				sendResponse("param not data");
				continue;
			}
			
			if(command.equalsIgnoreCase("FIND_ID")) {
				listStudents = dao.findById(param);
				ArrayList<String> list = new ArrayList<String>();
				for (Student student : listStudents) {
					list.add(student.toString());
				}
				
				sendResponse(list);
			}
			
		}
		socket.close();
	}

	private void sendResponse(ArrayList<String> listMessage) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		for (String string : listMessage) {
			dos.writeUTF(string.toString());
			dos.writeUTF(".");
		}
		dos.flush();
		byte[] data = baos.toByteArray();
		packet.setData(data);
		packet.setLength(data.length);
		socket.send(packet);
		
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

	private String getRequest() throws IOException {
		socket.receive(packet);
		ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
		DataInputStream dis = new DataInputStream(bais);
		
		return dis.readUTF();
	}
	
}
