package revisemiddle;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Pack_Unpack04 {
	public void pack(String sFolder, String destFile) throws IOException {
		File folder = new File(sFolder);
		if(!folder.exists()) return;
		File[] lisFiles = folder.listFiles();
		int size = lisFiles.length;
		RandomAccessFile raf = new RandomAccessFile(destFile, "rw");
		raf.writeInt(size);
		ArrayList<Long> positions = new ArrayList<Long>();
		for (File file : lisFiles) {
			raf.writeUTF(file.getName());
			raf.writeLong(file.length());
			positions.add(raf.getFilePointer());
			raf.writeLong(0);
		}
		int i = 0;
		for(File file : lisFiles) {
			Long positionFile = raf.getFilePointer();
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[1024];
			int byteReader;
			while((byteReader = bis.read(buffer)) != -1) {
				raf.write(buffer,0,byteReader);
			}
			bis.close();
			raf.seek(positions.get(i));
			raf.writeLong(positionFile);
			raf.seek(raf.length());
			i++;
		
		}
	}
	
	public void unpack(String sFile, String fileName) throws IOException {
		File fileSource = new File(sFile);
		if(!fileSource.exists()) return;
		
		String parent =fileSource.getParent();
		RandomAccessFile raf = new RandomAccessFile(fileSource, "rw");
		int totalFile = raf.readInt();
		
		for(int i = 0 ; i < totalFile; i++) {
			String name = raf.readUTF();
			Long size = raf.readLong();
			Long positon = raf.readLong();
			
			if(fileName.equals(name)) {
				raf.seek(positon);
				FileOutputStream fos = new FileOutputStream(parent+ "unpack"+ fileName);
				
				for(int j = 0 ; j < totalFile; j++) {
					fos.write(raf.read());
				}
				fos.close();
				break;
			}
			raf.close();
		}
		
	}
}
