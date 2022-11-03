package ontapgiuaky;

import java.io.File;

public class FindAll_Ext08 {
	public void findAll(String path, String...exts) {
		File fileSource = new File(path);
		if(!fileSource.exists()) return;
		else {
			if(fileSource.isDirectory()) {
				File[] listFiles = fileSource.listFiles();
				for (File file : listFiles) {
					if(file.isFile()) {
						for(String ext: exts) {
							if(file.getName().endsWith(ext)) {
								System.out.println(file.getAbsolutePath());
							}
						}
					} else if(file.isDirectory()) {
						findAll(file.getAbsolutePath(), exts);
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		new FindAll_Ext08().findAll("D:\\logs", "txt");
	}
}
