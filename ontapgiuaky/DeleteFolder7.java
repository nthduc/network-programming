package ontapgiuaky;

import java.io.File;

public class DeleteFolder7 {
	public boolean deleteFolder(String path) {
		File fileSource = new File(path);
		if(!fileSource.exists()) return false;
		
		else if(fileSource.isDirectory()) {
			File[] listFiles = fileSource.listFiles();
			for (File file : listFiles) {
				if(file.isFile()) {
					file.delete();
				} else {
					deleteFolder(file.getAbsolutePath());
					file.delete();
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		new DeleteFolder7().deleteFolder("D:\\logs\\Test");
	}
}
