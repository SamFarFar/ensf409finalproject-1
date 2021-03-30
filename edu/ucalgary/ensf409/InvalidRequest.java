package edu.ucalgary.ensf409;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        
        StringBuffer manufacture = new StringBuffer();

        // connect to database
        try{
            dbConnect = DriverManager.getConnection(dburl, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // create map manufacturers
        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM MANUFACTURER");
            while(results.next()) {
                manufacture.append(results.getString("Name") + ", " + results.getString("Phone") + ", " + results.getString("Province"));
                manufacture.append('\n');
            }
            myStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // create file
        try {
            File myObj = new File("OrderForm.txt");
            if (myObj.createNewFile()) {
                System.out.println("Invalid Request: Suggested Orderform Created");
            } 
          } catch (IOException e) {
            System.out.println("Error #3: Could Not Create File");
            e.printStackTrace();
          }

          // write out to file
          try {
            FileWriter myWriter = new FileWriter("OrderForm.txt");
            myWriter.write("Invalid Furniture Request\n");
            myWriter.write('\n');
            myWriter.write("List of Manufactures:\n");
            // connect to database and print out list of manufactures
            myWriter.write(manufacture.toString());
            myWriter.close();
          } catch (IOException e) {
            System.out.println("Error #4: Order Form Error Could Not Be Completed");
            e.printStackTrace();
          }
    }
    
}
