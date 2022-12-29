package udp_infor_student_2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Server {
	public static ArrayList<Student> findByName(ArrayList<Student> list, String name){
		ArrayList<Student> listStudents = new ArrayList<Student>();
		for (Student student : list) {
			if(name == student.getName()) {
				listStudents.add(student);
			}
		}
		return listStudents;
	}
	
	public static String toString(ArrayList<Student> list) {
		String result = "";
		if(list.size() == 0) {
			return "khong tim thay";
		}
		
		for (Student student : list) {
			result += student.toString();
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		Student st1 = new Student("abc", 10, 9.0);
		Student st2 = new Student("xyz", 19, 8.0);
		ArrayList<Student> listResult = new ArrayList<Student>();
		listResult.add(st1);
		listResult.add(st2);
		
		int port = 2222;
		byte[] data = new byte[1024];
		DatagramSocket socket = new DatagramSocket(port);
		DatagramPacket packet = new DatagramPacket(data, data.length);
		
		while(true) {
			packet.setData(data);
			packet.setLength(data.length);
			socket.receive(packet);
			String receive = new String(packet.getData(), 0,  packet.getLength());
			if(receive.equalsIgnoreCase("end")) break;
			
			StringTokenizer token = new StringTokenizer(receive, " ");
			String command = token.nextToken();
			String valueString = token.nextToken();
			
			if(command.equalsIgnoreCase("FindByName")) {
				ArrayList<Student> listStudents = findByName(listResult, valueString);
				receive = toString(listStudents);
			} else {
				receive = "Echo" + receive;
			}
			
			byte[] dataSend = receive.getBytes();
			packet.setData(dataSend);
			packet.setLength(dataSend.length);
			socket.send(packet);
			
		}
		socket.close();
	}
}
