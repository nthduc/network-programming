package fileclass;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

public class Regex {
	
	public void regex(String path, String regex) {
		File fileSource = new File(path);
		
		if(!fileSource.isDirectory()) {
			throw new IllegalArgumentException(fileSource+" is no directory.");
		}
		
		final Pattern p = Pattern.compile(regex);
		
		File[] listFile = fileSource.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				 return p.matcher(fileSource.getName()).matches();
			}
		});
	}
	
	public static void main(String[] args) {
		
	}
}
