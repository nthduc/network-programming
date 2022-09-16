package demo;

import java.io.File;

public class ShowOnlyFile {
	// chỉ hiện danh sách các file thôi
	public void dirView(String path) {
		File dir = new File(path);
		if((!dir.exists()) || (!dir.isDirectory())) return;
		File [] list = dir.listFiles();
		for(File f : list) {
			if(f.isFile())
			System.out.println(f);
		}
	}
	
	public static void main(String[] args) {
		ShowOnlyFile showOnlyFile = new ShowOnlyFile();
		String path = "D:\\SERVER";
		showOnlyFile.dirView(path);
	}
}
