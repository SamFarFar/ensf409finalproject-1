package edu.ucalgary.ensf409;
import java.sql.*;

/*
 * 
 *  Hey Dipler, just fyi we're gunna try and keep all the sql code within the 
 *  InventoryLink class just to better compartmentalize it.  I'll run through 
 *  it later and try to correct
 * 
 */

public class InvalidRequest {
    
    private Connection dbConnect;
    private ResultSet results;

    public InvalidRequest(String dburl, String user, String pass) {
        try{
            dbConnect = DriverManager.getConnection(dburl, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Cannot Process Request. Suggested Manufacturer\n");
        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM MANUFACTURER");
            while(results.next()) {
                System.out.println(results.getString("Name"));
            }
            myStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 

    }

    
}
