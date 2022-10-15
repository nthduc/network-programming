package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SplitFIle {

	public void splitFile(String source, long size) throws IOException {
		File fileSource = new File(source);
		if (!fileSource.exists())
			return;

		if (fileSource.isFile()) {
			FileInputStream fis = new FileInputStream(fileSource);
			long totalSize = fileSource.length();
			int countFile = (int) (totalSize / size);
			long remainder = totalSize % size;
			System.out.println(countFile);
			System.out.println(size);
			System.out.println(remainder);

			File newFolder = new File(fileSource.getParent() + "\\split");
			newFolder.mkdir();

			for (int i = 0; i < countFile; i++) {
				FileOutputStream fos = new FileOutputStream(fileSource.getParent() + "\\split\\split" + (i + 1));
				for (int j = 0; j < size; j++) {
					fos.write(fis.read());
				}
				fos.close();
			}

			if (remainder > 0) {
				FileOutputStream fos = new FileOutputStream(fileSource.getParent() + "\\split\\split_end");
				for (int i = 0; i < remainder; i++) {
					fos.write(fis.read());
				}
				fos.close();
			}

			fis.close();
		}
	}

	public static void main(String[] args) throws IOException {
		String source = "D:\\logs\\LTM\\video.mp4";
		long size = 1024 * 1000;
		new SplitFIle().splitFile(source, size);
	}
}
