package revisemiddle;

import java.io.File;

public class DeleteFolder5 {
	public boolean deleteFolder(String path) {
		File fileSource = new File(path);
		if(!fileSource.exists()) return false;
		else if(fileSource.isDirectory()) {
			File[] listFiles = fileSource.listFiles();
			for (File file : listFiles) {
				if(file.isFile()) {
					file.delete();
				}else {
					deleteFolder(file.getAbsolutePath());
					file.delete();
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		new DeleteFolder5().deleteFolder("D:\\logs\\Test");
	}
}
