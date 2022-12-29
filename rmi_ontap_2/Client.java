package rmi_ontap_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Client {
	public static void main(String[] args) throws IOException, NotBoundException {
		Registry reg = LocateRegistry.getRegistry(12345);
		ISearch isearch = (ISearch) reg.lookup("IS");
		BufferedReader userIn =  new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		String command, param;
		String userLogin = "";
		boolean isLogin = false;
		
		while(true) {
			line = userIn.readLine();
			
			if(line.equals("") || line.equalsIgnoreCase("QUIT")) {
				break;
			}
			
			StringTokenizer token = new StringTokenizer(line);
			command = token.nextToken();
			if(token.hasMoreElements()) {
				param = token.nextToken();
			} else {
				response("param not data");
				continue;
			}
			
			if(command.equalsIgnoreCase("USER")) {
				if(isearch.checkUserName(param)) {
					response("OK" + param);
					userLogin = param;
					
				}else {
					response("Username not exits");
				}
			} else if(command.equalsIgnoreCase("PASS")) {
				if(userLogin == null) {
					response("User not found");
					continue;
				}
				if(isearch.login(userLogin, param)) {
					response("Login Successfully");
					isLogin = true;
				} else {
					response("login failed");
				}
			}
		}
		ArrayList<Student> listStudents = new ArrayList<Student>();
		while(isLogin) {
			listStudents.clear();
			line = userIn.readLine();
			
			if(line.equals("") || line.equalsIgnoreCase("QUIT")) {
				response("byte");
				break;
			}
			
			StringTokenizer token = new StringTokenizer(line);
			command = token.nextToken();
			if(token.hasMoreElements()) {
				param = token.nextToken();
			} else {
				response("many param");
				continue;
			}
			
			if(command.equalsIgnoreCase("FIND_ID")) {
				listStudents = isearch.findByID(param);
				
			}
		}
	}

	private static void response(String string) {
		System.out.println(string);
		
	}
	
	
}
