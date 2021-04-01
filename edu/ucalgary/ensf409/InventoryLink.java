package edu.ucalgary.ensf409;

import java.sql.*;
import java.util.ArrayList;

public class InventoryLink {
	
	public final String DBURL; //store the database url information
    public final String USERNAME; //store the user's account username
    public final String PASSWORD; //store the user's account password
    private Connection dbConnect;
    private ResultSet results;

	/**
	 * constructor for the InventoryLink object that stores the database information and
	 * request information. It also creates an ArrayList that is stored internally and contains
	 * possible items that match the request based on what is still left in the Inventory
	 * database.
	 * @param DBurl
	 * @param user
	 * @param pass
	 * @param userRequest
	 */
	public InventoryLink(String DBurl, String user, String pass, Request userRequest) {
		this.DBURL = DBurl;
		this.USERNAME = user;
		this.PASSWORD = pass;
	}

	/**
	 * Initializes the connection to the database from the database information provided.
	 */
	public void initializeConnection() {
		try{
            dbConnect = DriverManager.getConnection(this.DBURL, this.USERNAME, this.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	/**
	 * By taking in a request this method can convert
	 * the request into an ArrayList of type String.
	 * @param request to be read for ArrayList creation.
	 * @return an ArrayList with potential Furniture
	 */
	public ArrayList<String> getPossibleItems(Request request) {
		// sort by price
		ArrayList<String> retVal = new ArrayList<>();
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
	
	public String[] arrListToArray(ArrayList<String> arrayList){
		String[] array = new String[arrayList.size()];
		for (int i = 0; i < arrayList.size(); i++) {
			array[i] = arrayList.get(i);
		}
		return array;
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
					results = myStmt.executeQuery("SELECT ManuID FROM chair WHERE ID = '" + ID + "'");
					break;
				case 'D':
					results = myStmt.executeQuery("SELECT ManuID  FROM desk WHERE ID = '" + ID + "'");
					break;
				case 'F':
					results = myStmt.executeQuery("SELECT ManuID  FROM filing WHERE ID = '" + ID + "'");
					break;
				case 'L':
					results = myStmt.executeQuery("SELECT ManuID  FROM lamp WHERE ID = '" + ID + "'");
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

	/* Need a method that adds a piece of furniture back into database so that we
	 * can start testing the program!
	 */
	public void addDesk(String ID){
		try {
			int firstLetter = ID.charAt(0); // cast first letter as its corresponding ASCII number
			if(firstLetter == 68){
				throw new InvalidIDException();
			}
			String query = "INSERT INTO  ? ()";
			PreparedStatement myStmt = dbConnect.prepareStatement(query);
			myStmt.setString(2, ID);
			myStmt.executeUpdate();
			myStmt.close();
		} catch (SQLException | InvalidIDException ex) {
			ex.printStackTrace();
		}
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
			String query = "SELECT Name FROM MANUFACTURER" + " WHERE ManuID = ?";
			PreparedStatement myStmt = dbConnect.prepareStatement(query);
			for (int i = 0; i < MIDPossible.length; i++) {
				myStmt.setString(1, MIDPossible[i]);
				myStmt.executeUpdate();
				while (results.next()) {
					System.out.println(results.toString());
				}
			}
			myStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * sorts the possible items by lowest to highest price
	 */
	public ArrayList<String> sort(ArrayList<String> pI) {
		
		
		
		
		
	}
	
	
	
}


