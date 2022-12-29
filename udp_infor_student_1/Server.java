package udp_infor_student_1;

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
		if(list.size() == 0) {
			return "Không tìm thấy !";
		}
		for (Student student : list) {
			result+= student.toString();
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		Student st1 = new Student("abc", 20, 9.0);
		Student st2 = new Student("xyz", 19, 8.0);
		
		ArrayList<Student> listStudents = new ArrayList<Student>();
		listStudents.add(st1);
		listStudents.add(st2);
		
		
		int port = 2222;
		byte[] data = new byte[1024];
		DatagramPacket packet = new DatagramPacket(data, data.length);
		DatagramSocket socket = new DatagramSocket(port);
		
		while(true) {
			packet.setData(data);
			packet.setLength(data.length);
			socket.receive(packet);
			String receive = new String(packet.getData(),0,packet.getLength());
			if(receive.equalsIgnoreCase("end")) {
				break;
			}
			
			StringTokenizer token = new StringTokenizer(receive, " ");
			String command = token.nextToken();
			String valueString = token.nextToken();
			if(command.equalsIgnoreCase("FindByName")) {
				ArrayList<Student> listResult = findByName(listStudents, valueString);
				receive = toString(listResult);
			} else {
				receive  = "Echo" + receive;
			}
			
			byte[] dataSend = receive.getBytes();
			packet.setData(dataSend);
			packet.setLength(dataSend.length);
			socket.send(packet);
			
		}
		socket.close();
		
	}
}
