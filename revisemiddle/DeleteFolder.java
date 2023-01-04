package revisemiddle;

import java.io.File;

public class DeleteFolder {
	public boolean deleteFolder(String path) {
		File fileSource = new File(path);
		if(!fileSource.exists()) return false;
		else if(fileSource.isDirectory()) {
			File[] listFile = fileSource.listFiles();
			for (File file : listFile) {
				if(file.isFile()) {
					file.delete();
				} else {
					deleteFolder(file.getAbsolutePath());
					file.delete(); // nếu muốn xóa file không xóa thư mục thì bỏ phần này
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		new DeleteFolder().deleteFolder("D:\\logs\\Test");
	}
}
