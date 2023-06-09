package com.projemMySQL.util;
import java.sql.*;
public class VeritabaniBaglanti {

	static Connection conn=null;
	public static Connection baglan() {
		try {
			
			conn=DriverManager.getConnection("jdbc:mysql://localhost/saglikocagi?serverTimezone=Europe/Istanbul","root","");
			return conn;
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage().toString());
			return null;
			
		}
	}
	
}
