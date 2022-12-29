package udp_infor_student_5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

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
		Student s1 = new Student("abc", 20, 8.0);
		Student s2 = new Student("xyz", 19, 7.0);
		
		ArrayList<Student> listStudents = new ArrayList<Student>();
		listStudents.add(s1);
		listStudents.add(s2);
		
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
			
			byte[] dataSend = receive.getBytes();
			packet.setData(dataSend);
			packet.setLength(dataSend.length);
			socket.send(packet);
		}
		
		socket.close();
	}
}
