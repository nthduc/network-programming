package week1;

import java.io.File;
import java.io.FilenameFilter;

public class Task2 {
	
	public boolean findFirst(String path, String pattern) {
		File fileSource = new File(path);
		
		if(!(fileSource.exists()) || !(fileSource.isDirectory())) return false;
		
		String [] lists = fileSource.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				
				return name.endsWith(pattern);
			}
		});
		for(String fileName : lists) {
			System.out.println(fileName);
		}
		return true;
		
		
	}
	
	public static void main(String[] args) {
		Task2 t2 = new Task2();
		String path = "D:\\logs";
		String pattern = ".txt";
		System.out.println(t2.findFirst(path, pattern));
	}
}
