package tcp_upload_transport_4;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Transport extends Thread{
	Socket s;
	DataInputStream dis;
	DataOutputStream dos;
	String saveServerDirDefault = "upload";
	String saveClientDirDefault = "download";
	
	public Transport(Socket s) throws IOException {
		this.s = s;
		dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream());
	}
	
	public void fileReceive(String fileName, long fileSize) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void fileSend(File f) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
		dos.writeLong(f.length());
		dos.flush();
		int data;
		
		while((data = bis.read()) != -1) {
			dos.write(data);
			dos.flush();
		}
		bis.close();
	}
}
