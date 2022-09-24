package week1;

import java.io.File;

public class Task4 {
	/*
	 * Bài 4 (Advanced): Viết hàm tính và hiển thị dung lượng theo cấu trúc cây
	 * thư mục void dirStat(String path)
	 */
	public void dirStat(String path, int level) {
		File file = new File(path);
		File[] listFiles = file.listFiles();
		
		for(int i = 0 ; i < listFiles.length; i++) {
			if(listFiles[i].isFile()) {
				System.out.println();
				print(listFiles[i].getPath(), level);
			}
		}
		
		for(int i = 0; i < listFiles.length; i++) {
			if(listFiles[i].isDirectory()) {
				System.out.println();
				print(path, level);
				dirStat(listFiles[i].getPath(), level + 1);
			}
		}
	}
	
	private void print(String path , int level) {
		File file = new File(path);
		if(file.isFile()) {
			for(int i = 0; i < level; i++) {
				System.out.print("|---|");
			}
			System.out.print("|---| "+ file.length());
		} else if(file.isDirectory()) {
			for(int i = 0; i < level; i++) {
				System.out.print("|---|");
			}
			System.out.print("|---| "+ file.length());
		}
		
	}
	public static void main(String[] args) {
		Task4 t4 = new Task4();
		String path = "D:\\logs\\LTM";
		int level = 2;
		t4.dirStat(path, level);
	}
}
