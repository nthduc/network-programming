package ontapgiuaky;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Pack_Unpack01 {
	public void pack(String sFolder, String destFile) throws IOException {
		File folder = new File(sFolder);
		if(!folder.exists()) return;
		
		File[] listFiles = folder.listFiles();
		int size = listFiles.length;
		RandomAccessFile raf = new RandomAccessFile(destFile, "rw");
		raf.writeInt(size);
		ArrayList<Long> positions = new ArrayList<>();
		for (File file : listFiles) {
			raf.writeUTF(file.getName());
			raf.writeLong(file.length());
			positions.add(raf.getFilePointer());
			raf.writeLong(0);
		}
		
		int i = 0;
		for (File file : listFiles) {
			long positionFile = raf.getFilePointer();
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			int byteReader;
			byte[] buffer = new byte[1024];
			while((byteReader = bis.read(buffer)) != -1) {
				raf.write(buffer,0,byteReader);
			}
			bis.close();
			raf.seek(positions.get(i));
			raf.writeLong(positionFile);
			raf.seek(raf.length());
			i++;
			
		}
		
		raf.close();
		
		
	}
	
	public void unpack(String sFile, String fileName) throws IOException {
		File sourceFile = new File(sFile);
		if(!sourceFile.exists()) return;
		String parent = sourceFile.getParent();
		
		RandomAccessFile raf = new RandomAccessFile(sFile, "rw");
		int totalFile = raf.readInt();
		for(int i = 0 ; i < totalFile; i++) {
			String name = raf.readUTF();
			Long size = raf.readLong();
			Long position = raf.readLong();
			
			if(fileName.equals(name)) {
				raf.seek(position);
				
				FileOutputStream fos = new FileOutputStream(parent + "unpack"+ fileName);
				for(int j = 0 ; j <= size; j++) {
					fos.write(raf.read());
				}
				fos.close();
				break;
			}
		}
		
		raf.close();
		
	}
}
		