package tcp_upload_transport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Transport extends Thread{
	Socket s;
	DataInputStream dis;
	DataOutputStream dos;
	public String saveServerDirDefault = "upload";
	public String saveClientDirDefault = "download";
	String message = "";
	
	public Transport(Socket s) throws IOException{
		this.s = s;
		dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream());
	}
	
	
	
	public void fileReceive(String fileName, long fileSize) throws IOException {
		File f = new File(saveServerDirDefault + File.separator + fileName);
		BufferedOutputStream bos;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(f));
			dos.writeInt(0);
			dos.flush();
			for (int i = 0; i < fileSize; i++) {
				bos.write(dis.read());
				bos.flush();
			}
			bos.close();
		} catch (Exception e) {
			
		}
		
	}
	
	public void sendFile(File sf) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sf));
		dos.writeLong(sf.length());
		dos.flush();
		int data;
		
		while((data = bis.read()) != -1) {
			dos.write(data);
			dos.flush();
		}
		bis.close();
		
	}
}
