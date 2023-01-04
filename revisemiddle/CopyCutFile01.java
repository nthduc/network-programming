package revisemiddle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyCutFile01 {
	public boolean copyFile(String pathSourceFile, String pathDestFile, boolean moved) throws IOException {
		File fileSource = new File(pathSourceFile);
		if(!fileSource.exists()) return false;
		else {
			FileInputStream fis = new FileInputStream(pathSourceFile);
			FileOutputStream fos = new FileOutputStream(pathDestFile);
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
		new CopyCutFile01().copyFile("D:\\logs\\LTM\\video.mp4", "D:\\logs\\LTM\\videoCopy.mp4", false);
	}
}
