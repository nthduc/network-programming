package ontapgk;

import java.io.File;

public class TimKiemThongKeFile {
	
	public void thongKe(String thuMucNgon, String fileDich) {
		File fileSource = new File(thuMucNgon);
		if(!fileSource.exists()) return;
		else {
			int count = 0;
			if(fileSource.isDirectory()) {
				File[] listFiles = fileSource.listFiles();
				for (File file : listFiles) {
					
					if(file.isFile()) {
						
						if(file.getName().endsWith(fileDich)) {
							++count;
							
							System.out.println(file.getAbsolutePath()+ " " + file.getAbsolutePath().length()+ "bytes");
						}
					} else if(file.isDirectory()) {
						thongKe(file.getAbsolutePath(), fileDich);
					}
				}
				
			}
			System.out.println("Số lượng file" + " "+ count);
			
		}
		
		
	}
	
	public static void main(String[] args) {
		String thuMucNgon = "D:\\logs";
		new TimKiemThongKeFile().thongKe(thuMucNgon, "txt");
	}
}
