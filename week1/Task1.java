package week1;

import java.io.File;

public class Task1 {
	/*
	 * Hiện thực hàm boolean delete(String path) xóa tất cả những gì có thể trong
	 * thư mục được chỉ định bởi path; trả về true nếu xóa thành công, false nếu xóa
	 * không thành công
	 */
	public boolean delete(String path) {
		File fileSource = new File(path);

		if (!(fileSource.exists()))
			return false;

		if (fileSource.isFile()) {
			fileSource.delete();
		} else if (fileSource.isDirectory()) {
			File[] listFile = fileSource.listFiles();

			for (File file : listFile) {
				if (file.isFile())
					file.delete();
				else if (file.isDirectory()) {
					delete(file.getAbsolutePath());
					file.delete();
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Task1 d1 = new Task1();
		String path = "D:\\logs\\LTM";
		System.out.println(d1.delete(path));
	}
}
