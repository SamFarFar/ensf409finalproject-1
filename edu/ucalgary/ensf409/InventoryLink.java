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
		// sort by price
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
		int numberOfParts = 0;
		try {
            Statement myStmt = dbConnect.createStatement();
            switch(ID.charAt(0)){
				case 'C':
					numberOfParts = 4;
					results = myStmt.executeQuery("SELECT Legs,Arms,Seat,Cushion FROM chair WHERE ID = '" + ID + "'");
					break;
				case 'D':
					numberOfParts = 3;
					results = myStmt.executeQuery("SELECT Legs,Top,Drawer FROM desk WHERE ID = '" + ID + "'");
					break;
				case 'F':
					numberOfParts = 3;
					results = myStmt.executeQuery("SELECT Rails,Drawers,Cabinet FROM filing WHERE ID = '" + ID + "'");
					break;
				case 'L':
					numberOfParts = 2;
					results = myStmt.executeQuery("SELECT Base,Bulb FROM lamp WHERE ID = '" + ID + "'");
					break;
				default:
					return null;
			}
            retVal = new boolean[numberOfParts];
            while(results.next()){
				for(int i = 0; i < numberOfParts; i++){
					if(results.getString(i).equals("Y")){
						retVal[i] = true;
					} else retVal[i] = false;
				}
			}
            myStmt.close();
            return retVal;
		} catch(SQLException ex) {
            ex.printStackTrace();
        }
		return null;
	}
	
	public int getPrice(String ID) {
		int price = -1;
		try {
            Statement myStmt = dbConnect.createStatement();
            switch(ID.charAt(0)){
				case 'C':
					results = myStmt.executeQuery("SELECT Price FROM chair WHERE ID = '" + ID + "'");
					break;
				case 'D':
					results = myStmt.executeQuery("SELECT Price FROM desk WHERE ID = '" + ID + "'");
					break;
				case 'F':
					results = myStmt.executeQuery("SELECT Price FROM filing WHERE ID = '" + ID + "'");
					break;
				case 'L':
					results = myStmt.executeQuery("SELECT Price FROM lamp WHERE ID = '" + ID + "'");
					break;
				default:
					return price;
			}
            while(results.next()){
				price = results.getInt(0);
			}
            myStmt.close();
		} catch(SQLException ex) {
            ex.printStackTrace();
        }
        return price;
	}

	public String[] IDTOManuID(String[] ID){
		String[] ManuID = new String[ID.length];
		for (int i = 0; i < ID.length; i++) {
			ManuID[i] = getManuID(ID[i]);
		}
		return ManuID;
	}
	// function that takes an ID array and converts it to its corresponding manuID array
	private String getManuID(String ID){
		String ManuID= "";
		try {
			Statement myStmt = dbConnect.createStatement();
			switch(ID.charAt(0)){
				case 'C':
					results = myStmt.executeQuery("SELECT INVENTORY.CHAIR.ManuID FROM INVENTORY.CHAIR WHERE ID = '" + ID + "'");
					break;
				case 'D':
					results = myStmt.executeQuery("SELECT INVENTORY.DESK.ManuID  FROM INVENTORY.DESK WHERE ID = '" + ID + "'");
					break;
				case 'F':
					results = myStmt.executeQuery("SELECT INVENTORY.FILING.ManuID  FROM INVENTORY.FILING WHERE ID = '" + ID + "'");
					break;
				case 'L':
					results = myStmt.executeQuery("SELECT INVENTORY.LAMP.ManuID  FROM INVENTORY.LAMP WHERE ID = '" + ID + "'");
					break;
				default:
					throw new IllegalArgumentException("The ID provided does not have a manufacturer");
			}
			while(results.next()){
				ManuID = results.getString(1);
			}
			myStmt.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return ManuID;
	}
	public void deleteFurniture(String ID){
		try {
			String furniture = null;
			switch(ID.charAt(0)){
				case 'C':
					furniture = "chair";
					break;
				case 'D':
					furniture = "desk";
					break;
				case 'F':
					furniture = "filing";
					break;
				case 'L':
					furniture = "lamp";
					break;
				default:
					return;
			}
            String query = "DELETE FROM " + furniture + " WHERE ID = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setString(1, ID); 
            myStmt.executeUpdate();
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
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


	public void invalidRequest(String[] ID){
		String[] MIDPossible = IDTOManuID(ID);


		try {
			String query = "SELECT * FROM MANUFACTURER" +" WHERE ManuID = ?";
			PreparedStatement myStmt = dbConnect.prepareStatement();

			results = myStmt.executeQuery(query);
			while(results.next()) {
				System.out.println(results.getString("Name"));
			}
			myStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
