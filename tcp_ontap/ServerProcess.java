package tcp_ontap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

import udp_ontap.Student;

public class ServerProcess extends Thread{
	Socket socket;
	DAO dao;
	BufferedReader netIn;
	PrintWriter netOut;
	boolean userLogined = false;
	String userLogin = "";
	String ip = "127.0.0.1";
	int port = 2000;
	
	
	public ServerProcess(Socket socket) throws IOException {
		this.socket = socket;
		netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		netOut = new PrintWriter(socket.getOutputStream());	
		dao = new DAO();
	}
	
	public void run() {
		netOut.println("System is Ready...");
		String line, command, param;
		
		while(true) {
			try {
				line = netIn.readLine();
			
			if(line.equalsIgnoreCase("") || line.equalsIgnoreCase("QUIT")) {
				break;
			}
			
			StringTokenizer token = new StringTokenizer(line);
			command = token.nextToken();
			if(token.hasMoreElements()) {
				param = token.nextToken();
			} else {
				netOut.print("param not data");
				continue;
			}
			
			if(command.equalsIgnoreCase("USER")) {
				if(dao.checkUsername(param)) {
					userLogin = param;
					netOut.println("OK" + param);
					
				} else {
					netOut.println("User not exits");
				}
			} else if(command.equalsIgnoreCase("PASS")) {
				if(userLogin == "") {
					netOut.println("User not exists");
					continue;
				}
				
				if(dao.login(userLogin, param)) {
					netOut.println("Hello" + userLogin);
					userLogined = true;
					break;
				} else {
					netOut.println("Login error");
				}
			} else {
				netOut.println("Error command not exits");
			}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
		
		ArrayList<Student> listStudent = new ArrayList<Student>();
		while(userLogined) {
			try {
				line = netIn.readLine().trim();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			if(line == null || line.equalsIgnoreCase("QUIT")) {
				break;
			}
			StringTokenizer token = new StringTokenizer(line);
			command = token.nextToken();
			if(token.hasMoreElements()) {
				param = token.nextToken();
			} else {
				netOut.println("Param not data");
				continue;
			}
			
			if(command.equalsIgnoreCase("FIND_ID")) {
				listStudent = dao.findById(param);
			}
			response(listStudent);
		}
		try {
			socket.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	private void response(ArrayList<Student> listStudent) {
		for(Student s : listStudent) {
			netOut.println(s.toString());
		}
		
	}
}
