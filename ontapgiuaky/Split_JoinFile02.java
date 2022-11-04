package ontapgiuaky;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Split_JoinFile02 {
	public void splitFile(String path, long size) throws IOException {
		File fileSource = new File(path);
		if(!fileSource.exists()) return;
		if(fileSource.isFile()) {
			FileInputStream fis  = new FileInputStream(fileSource);
			long totalSize = fileSource.length();
			int countFile = (int) (totalSize / size);
			long remaider = totalSize % size;
			System.out.println(totalSize);
			System.out.println(countFile);
			System.out.println(remaider);
			File newFile = new File(fileSource.getParent() + "\\split");
			newFile.mkdir();
			
			for(int i = 0 ; i < countFile; i++) {
				FileOutputStream fos = new FileOutputStream(fileSource.getParent() + "\\split\\split"+ (i+1));
				for(int j = 0 ; j < size; j++) {
					fos.write(fis.read());
				}
				fos.close();
			}
			if(remaider > 0) {
				FileOutputStream fos = new FileOutputStream(fileSource.getParent() + "\\split\\split_end");
				for(int i = 0 ; i < remaider; i++) {
					fos.write(fis.read());
				}
				fos.close();
			}
		}
	}
	
	public void joinFile(String pathFolder, String ext) throws IOException {
		File folder = new File(pathFolder);
		if(!folder.exists()) return;
		if(folder.isDirectory()) {
			File[] listFiles = folder.listFiles();
			FileOutputStream fos = new FileOutputStream(folder.getParent()+ "\\Joinner" + ext);
			for(int i = 0 ; i < listFiles.length; i++) {
				FileInputStream fis;
				if(i == listFiles.length - 1) {
					fis = new FileInputStream(folder.getParent()+ "split_end");
				} else {
					fis = new FileInputStream(folder.getParent() + "split" + (i+1));
				}
				
				int byteReader;
				while((byteReader = fis.read()) != -1) {
					fos.write(byteReader);
				}
				fis.close();
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		new Split_JoinFile02().splitFile("D:\\logs\\LTM\\video.mp4", 1024*1000);
	}
}
