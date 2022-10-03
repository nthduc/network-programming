package fileclass;

import java.io.File;

public class DeleteAll {
	
	public void deleteAll(String path, String exts) {
		File fileSource = new File(path);
		
		if(!fileSource.exists()) {
			System.out.println("Path File Not Exist");
		}
		File[] listFile = fileSource.listFiles();
		
		for(int i = 0 ; i < listFile.length; i++) {
			// check if file && endWith exts
			if(listFile[i].isFile() && listFile[i].getPath().endsWith(exts)) {
				listFile[i].delete();
			}
			// if Folder
			if(listFile[i].isDirectory()) {
				for(File file : listFile) {
					if(listFile[i].isFile() && listFile[i].getPath().endsWith(exts)) {
						file.delete();
					} else if(file.isDirectory()) {
						for(int j = 0 ; j < exts.length(); j++)
						deleteAll(file.getAbsolutePath(), exts);
					}	
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
	}
}
