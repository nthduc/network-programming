package fileclass;

import java.io.File;

public class FindFile {
	
	public void findFile(String path , String pattern) {
		String keyword = "";
		File file = new File(path);
		
		if(!file.exists()) return;
		//Check if File
		if(file.isFile()) {
			// check * first
			if(pattern.charAt(0) == '*') {
				// cắt phần tử đầu tiên
				keyword = pattern.substring(1);
				if(file.getName().endsWith(keyword)) {
					System.out.println(file.getAbsolutePath());
				}
				//check * last
			} else if(pattern.charAt(pattern.length()-1) == '*') {
				keyword = pattern.substring(0, pattern.length()- 1);
				if(file.getName().startsWith(keyword)) {
					System.out.println(file.getAbsolutePath());
				}
			} else {
				char[] patternArray = pattern.toCharArray();
				int position = 0;
				String start = "";
				String end = "";
				
				for(int i = 0; i < patternArray.length; i++) {
					if(patternArray[i] == '*') {
						position = i;
					}
				}
				
				start = pattern.substring(0,position);
				end = pattern.substring(position+1, pattern.length());
				if(file.getName().startsWith(start)&& file.getName().endsWith(end)) {
					System.out.println(file.getAbsolutePath());
				}
				
			}
		
		}
		//check if Folder
		else if(file.isDirectory()) {
			File[] listFiles = file.listFiles();
			for(File f: listFiles) {
				findFile(f.getAbsolutePath(), pattern);
			}
		}
	}
	public static void main(String[] args) {
		String path = "D:\\logs\\LTM";
		String pattern = "aa*.txt";
		new FindFile().findFile(path, pattern);
		// Solution 2
		String yourName = "Nguyen*Duc";
		String[] stringArr = yourName.split("*");
		for (String str : stringArr) {
			System.out.println(str);
		}
	}
}
