package udp_revise;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class StInfoStudent {
	BufferedReader netIn;
	PrintWriter netOut;
	DAO dao;
	String lastUser;
	ArrayList<Student> listStudents = new ArrayList<Student>();
	String ip = "127.0.0.1";
	int port = 2000;
	
	DatagramPacket packet = new DatagramPacket(new byte[0], 0);
	DatagramSocket socket = new DatagramSocket();
	
	public StInfoStudent(Socket socket, DAO dao) throws IOException {
		netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		netOut = new PrintWriter(socket.getOutputStream());
		this.dao = dao;
	}
	
	public void run() {
		boolean isLogin = false;
		String com, param;
		String line;
		
		try {
			while(true) {
				line = netIn.readLine();
				
				if(line == null || line.equalsIgnoreCase("QUIT")) {
					break;
				}
				
				StringTokenizer token = new StringTokenizer(line);
				com = token.nextToken();
				param = token.nextToken();
				
				if("USER".equalsIgnoreCase(com)) {
					if(lastUser == null) {
						netOut.println("user name first");
						continue;
					}
					if(DAO.login(lastUser, param)) {
						netOut.print("Login OK");
						isLogin = true;
						break;
					} else {
						netOut.println("Login error");
					}
				} else {
					netOut.println("error command");
				}
			}
			
			
			while(isLogin) {
				line = netIn.readLine();
				
				if(line == null || line.equalsIgnoreCase("QUIT")) {
					break;
				}
				
				StringTokenizer token = new StringTokenizer(line);
				com = token.nextToken();
				param = token.nextToken();
				
				ArrayList<Student> list = null;
				if(com.equalsIgnoreCase("FIND_ID")) {
					list = DAO.findById(param);
				}
				if(listStudents.isEmpty()) {
					System.out.println("No data");
				}
				
				printListSV(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void printListSV(ArrayList<Student> list) throws IOException {
		packet.setAddress(InetAddress.getByName(ip));
		packet.setPort(port);
		byte[] data;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		
		for(Student student: list) {
			dos.writeInt(student.getId());
			dos.writeUTF(student.getName());
			dos.writeInt(student.getAge());
			dos.writeDouble(student.getScore());
		}
		dos.flush();
		data = baos.toByteArray();
		packet.setData(data);
		packet.setLength(data.length);
		socket.send(packet);
		
	}
}
