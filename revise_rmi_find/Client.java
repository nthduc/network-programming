package revise_rmi_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Client {
	public void printResult(ArrayList<Student> list) {
		if(list.size() == 0) {
			System.out.println("Không tìm thấy sinh viên nào");
		} else {
			for (Student student : list) {
				System.out.println(student.toString());
			}
		}
	}
	
	public void runProcess() throws NotBoundException, IOException {
		Registry reg = LocateRegistry.getRegistry("127.0.0.1",2222);
		IFind ifind = (IFind) reg.lookup("find");
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String line = userIn.readLine();
			if(line.equalsIgnoreCase("quit")) break;
			
			StringTokenizer token = new StringTokenizer(line);
			if(token.countTokens() != 2) {
				System.out.println("Sai cú pháp");
			} else {
				
				String command = token.nextToken();
				String param = token.nextToken();
				if(command.equalsIgnoreCase("FindByName")) {
					ArrayList<Student> result = ifind.findByName(param);
					printResult(result);
				} else {
					if(command.equalsIgnoreCase("FindByAge")) {
						ArrayList<Student> result = ifind.findByAge(Integer.parseInt(param));
						printResult(result);
					} else {
						if(command.equalsIgnoreCase("FindByScore")) {
							ArrayList<Student> result = ifind.findByScore(Double.parseDouble(param));
							printResult(result);
						} else {
							System.out.println("Lệnh không bợp lệ");
						}
					}
				}
			}
		}
	}
}
