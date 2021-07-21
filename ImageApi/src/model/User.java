package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import pack.GetConn;

public class User {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String password;
	public void Add(){
		try{

			
			Connection cn= GetConn.getC();
			String sql = "insert into imgapidb.users (name, username, password) values(?,?,?)";
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, this.getName());
			ps.setString(2, this.getUsername());
			ps.setString(3, this.getPassword());
			ps.execute();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public User Get(String username){
		try{
			
			Connection cn=GetConn.getC();
			
			String sql = "select * from imgapidb.users where username=?";
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();  
			if(rs.next()) { 
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));
			User ob = new User();
			ob.setId(rs.getInt(1));
			ob.setName(rs.getString(2));
			ob.setUsername(rs.getString(3));
			ob.setPassword(rs.getString(4));
			return ob;
			}
			return null;
		}
		catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
}
