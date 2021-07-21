package model;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import pack.GetConn;

public class Image {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String img_name;
	
	public String getImg_name() {
		return img_name;
	}
	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
	private int userid;
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public static ArrayList<Image> Get(int userid){
		try{
			
			Connection cn=GetConn.getC();
			
			String sql = "select * from imgapidb.images where userid=?";
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setInt(1, userid);
			ResultSet rs=ps.executeQuery();  
		
			ArrayList<Image> arr = new ArrayList<Image>();
			
			while(rs.next()) { 
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
			Image ob = new Image();
			ob.setId(rs.getInt(1));
			ob.setImg_name(rs.getString(2));
			ob.setUserid(rs.getInt(3));
		//	ob.setPassword(rs.getString(4));
			arr.add(ob);
			
			}
			return arr;
			
		}
		catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
//	public void Add(){
//		try{
//
//			
//			Connection cn= GetConn.getC();
//			String sql = "insert into imgapidb.images (img_name, userid) values(?,?)";
//			PreparedStatement ps=cn.prepareStatement(sql);
//			ps.setString(1, this.getImg_name());
//			ps.setInt(2, this.getUserid());
//			//ps.setString(3, this.getPassword());
//			ps.execute();
//		}
//		catch(Exception e){
//			System.out.println(e);
//		}
//	}
}
