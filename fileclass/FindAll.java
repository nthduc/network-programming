package fileclass;

import java.io.File;

public class FindAll {
	
	public boolean findFirst(String path , String pattern) {
		File fileSource = new File(path);
		File[] listFile = fileSource.listFiles();
		
		for(int i = 0 ; i < listFile.length; i++) {
			if(listFile[i].getName().contains(pattern)) {
				return true;
			}
		}
		return false;
	}
	
	public void findAll(String path , String pattern) {
		File fileSource = new File(path);
		
		//check not exists
		if(!fileSource.exists()) {
			System.out.println("Path File Not Exists");
		}
		
		File[] listFile = fileSource.listFiles();
		for(int i = 0 ; i < listFile.length; i++) {
			if(listFile[i].isFile() && listFile[i].getName().endsWith(pattern)) {
				System.out.println(listFile[i].getName());
			}
			else if(listFile[i].isDirectory()) {
				findAll(listFile[i].getAbsolutePath(), pattern);
			}
		}
	}
	
	
	public static void main(String[] args) {
		FindAll fa = new FindAll();
		String path = "D:\\logs\\LTM";
		String pattern = "abc.xyz";
		fa.findAll(path, pattern);
		
	}
}
