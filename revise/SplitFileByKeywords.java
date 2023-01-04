package revise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class SplitFileByKeywords {
	
	public int splitFileByKeywords(String srcFile, String destFile, String[] keywords) throws IOException{
		
		File src = new File(srcFile);
		if(!src.exists()) return -1;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(src), "UTF-8"));
		PrintWriter pr = new PrintWriter(new OutputStreamWriter(new FileOutputStream(destFile), "UTF-16"));
		
		String line;
		int row = 1;
		int count = 0;
		while((line = br.readLine()) != null) {
			for(int i = 0 ; i < keywords.length; i++) {
				if(line.contains(keywords[i])) {
					pr.println(row + ". " + line);
					count++;
					break;
				}
			}
			row++;
		}
		br.close();
		pr.close();
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		SplitFileByKeywords sp = new SplitFileByKeywords();
		String srcFile = "D:\\logs\\srcFile.txt";
		String destFile = "D:\\logs\\destFile.txt";
		String[] keywords = {"ABC","CDE","FGH"};
		System.out.println(sp.splitFileByKeywords(srcFile, destFile, keywords));
	}
}	
