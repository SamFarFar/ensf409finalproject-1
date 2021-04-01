package edu.ucalgary.ensf409;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OrderForm {

    private String originalRequestName;
    private int originalRequestAmount;
    private String[] itemsOrdered;
    private int totalPrice;

    public OrderForm( String originalRequestName,
    int originalRequestAmount, String[] itemsOrdered, int totalPrice) {
        this.originalRequestName = originalRequestName;
        this.originalRequestAmount = originalRequestAmount;
        this.itemsOrdered = itemsOrdered;
        this.totalPrice = totalPrice;
    }

    public void printOrderForm(){
        try {
            File myObj = new File("OrderForm.txt");
            if (myObj.createNewFile()) {
                System.out.println("Order Form Created Succesfully");
            }
        } catch (IOException e) {
            System.out.println("Error #1: Order Form Creation Error");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("OrderForm.txt");
            myWriter.write("Furniture Order Form\n\n");
            myWriter.write("Faculty Name: \n");
            myWriter.write("Contact: \n");
            myWriter.write("Date: \n\n");
            myWriter.write("Original Request: " + this.originalRequestName + ", " + this.originalRequestAmount + "\n\n");
            myWriter.write("Items Ordered\n");
            for(int i = 0; i < this.itemsOrdered.length; i++) {
                myWriter.write("ID: " + this.itemsOrdered[i] + "\n");
            }
            myWriter.write("\nTotal Price: $" + this.totalPrice);
            myWriter.close();
            System.out.println("Order Form Processing Completed.");
        } catch (IOException e) {
            System.out.println("Error #2: Order Form Processing Could Not Be Completed");
            e.printStackTrace();
        }
    }

}
