package pack;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;


public class GetConn {
	static Connection cn=null;

	static String driver="com.mysql.jdbc.Driver";
	static String url="jdbc:mysql://localhost:3306/imgapidb";
	static String user="root";
	static String pass="nopass123";
	public static Connection getC(){
	try{
		Class.forName(driver);
		cn=DriverManager.getConnection(url,user,pass);
		
	}catch(Exception e){
		System.out.println(e);
	}
	return cn;
	}
}
