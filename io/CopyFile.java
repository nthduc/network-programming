package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFile {

	public boolean copyFile(String sourceFile, String destFile, boolean moved) throws IOException {
		InputStream fit = null;
		OutputStream fos = null;
		File dest = new File(destFile);

		try {
			fit = new FileInputStream(sourceFile);
			fos = new FileOutputStream(destFile);

			if (dest.exists() && !moved) {
				moved = false;
			}

			int data;
			while ((data = fit.read()) != -1) {
				fos.write(data);
				moved = true;
			}
		} catch (Exception e) {

		} finally {
			fit.close();
			fos.close();
		}
		
		return moved;

	}

	public static void main(String[] args) throws IOException {
		CopyFile copyFile = new CopyFile();
		String sourceFile = "D:\\logs\\LTM\\abcde.txt";
		String destFile = "D:\\logs\\LTM\\New\\xyz.txt";
		copyFile.copyFile(sourceFile, destFile, false);
	}
}
