package udp_infor_student_3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Server {
	
	public static ArrayList<Student> findByName(ArrayList<Student> list, String name){
		ArrayList<Student> listResult = new ArrayList<Student>();
		
		for (Student student : list) {
			if(name == student.getName()) {
				listResult.add(student);
			}
		}
		return listResult;
	}
	
	public static String toString(ArrayList<Student> list) {
		String result = "";
		if(list.size() == 0) return "Khong tim thay";
		
		for (Student student : list) {
			result += student.toString();
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		Student st1 = new Student("abc", 7, 9.0);
		Student st2 = new Student("xyz", 9, 7.0);
		
		ArrayList<Student> listStudents = new ArrayList<Student>();
		listStudents.add(st1);
		listStudents.add(st2);
		
		int port = 2222;
		byte[] data = new byte[1024];
		DatagramSocket socket = new DatagramSocket(port);
		DatagramPacket packet = new DatagramPacket(data, data.length);
		
		while(true) {
			packet.setData(data);
			packet.setLength(data.length);
			socket.receive(packet);
			String receive = new String(packet.getData(),0,packet.getLength());
			if(receive.equalsIgnoreCase("end")) break;
			
			StringTokenizer token = new StringTokenizer(receive," ");
			String command = token.nextToken();
			String valueString = token.nextToken();
			
			if(command.equalsIgnoreCase("FindByName")) {
				ArrayList<Student> listResult = findByName(listStudents, valueString);
				receive = toString(listResult); 
			} else {
				receive = "Echo" + receive;
			}
			socket.close();
		}
	}
}
