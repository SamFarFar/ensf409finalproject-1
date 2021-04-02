package edu.ucalgary.ensf409;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OrderForm {

    private String originalRequestName;
    private int originalRequestAmount;
    private ArrayList<String> itemsOrdered;
    private int totalPrice;

    /**
     * Constructor for the OrderForm class that takes the request name and amount with a String
     * Array of the item ID's ordered and the total price of the order.
     * @param originalRequestName to be printed to the order form.
     * @param originalRequestAmount to be printed to the order form.
     * @param itemsOrdered to be printed to the order form.
     * @param totalPrice to be printed to the order form.
     */
    public OrderForm( String originalRequestName,
    int originalRequestAmount, ArrayList<String> itemsOrdered, int totalPrice) {
        this.originalRequestName = originalRequestName;
        this.originalRequestAmount = originalRequestAmount;
        this.itemsOrdered = itemsOrdered;
        this.totalPrice = totalPrice;
    }

    /**
     * printOrderForm method creates the "OrderForm.txt" file that can be used for printing
     */
    public void printOrderForm(){
        try {
            File myObj = new File("OrderForm.txt");
            if (myObj.createNewFile()) {
                System.out.println("Order Form Created Successfully");
            }
        } catch (IOException e) {
            System.out.println("Error #1: Order Form Creation Error");
            e.printStackTrace();
        }
		String output = "Purchase ";
        try {
            FileWriter myWriter = new FileWriter("OrderForm.txt");
            myWriter.write("Furniture Order Form\n\n");
            myWriter.write("Faculty Name: \n");
            myWriter.write("Contact: \n");
            myWriter.write("Date: \n\n");
            myWriter.write("Original Request: " + this.originalRequestName + ", " + this.originalRequestAmount + "\n\n");
            myWriter.write("Items Ordered\n");
            for(int i = 0; i < this.itemsOrdered.size(); i++) {
                myWriter.write("ID: " + this.itemsOrdered.get(i) + "\n");
                output += (this.itemsOrdered.get(i) + " ");
                if(i < this.itemsOrdered.size()-1)
					output += "and ";
            }
            myWriter.write("\nTotal Price: $" + this.totalPrice);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error #2: Order Form Processing Could Not Be Completed");
            e.printStackTrace();
        }
        output += ("for " + this.totalPrice);
        System.out.println(output);

    }

}
