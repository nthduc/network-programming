package rmi_revise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Client {
	public static void main(String[] args) throws SQLException, IOException, NotBoundException {
		Registry reg = LocateRegistry.getRegistry(12345);
		ISearch isearch = (ISearch) reg.lookup("IS");
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		String line;
		String command, param;
		String userName = null;
		boolean isLogin = false;
		
		while(!isLogin) {
			line = userIn.readLine();
			if(line == null || line.equalsIgnoreCase("QUIT")) {
				break;
			}
			
			StringTokenizer token = new StringTokenizer(line);
			command = token.nextToken();
			if(token.hasMoreElements()) {
				param = token.nextToken();
			} else {
				System.out.println("Param not data");
				continue;
			}
			
			switch (command) {
			case "USER": {
				
				if(isearch.checkUserName(param)) {
					response("OK Username");
					userName = param;
				} else {
					response("Error username");
				}
				break;
			}
			
			case "PASS" :{
				if(userName == null) {
					response("User name err");
				} else {
					if(isearch.login(userName, param)) {
						response("OK login");
						isLogin = true;
					} else {
						response("ERR login");
					}
				}
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + command);
			}
		}
		
		ArrayList<Student> list = new ArrayList<Student>();
		while(isLogin) {
			list.clear();
			line = userIn.readLine();
			
			if(line.equals("") || line.equalsIgnoreCase("QUIT")) break;
			StringTokenizer token = new StringTokenizer(line);
			command = token.nextToken();
			if(token.hasMoreElements()) {
				param = token.nextToken();
			} else {
				response("param not data");
				continue;
			}
			
			switch (command) {
			case "FIND_ID": {
				list = isearch.findById(param);
				break;
			
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + command);
			}
		}
	}

	private static void response(String string) {
		System.out.println(string);
		
	}
}
