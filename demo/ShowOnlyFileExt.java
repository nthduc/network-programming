package demo;

import java.io.File;
import java.io.FilenameFilter;

public class ShowOnlyFileExt {
	// hiện thị file với phần mở rộng đuôi
	public void dirViewWithExt(String path, String ext) {
		File dir = new File(path);
		if ((!dir.exists()) || !dir.isDirectory())
			return;
		String[] list = dir.list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(ext);
			}
		});
		for (String name : list)
			System.out.println(name);
	}

	public static void main(String[] args) {
		ShowOnlyFileExt showOnlyFileExt = new ShowOnlyFileExt();
		String path = "D:\\SERVER";
		String ext = ".vmdk";
		showOnlyFileExt.dirViewWithExt(path, ext);
	}
}
