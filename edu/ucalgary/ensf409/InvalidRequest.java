package edu.ucalgary.ensf409;
import java.sql.*;

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
    }
    
}
