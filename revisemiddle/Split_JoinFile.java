package revisemiddle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Split_JoinFile {
	public void splitFile(String path, long size) throws IOException {
		File fileSource = new File(path);
		if(!fileSource.exists()) return;
		if(fileSource.isFile()) {
			FileInputStream fis = new FileInputStream(fileSource);
			long totalSize = fileSource.length();
			int countFile = (int) (totalSize / size);
			long remaider = totalSize % size;
			System.out.println(countFile);
			System.out.println(totalSize);
			System.out.println(size);
			System.out.println(remaider);
			File newFolder = new File(fileSource.getParent()+ "\\split");
			newFolder.mkdir();
			for(int i = 0 ; i < countFile; i++) {
				FileOutputStream fos = new FileOutputStream(fileSource.getParent()+ "\\split\\split" + (i+1));
				for(int j = 0; j < size; j++) {
					fos.write(fis.read());
				}
				fos.close();
			}
			if(remaider > 0) {
				FileOutputStream fos = new FileOutputStream(fileSource.getParent()+ "\\split\\split_end");
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
			File[] listFile = folder.listFiles();
			FileOutputStream fos = new FileOutputStream(folder.getParent() + " Joiner"+ ext);
			for(int i = 0 ; i < listFile.length; i++) {
				FileInputStream fis;
				if(i == listFile.length - 1) {
					fis = new FileInputStream(folder.getParent() + "\\split_end");
					
				} else {
					fis = new FileInputStream(folder.getParent()+ "\\split" + (i+1));
				}
				int byteReaded;
				while((byteReaded = fis.read()) != -1) {
					fos.write(byteReaded);
				}
				fis.close();
			}
			
		}
	}
	public static void main(String[] args) throws IOException {
		new Split_JoinFile().splitFile("D:\\logs\\LTM\\video.mp4", 1024*1000);
//		new Split_JoinFile().joinFile("D:\\logs\\split", "mp4");
	}
}
