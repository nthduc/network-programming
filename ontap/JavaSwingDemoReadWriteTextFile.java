package ontap;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class JavaSwingDemoReadWriteTextFile{
	JFrame jr;
	JButton btRead , btWrite;
	JTextArea ta;
	
	public JavaSwingDemoReadWriteTextFile() {
		jr = new JFrame();
		jr.setTitle("Java Swing Read Text File");
		jr.setVisible(true);
		jr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btRead = new JButton("Read");
		btWrite = new JButton("Write");
		ta = new JTextArea(10,50);
		jr.setLayout(new FlowLayout());
		jr.add(btRead);
		jr.add(btWrite);
		jr.add(ta);
		jr.pack();
		
		btWrite.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					save(ta.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(jr, "Save done");
				ta.setText("");
				
			}

			private void save(String text) throws IOException {
				FileOutputStream fos = new FileOutputStream("D:\\logs\\LTM\\file.txt");
				OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
				PrintWriter pw = new PrintWriter(osw,true);
				pw.print(text);
				pw.close();
				
			}
		});
		
		btRead.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ta.setText(loadData());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}

			private String loadData() throws IOException {
				String value = "";
				FileInputStream fis = new FileInputStream("D:\\logs\\LTM\\file.txt");
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				String line;
				
				while((line = br.readLine()) != null){
					value += line + "\n";
				}
				br.close();
				return value;
			}
		});
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new JavaSwingDemoReadWriteTextFile();
	}
}
