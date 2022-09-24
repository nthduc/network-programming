package week1;

import java.io.File;

public class Task3 {
	/*
	 * Bài 3 (Advanced): Viết hàm hiển thị cấu trúc cây của thư mục void
	 * dirTree(String path); dùng các ký tự + - | để vẽ cấu trúc cây. Cần hiển thị
	 * được cấp con hay ngang cấp,…
	 */
	public void dirTree(String path, int level) {
		File file = new File(path);
		File[] listFile = file.listFiles();
		
		for(int i = 0; i < listFile.length; i++) {
			if(listFile[i].isFile()) {
				System.out.println();
				print(listFile[i].getPath(),level);
			}
		}
		
		for(int i = 0; i < listFile.length; i++) {
			if(listFile[i].isDirectory()) {
				System.out.println();
				print(path, level);
				dirTree(listFile[i].getPath(), level + 1);
			}
		}
	}

	private void print(String path, int level) {
		File file = new File(path);
		if(file.isDirectory()) {
			for(int i = 0; i < level; i++) {
				System.out.print("|---|");
			}
			System.out.print("|---| + ");
		} else if (file.isFile()) {
			for(int i = 0; i < level; i++) {
				System.out.print("|--|");
			}
			System.out.print("|---| - ");
		}
		
	}

	public static void main(String[] args) {
		Task3 t3 = new Task3();
		String path = "D:\\logs\\LTM";
		int level = 2;
		t3.dirTree(path, level);
		
	}
}
