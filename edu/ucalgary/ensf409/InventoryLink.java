package edu.ucalgary.ensf409;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


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
	public InventoryLink(String DBurl, String user, String pass) {
		this.DBURL = DBurl;
		this.USERNAME = user;
		this.PASSWORD = pass;
	}

	/**
	 * Returns the DBurl Value
	 * @return DBurl
	 */
	public String getDBURL() {
		return DBURL;
	}

	/**
	 * Returns the Password Value
	 * @return DBurl
	 */
	public String getPASSWORD() {
		return PASSWORD;
	}

	/**
	 * Returns the username Value
	 * @return DBurl
	 */
	public String getUSERNAME() {
		return USERNAME;
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
	
	/**
	 * Takes a String ID of a desired furniture item in the database, and 
	 * iterates through all parts of the entry, returning a boolean array 
	 * corresponding to the availability of its parts.
	 * @param ID String correlating to the desired ID field of an entry 
	 * in the database
	 * @return boolean array from the "Y" and "N" values in the entry's 
	 * columns, wherein "Y" becomes true and "N" becomes no (in order)
	 */
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
					if(results.getString(i+1).equals("Y")){
						retVal[i] = true;
					} else{
						 retVal[i] = false;
					 }
				}
			}
            myStmt.close();
            return retVal;
		} catch(SQLException ex) {
            ex.printStackTrace();
        }
		return null;
	}
	
	/**
	 * Takes a String ID of a desired furniture item in the database, and 
	 * retrieves its price, in the form of an integer.
	 * @param ID String correlating to the desired ID field of an entry 
	 * in the database
	 * @return Price of the desired item in the database as an integer
	 */
	public int getPrice(String ID) {
		int price = 0;
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
				price = results.getInt(1);
			}
            myStmt.close();
		} catch(SQLException ex) {
            ex.printStackTrace();
        }
        return price;
	}
	
	/**
	 * Takes a String ID of a desired furniture item in the database, and 
	 * retrieves its ManuID, in the form of a String.
	 * @param ID String correlating to the desired ID field of an entry 
	 * in the database
	 * @return String value of ManuID field of given item in the database
	 */
	public String getManuID(String ID){
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
	
	/**
	 * Used to translate a boolean value of part availability (as is stored 
	 * in the furniture's classes) and convert it into a String value usable 
	 * within the database.
	 * @param b Boolean value relating to the availability of a certain part
	 * @return "Y" if b == true, "N" if b == false
	 */
	private String getLetter(boolean b){
		if(b){
			return "Y";
		} else {
			return "N";
		}
	}
	
	/**
	 * Takes an object containing all necessary data to create a new database 
	 * entry, and does so.
	 * @param chair Chair object to be added to database
	 */
	public void addFurn(Chair chair){
		try {
			String legs = getLetter(chair.isLegs());
			String arms = getLetter(chair.isArms());
			String seat = getLetter(chair.isSeat());
			String cushion = getLetter(chair.isCushion());
			String query = "INSERT INTO chair (ID, Type, Legs, Arms, Seat," + 
							" Cushion, Price, ManuID) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement myStmt = dbConnect.prepareStatement(query);
			myStmt.setString(1, chair.getID());
            myStmt.setString(2, chair.getType());
            myStmt.setString(3, legs);
            myStmt.setString(4, arms);
            myStmt.setString(5, seat);
            myStmt.setString(6, cushion);
            myStmt.setInt(7, chair.getPrice());
            myStmt.setString(8, chair.getManuID());
			myStmt.executeUpdate();
			myStmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Takes an object containing all necessary data to create a new database 
	 * entry, and does so.
	 * @param desk Desk object to be added to database
	 */
	public void addFurn(Desk desk){
		try {
			String legs = getLetter(desk.isLegs());
			String top = getLetter(desk.isTop());
			String drawer = getLetter(desk.isDrawer());
			String query = "INSERT INTO desk (ID, Type, Legs, Top, Drawer,"+
							" Price, ManuID) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement myStmt = dbConnect.prepareStatement(query);
			myStmt.setString(1, desk.getID());
            myStmt.setString(2, desk.getType());
            myStmt.setString(3, legs);
            myStmt.setString(4, top);
            myStmt.setString(5, drawer);
            myStmt.setInt(6, desk.getPrice());
            myStmt.setString(7, desk.getManuID());
			myStmt.executeUpdate();
			myStmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Takes an object containing all necessary data to create a new database 
	 * entry, and does so.
	 * @param filing Filing object to be added to database
	 */
	public void addFurn(Filing filing){
		try {
			String rails = getLetter(filing.isRails());
			String drawers = getLetter(filing.isDrawers());
			String cabinet = getLetter(filing.isCabinet());
			String query = "INSERT INTO filing (ID, Type, Rails, Drawers,"+
							" Cabinet, Price, ManuID) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement myStmt = dbConnect.prepareStatement(query);
			myStmt.setString(1, filing.getID());
            myStmt.setString(2, filing.getType());
            myStmt.setString(3, rails);
            myStmt.setString(4, drawers);
            myStmt.setString(5, cabinet);
            myStmt.setInt(6, filing.getPrice());
            myStmt.setString(7, filing.getManuID());
			myStmt.executeUpdate();
			myStmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Takes an object containing all necessary data to create a new database 
	 * entry, and does so.
	 * @param lamp Lamp object to be added to database
	 */
	public void addFurn(Lamp lamp){
		try {
			String base = getLetter(lamp.isBase());
			String bulb = getLetter(lamp.isBulb());
			String query = "INSERT INTO lamp (ID, Type, Base, Bulb, Price,"+
							" ManuID) VALUES (?,?,?,?,?,?)";
			PreparedStatement myStmt = dbConnect.prepareStatement(query);
			myStmt.setString(1, lamp.getID());
            myStmt.setString(2, lamp.getType());
            myStmt.setString(3, base);
            myStmt.setString(4, bulb);
            myStmt.setInt(5, lamp.getPrice());
            myStmt.setString(6, lamp.getManuID());
			myStmt.executeUpdate();
			myStmt.close();
		} catch (SQLException ex) {
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
	
	public ArrayList<String> stripDuplicates(ArrayList<String> str){
		ArrayList<String> retVal = new ArrayList<>();
		for(String s : str){
			if(!retVal.contains(s)){
				retVal.add(s);
			}
		}
		return retVal;
	}
	
	private ArrayList<String> getAllManuIDs(Request rq){
		ArrayList<String> retVal = new ArrayList<>();
		try{
			Statement myStmt = dbConnect.createStatement();
			String query = "SELECT ManuID FROM " + rq.getFurniture() + " WHERE Type = '" + rq.getType() + "'";
			results = myStmt.executeQuery(query);
			while(results.next()){
				retVal.add(results.getString(1));
			}
			myStmt.close();
		} catch(SQLException ex) {
            ex.printStackTrace();
        }
		return retVal;
	}
	
	// updated to ArrayList for consistency.  Not entirely sure why
	// we're just printing them to the console...
	public void invalidRequest(Request rq, ArrayList<Chair> chairs,
				ArrayList<Lamp> lamps, ArrayList<Desk> desks, ArrayList<Filing> filings){
		switch(rq.getFurniture()){
			case "chair":
				for(int i = 0; i < chairs.size(); i++){
					addFurn(chairs.get(i));
				}
				break;
			case "desk":
				for(int i = 0; i < desks.size(); i++){
					addFurn(desks.get(i));
				}
				break;
			case "filing":
				for(int i = 0; i < filings.size(); i++){
					addFurn(filings.get(i));
				}
				break;
			case "lamp":
				for(int i = 0; i < lamps.size(); i++){
					addFurn(lamps.get(i));
				}
				break;
			default:
				return;
		}
		ArrayList<String> MIDPossible = getAllManuIDs(rq);
		MIDPossible = stripDuplicates(MIDPossible);
		String output = "\nOrder cannot be fulfilled based on current "+
								"inventory. Suggested manufacturers are ";

		try {
			Statement myStmt = dbConnect.createStatement();
			int counter = 0;
			for (int i = 0; i < MIDPossible.size(); i++) {
				String query = "SELECT Name FROM manufacturer WHERE ManuID = '" + MIDPossible.get(i) +"'";
				results = myStmt.executeQuery(query);
				while(results.next()){
					output += (results.getString(1) + ", ");
					counter++;
				}
			}
			output = output.substring(0,output.length()-2);
			if(counter > 1){
			int lastCom = output.lastIndexOf(",");
			String firstHalf = output.substring(0, lastCom + 2);
			String secondHalf = output.substring(lastCom + 1);
				output = firstHalf + "and" + secondHalf + ".";
			}else{
				output += ".";
			}
			myStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(output); // Keep this one for printing output
		System.exit(1);
	}


	
	/**
	 * Implements bubble sort algorithm in order to sort the given 
	 * arrayList of String ID's by their corresponding price, lowest to 
	 * highest.
	 * @param pI ArrayList<String> of possible ID's
	 * @return the original ArrayList sorted by price from lowest to highest for 
	 * each ID
	 */
	public ArrayList<String> sort(ArrayList<String> pI) {
		for (int i = 0; i < pI.size()-1; i++){
            for (int j = 0; j < pI.size()-i-1; j++){
				if(getPrice(pI.get(j)) > getPrice(pI.get(j+1))){
					String swap = pI.get(j);
					pI.set(j, pI.get(j+1));
					pI.set(j+1, swap);
				}
			}
		}
		return pI;
	}
	
}


