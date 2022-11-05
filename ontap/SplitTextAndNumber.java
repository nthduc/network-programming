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
	public int splitTextAndNumber(String srcFile, String wordFile, String number) throws IOException, FileNotFoundException {
		File src = new File(srcFile);
		if(!src.exists()) return -1;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(src),"UTF-8"));
		PrintWriter wordText = new PrintWriter(new OutputStreamWriter(new FileOutputStream(src),"UTF-8"));
		PrintWriter numberWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(number),"UTF-8"));
		String line;
		int count = 0;
		while((line = br.readLine()) != null) {
			String numberLine = "";
			String wordLine = "";
			System.out.println(line);
			String[] split = line.split(" ");
			for(int i = 0 ; i < split.length; i++) {
				if(isNumberic(split[i])) {
					numberLine += split[i] + " ";
					count++;
				} else {
					wordLine += split[i] + " ";
				}
			}
			wordText.println(wordLine);
			numberWriter.println(number)
		}
		
	}

	private boolean isNumberic(String string) {
		// TODO Auto-generated method stub
		return false;
	}
}
