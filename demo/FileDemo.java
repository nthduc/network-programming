package demo;

import java.io.File;

public class FileDemo {
	// hiện thị tất cả file và thư mục trong đường dẫn path
	public void dirView(String path) {
		File dir = new File(path);
		if((!dir.exists()) || (!dir.isDirectory())) return;
			String[] list = dir.list();
			for(String name : list) System.out.println(name);
		
	}
	
	public static void main(String[] args) {
		FileDemo fileDemo = new FileDemo();
		String path = "D:\\SERVER";
		fileDemo.dirView(path);
	}
}
