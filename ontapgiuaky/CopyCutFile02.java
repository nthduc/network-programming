package ontapgiuaky;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyCutFile02 {
	public boolean copyFile(String pathSource, String pathDest, boolean moved) throws IOException {
		File fileSource = new File(pathSource);
		if(!fileSource.exists()) return false;
		else {
			FileInputStream fis = new FileInputStream(pathSource);
			FileOutputStream fos = new FileOutputStream(pathDest);
			byte[] buffer = new byte[1024];
			int byteReaded;
			while((byteReaded = fis.read(buffer)) != -1) {
				fos.write(buffer,0,byteReaded);
			}
			fis.close();
			fos.close();
			if(moved) {
				fileSource.delete();
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		new CopyCutFile02().copyFile("D:\\logs\\video.mp4", "D:\\logs\\video2.mp4", false);
	}
}
