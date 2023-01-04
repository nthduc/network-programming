package udp_revise;

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
	DAO dao;
	byte[] buffer = new byte[102400];
	int port = 2000;
	String userLogin;
	
	public Server() throws SocketException {
		packet = new DatagramPacket(buffer, buffer.length);
		socket = new DatagramSocket(port);
		dao = new DAO();
	}
	
	public void run() throws IOException {
		boolean isLogin = false;
		String command, param;
		String line;
		
		while(true) {
			line = getRequest();
			if(line.equals("") || line.equalsIgnoreCase("QUIT")) {
				break;
			}
			StringTokenizer token = new StringTokenizer(line);
			command = token.nextToken().toUpperCase();
			
			if(token.hasMoreElements()) {
				param = token.nextToken();
			} else {
				sendResponse("Params not none");
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
					sendResponse("Error ! User name none");
					continue;
				}
				if(dao.login(userLogin, param)) {
					ArrayList<String> list = new ArrayList<String>();
					list.add("OK Hello" + userLogin);
					list.add("Login SuccessFully");
					isLogin = true;
					sendResponse(list);
					break;
				} else {
					sendResponse("Login failed");
				}
			} else {
				sendResponse("Error command not exits");
			}
			
		}
		
		while(isLogin) {
			line = getRequest();
			if(line.equals("")|| line.equalsIgnoreCase("QUIT")) {
				break;
			}
			
			StringTokenizer token = new StringTokenizer(line);
			command = token.nextToken();
			ArrayList<Student> listStudents = new ArrayList<Student>();
			if(token.hasMoreElements()) {
				param = token.nextToken();
			} else {
				sendResponse("Param not none");
				continue;
			}
			
			if(command.equalsIgnoreCase("FIND_ID")) {
				listStudents = dao.findById(param);
				ArrayList<String> list = new ArrayList<String>();
				for(Student student : listStudents) {
					list.add(student.toString());
				}
				sendResponse(list);
			}
		}
		socket.close();
	}

	private void sendResponse(ArrayList<String> lines) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		for (String line : lines) {
			dos.writeUTF(line.toString());
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
