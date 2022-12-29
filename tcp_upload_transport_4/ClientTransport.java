package tcp_upload_transport_4;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class ClientTransport {
	public static void main(String[] args) throws UnknownHostException, IOException {
		int errorCode = 1;
		Socket socket = new Socket("127.0.0.1",ServerTransport.PORT);
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		System.out.println(dis.readUTF());
		
		while(true) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String command = br.readLine();
			dos.writeUTF(command);
			dos.flush();
			
			StringTokenizer token = new StringTokenizer(command + " ");
			String key = token.nextToken().toUpperCase();
			switch (key) {
			case "SEND": {
				String pathDirectoryUploadAtClient = dis.readUTF();
				if(pathDirectoryUploadAtClient.equalsIgnoreCase("-11")) {
					System.out.println("To many params");
					break;
				}
				
				String sf = token.nextToken();
				File fileSource = new File(pathDirectoryUploadAtClient + File.separator + sf);
				if(!fileSource.exists()) {
					System.out.println("File khong ton tai");
					break;
				}
				
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileSource));
				dos.writeLong(fileSource.length());
				dos.flush();
				errorCode = dis.read();
				if(errorCode == 0) {
					int data;
					while((data = dis.read()) != -1) {
						dos.write(data);
						dos.flush();
					}
					bis.close();
					break;
				}
				
			}
			
			case "GET": {
				String pathDirectoryatServer = dis.readUTF();
				if(pathDirectoryatServer.equalsIgnoreCase("-1")) {
					System.out.println("many params");
					break;
				}
				
				String sourceFile = token.nextToken();
				String valueString = token.nextToken();
				File df = new File(pathDirectoryatServer + File.separator + sourceFile);
				
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(df));
				long fileSize = dis.readLong();
				for (int i = 0; i < fileSize; i++) {
					bos.write(dis.read());
					bos.flush();
				}
				bos.close();
				break;
				
				
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + key);
			}
		}
	}
}
