package revisemiddle;

import java.io.File;

public class DeleteFolder2 {
	public boolean deleteFolder(String path) {
		File fileSource = new File(path);
		if(!fileSource.exists()) return false;
		else if(fileSource.isDirectory()) {
			File[] lisFiles = fileSource.listFiles();
			for (File file : lisFiles) {
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
		new DeleteFolder().deleteFolder("D:\\logs\\Test");
	}
}
