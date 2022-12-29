package tcp_ontap_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ServerProcess extends Thread{
	Socket socket;
	BufferedReader netIn;
	PrintWriter netOut;
	boolean isLogin = false;
	String userLogin = "";
	String ip = "127.0.0.1";
	int port = 2000;
	DAO dao;
	
	public ServerProcess(Socket socket) throws IOException {
		this.socket = socket;
		netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		netOut = new PrintWriter(socket.getOutputStream());
		dao = new DAO();
	}
	
	public void run() {
		netOut.println("System is Ready ...");
		String line, command, param;
		
		while(true) {
			line = netIn.readLine();
			
			if(line.equals("") || line.equalsIgnoreCase("QUIT")) {
				netOut.println("good bye");
				break;
			}
			
			StringTokenizer token = new StringTokenizer(line);
			command = token.nextToken();
			if(token.hasMoreElements()) {
				param = token.nextToken();
			} else {
				netOut.println("param not data");
				continue;
			}
			
			if(command.equalsIgnoreCase("USER")) {
				if(dao.checkUserName(param)) {
					userLogin = param;
					netOut.println("OK" + param);
					
				} else {
					netOut.println("User not exits");
				}
			} else if(command.equalsIgnoreCase("PASS")) {
				if(userLogin == "") {
					netOut.println("User not found");
					continue;
				} else if(dao.login(userLogin, param)) {
					netOut.println("Hello" + userLogin);
					isLogin = true;
					
				}else {
					netOut.println("Login Failed");
				}
			} else {
				netOut.println("Command not found");
			}
			
		}
		
		while(isLogin) {
			try {
				line = netIn.readLine();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			if(line.equals("" )|| line.equalsIgnoreCase("QUIT")) {
				break;
			}
			ArrayList<Student> listStudents = new ArrayList<Student>();
			StringTokenizer token = new StringTokenizer(line);
			command = token.nextToken();
			
			if(token.hasMoreTokens()) {
				param = token.nextToken();
				
			} else {
				netOut.println("many params");
				continue;
			}
			
			if(command.equalsIgnoreCase("FIND_ID")) {
				listStudents = dao.findByID(param);
				
				response(listStudents);
			}
		}
	}

	private void response(ArrayList<Student> listStudents) {
		for (Student student : listStudents) {
			netOut.print(student.toString());
		}
		
	}
	
}
