package week1;

import java.io.File;

public class Task2s {
	/*
	 * Hiện thực hàm boolean findFirst(String path, String pattern) tìm và hiển thị
	 * đường dẫn đầy đủ file/folder chỉ định bởi path có chứa chuỗi quy định bởi
	 * pattern; trả về true nếu tìm thấy, false nếu không tìm thấy
	 */
	public boolean find(String path, String filePattern) {
		File file = new File(path);
		String[] arr = file.list();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(filePattern)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Task2s ts2 = new Task2s();
		String path = "D:\\logs";
		String filePattern = "a";
		System.out.println(ts2.find(path, filePattern));
	}
}
