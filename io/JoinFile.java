package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class JoinFile {
	public void joinFile(String pathFolder, String ext) throws IOException {
		File folder = new File(pathFolder);
		if (!folder.exists())
			return;

		if (folder.isDirectory()) {
			File[] listFile = folder.listFiles();
			FileOutputStream fos = new FileOutputStream(folder.getAbsolutePath() + "joiner." + ext);

			for (int i = 0; i < listFile.length; i++) {
				FileInputStream fis;
				if (i == listFile.length - 1) {
					fis = new FileInputStream(folder.getAbsolutePath() + "\\split_end");
				} else {
					fis = new FileInputStream(folder.getAbsolutePath() + "\\split" + (i + 1));
				}
				int byteReaded;
				while ((byteReaded = fis.read()) != -1) {
					fos.write(byteReaded);
				}
				fis.close();
			}
			fos.close();
		}
	}

	public static void main(String[] args) throws IOException {
		String pathFile = "D:\\logs\\LTM\\split";
		String ext = "mp4";
		new JoinFile().joinFile(pathFile, ext);
	}
}
