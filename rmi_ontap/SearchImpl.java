package rmi_ontap;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchImpl extends UnicastRemoteObject implements ISearch{
	private Connection conn;
	String name;
	
	public SearchImpl(String name) throws RemoteException {
		this.name = name;
		String driver = "net.ucanaccess.jbdc.UcanaccessDriver";
		String sql = "jdbc:ucanaccess://d://a.accdb";
		try {
			Class.forName(driver);
			this.conn = DriverManager.getConnection(sql);
		} catch (Exception e) {
			System.out.println("khong the ket noi");
			throw new RemoteException(e.getMessage());
		}
	}

	@Override
	public boolean checkUserName(String param) throws RemoteException {
	try {
		PreparedStatement ps = conn.prepareStatement("Select * from user where userName = ?");
		ps.setString(1,param);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	} catch (Exception e) {
		throw new RemoteException();
	}
		
	}

	@Override
	public boolean login(String userLogin, String param) throws RemoteException {
		try {
			PreparedStatement ps = conn.prepareStatement("Select * from User where username = ? and password = ?");
			ps.setString(1, userLogin);
			ps.setString(2,param);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			throw new RemoteException();
		}
		
		
	}

	@Override
	public ArrayList<Student> findById(String param) throws RemoteException {
		try {
			ArrayList<Student> listStudents = new ArrayList<Student>();
			int idSd = Integer.parseInt(param);
			PreparedStatement ps = conn.prepareStatement("Select * from Student where ID = ?");
			ps.setInt(1, idSd);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Student st = new Student(rs.getInt(idSd),rs.getString("name"),rs.getInt("Age"),rs.getDouble("Score"));
				listStudents.add(st);	
			}
			return listStudents;
		} catch (Exception e) {
			throw new RemoteException();
		}
		
	}
	
	
}
