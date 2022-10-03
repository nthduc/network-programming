package demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy {
	public void copy(String source, String dest) throws IOException {
		InputStream fit = new FileInputStream(source);
		OutputStream fos = new FileOutputStream(dest);
		
		int data;
		while((data = fit.read()) != -1) 
		fos.write(data);
		
		fit.close();
		fos.close();
	}
	
	public static void main(String[] args) throws IOException {
		FileCopy fileCopy = new FileCopy();
		String source = "D:\\logs\\LTM\\xyz.txt";
		String dest = "D:\\logs\\LTM\\New folder\\abc.txt";
		fileCopy.copy(source, dest);
		
	}
}
