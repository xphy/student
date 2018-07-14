package com.db;

import java.sql.*;


public class DButil {
    private static String url="jdbc:mysql://localhost:3306/worker";
    private static String use="root";
    private static String pwd="root";
    public static Connection getConnection() {
    	Connection conn=null;
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url, use, pwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
    }
}   
