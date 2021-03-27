package edu.ucalgary.ensf409;

import java.sql.*;

public class InventoryLink {
	
	public final String DBURL; //store the database url information
    public final String USERNAME; //store the user's account username
    public final String PASSWORD; //store the user's account password
    private Connection dbConnect;
    private ResultSet results;
	
	public InventoryLink(String dburl, String user, String pass) {
		this.DBURL = dburl;
		this.USERNAME = user;
		this.PASSWORD = pass;		
	}
	
	public void initializeConnection() {
		try{
            dbConnect = DriverManager.getConnection(this.DBURL, this.USERNAME, this.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	
	
	public void close() {
		try {
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
}
