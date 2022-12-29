package rmi_ontap_2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SearchImpl extends UnicastRemoteObject implements ISearch{
	private Connection conn;
	String name = "";
	
	public SearchImpl(String name) throws RemoteException {
		this.name = name;
		String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
		String sql = "jdbc:ucanacess://d:/abc.accdb";
		
		try {
			Class.forName(driver);
			this.conn = DriverManager.getConnection(sql);
			
		} catch (Exception e) {
			System.out.println("Khong the ket noi");
			throw new RemoteException(e.getMessage());
		}
	}

	@Override
	public boolean checkUserName(String param) throws RemoteException {
		try {
			PreparedStatement ps = conn.prepareStatement("Select * from user where username = ?");
			ps.setString(1, param);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			throw new RemoteException();
		}
		
		
	}

	@Override
	public boolean login(String userLogin, String param) throws RemoteException {
		
		try {
			PreparedStatement ps = conn.prepareStatement("Select * from user where username = ? and password = ?");
			ps.setString(1, userLogin);
			ps.setString(2,param);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			throw new RemoteException();
		}
	}

	@Override
	public ArrayList<Student> findByID(String param) throws RemoteException {
		try {
			ArrayList<Student> list = new ArrayList<Student>();
			int idStr = Integer.parseInt(param);
			PreparedStatement ps = conn.prepareStatement("Select * from student where ID = ?");
			ps.setInt(1, idStr);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Student(rs.getInt(idStr), rs.getString("NAME"), rs.getInt("AGE"), rs.getDouble("SCORE")));
				
			}
			return list;
		} catch (Exception e) {
			throw new RemoteException();
		}
		
		
	}
}
