package demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopyUsingStream {
	
	public void copyFileUsingStream(String sourceFile, String destFile) throws IOException {
		InputStream fis = null;
		OutputStream fos = null;
		
		
		try {
			fis = new FileInputStream(sourceFile);
			fos = new FileOutputStream(destFile);
			
			byte[] buffer = new byte[1024];
			int data;
			while((data = fis.read(buffer)) > 0) {
				fos.write(buffer,0,data	);
			}
			
		} catch (Exception e) {
			
		} finally {
			fis.close();
			fos.close();
		}
		
	}
	
	public static void main(String[] args) {
		
	}
}
