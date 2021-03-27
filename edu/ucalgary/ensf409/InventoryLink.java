package edu.ucalgary.ensf409;

import java.sql.*;
import java.util.ArrayList;

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
	
	public ArrayList<String> getPossibleItems(Request request) {
		ArrayList<String> retVal = new ArrayList<String>();
		try {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM " + request.getFurniture());
            
            // go through all furniture items in the request's type, if their 
            // type matches the request's type, add their ID to an ArrayList to return
            while (results.next()){
				if(results.getString("Type").equals(request.getType())){
					retVal.add(results.getString("ID"));
				}
            }
            
            myStmt.close();
		} catch(SQLException ex) {
            ex.printStackTrace();
        }
        return retVal;
	}
	
	public boolean[] getValidParts(String ID) {
		boolean[] retVal;
		String furniture = null;
		switch(ID.charAt(0)){
			case 'C':
				retVal = new boolean[4];
				furniture = "chair";
				break;
			case 'D':
				retVal = new boolean[3];
				furniture = "desk";
				break;
			case 'F':
				retVal = new boolean[3];
				furniture = "filing";
				break;
			case 'L':
				retVal = new boolean[2];
				furniture = "lamp";
				break;
			default:
				return null;
		}
		int index = 0;
		try {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM " + furniture);
            while (results.next()){
				if(ID.equals(results.getString("ID"))){
					if(furniture.equals("chair")){
						
						if(results.getString("Legs").equals("Y")){
							retVal[index++] = true;
						} else retVal[index++] = false;
						if(results.getString("Arms").equals("Y")){
							retVal[index++] = true;
						} else retVal[index++] = false;
						if(results.getString("Seat").equals("Y")){
							retVal[index++] = true;
						} else retVal[index++] = false;
						if(results.getString("Cushion").equals("Y")){
							retVal[index++] = true;
						} else retVal[index++] = false;
						
					} else if(furniture.equals("desk")){
						
						if(results.getString("Legs").equals("Y")){
							retVal[index++] = true;
						} else retVal[index++] = false;
						if(results.getString("Top").equals("Y")){
							retVal[index++] = true;
						} else retVal[index++] = false;
						if(results.getString("Drawer").equals("Y")){
							retVal[index++] = true;
						} else retVal[index++] = false;
						
					} else if(furniture.equals("filing")){
						
						if(results.getString("Rails").equals("Y")){
							retVal[index++] = true;
						} else retVal[index++] = false;
						if(results.getString("Drawers").equals("Y")){
							retVal[index++] = true;
						} else retVal[index++] = false;
						if(results.getString("Cabinet").equals("Y")){
							retVal[index++] = true;
						} else retVal[index++] = false;
						
					} else if(furniture.equals("lamp")){
						
						if(results.getString("Base").equals("Y")){
							retVal[index++] = true;
						} else retVal[index++] = false;
						if(results.getString("Bulb").equals("Y")){
							retVal[index++] = true;
						} else retVal[index++] = false;
						
					}
					
				}
            }
            myStmt.close();
		} catch(SQLException ex) {
            ex.printStackTrace();
        }
		return retVal;
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
