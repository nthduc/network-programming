package fileclass;

import java.io.File;

public class _DeleteAll {
	
	public void DeleteFind(String path, String exts) {
		File fileSource = new File(path);
		
		// check not exists
		if(!fileSource.exists()) {
			System.out.println("Path file not exist");
		}
		
		File[] listFile = fileSource.listFiles();
		for(int i = 0 ; i < listFile.length; i++) {
			if(listFile[i].isFile() && listFile[i].getPath().endsWith(exts)) {
				listFile[i].delete();
			}
			if(listFile[i].isDirectory()) {
				DeleteFind(listFile[i].getPath(), exts);
			}
		}
		
		System.out.println("Delete Successfully !");
	}
	
	public void DeleteAll(String path, String[] exts) {
		for(int i = 0 ; i < exts.length; i++) {
			DeleteFind(path, exts[i]);
		}
	}
	
	
	public static void main(String[] args) {
		_DeleteAll deleteAll = new _DeleteAll();
		String path = "D:\\logs\\LTM";
		String[] exts = {"txt"};
		deleteAll.DeleteAll(path, exts);
	}
}
