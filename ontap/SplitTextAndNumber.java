package ontap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class SplitTextAndNumber {
	public int splitTextAndNumber(String srcFile, String wordFile, String numFile) throws IOException {
		File src = new File(srcFile);
		if (!src.exists())
			return -1;
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(src), "UTF-16"));
		PrintWriter wordWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(wordFile), "UTF-8"));
		PrintWriter numWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(numFile), "UTF-8"));
		String line;
		int count = 0;
		while ((line = reader.readLine()) != null) {
			String numberLine = "";
			String wordLine = "";
			System.out.println(line);
			String[] split = line.split(" ");
			for (int i = 0; i < split.length; i++) {
				if (isNumeric(split[i])) {
					numberLine += split[i] + " ";
					count++;
				} else {
					wordLine += split[i] + " ";
				}
			}
			wordWriter.println(wordLine);
			numWriter.println(numberLine);
		}
		reader.close();
		wordWriter.close();
		numWriter.close();
		return count;
	}

	public boolean isNumeric(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public static void main(String[] args) throws IOException {
		String srcFile="D:\\TestLapTrinhMang\\testSplitNumberAndFile.txt";
		String wordFile="D:\\TestLapTrinhMang\\wordFile.txt";
		String numFile="D:\\TestLapTrinhMang\\numFile.txt";
		BT2 bt2 = new BT2();
		System.out.println(bt2.splitTextAndNumber(srcFile, wordFile, numFile));
		
	}