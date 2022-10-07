package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class _CopyFile {
	
	public boolean copyFile(String sourceFile, String destFile , boolean moved) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		File dFile;
		File sFile;
		
		try {
			sFile = new File(sourceFile);
			dFile = new File(destFile);
			is = new BufferedInputStream(new FileInputStream(sFile));
			os = new BufferedOutputStream(new FileOutputStream(destFile));
			
			if(dFile.exists() && !moved) {
				moved = false;
			}
			
			if(!(sFile.exists())) {
				moved = false;
			}
			
			byte[] buffer = new byte[1024];
			int lengthRead;
			while((lengthRead = is.read(buffer)) > 0) {
				os.write(buffer, 0 , lengthRead);
				os.flush();
				moved = true;
			}
		} catch (Exception e) {
			
		} finally {
			is.close();
			os.close();
		}
		return moved;
	}
	
	public static void main(String[] args) throws IOException {
		_CopyFile copyFile = new _CopyFile();
		String sourceFile = "D:\\logs\\LTM\\abcde.txt";
		String destFile = "D:\\logs\\LTM\\New\\xyz.txt";
		copyFile.copyFile(sourceFile, destFile, false);
	}
}
