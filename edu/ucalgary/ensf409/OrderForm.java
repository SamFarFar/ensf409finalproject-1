/**
@author Matteo Morrone <a href = "mailto:matteo.morrone@ucalgary.ca">
	matteo.morrone@ucalgary.ca</a>
@author Sam FarzamFar <a href = "mailto:sam.farzamfar@ucalgary.ca">
	sam.farzamfar@ucalgary.ca</a>
@author Sandip Mishra <a href = "mailto:matteo.morrone@ucalgary.ca">
	matteo.morrone@ucalgary.ca</a>
@version 9.8
@since 1.0
*/

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
    private boolean isFormCreated = false;

    /**
     * Constructor for the OrderForm class that takes the request name and amount with a String
     * Array of the item ID's ordered and the total price of the order.
     * @param originalRequestName to be printed to the order form.
     * @param originalRequestAmount to be printed to the order form.
     * @param itemsOrdered to be printed to the order form.
     * @param totalPrice to be printed to the order form.
     */
    public OrderForm( String originalRequestName, int originalRequestAmount, 
					ArrayList<String> itemsOrdered, int totalPrice) {
        this.originalRequestName = originalRequestName;
        this.originalRequestAmount = originalRequestAmount;
        this.itemsOrdered = itemsOrdered;
        this.totalPrice = totalPrice;
    }

    /**
     * Getter for originalRequestName data member
     * @return originalRequestName
     */
    public String getOriginalRequestName() {
        return this.originalRequestName;
    }

    /**
     * Getter for originalRequestAmount data member
     * @return originalRequestAmount
     */
    public int getOriginalRequestAmount() {
        return this.originalRequestAmount;
    }

    /**
     * Getter for itemsOrdered data member
     * @return ItemsOrdered
     */
    public ArrayList<String> getItemsOrdered() {
        return itemsOrdered;
    }

    /**
     * Getter for totalPrice data member
     * @return TotalPrice
     */
    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     * Getter for isFormCreated data member
     * @return IsFormCreated
     */
    public boolean getIsFormCreated() {
        return isFormCreated;
    }

    /**
     * printOrderForm method creates the "OrderForm.txt" file that can be used for printing,
     * also generates an output message to the console consisting of parts to be pruchased, and 
     * their total price
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
        String output = "\nPurchase ";
        try {
            FileWriter myWriter = new FileWriter("OrderForm.txt");
            myWriter.write("Furniture Order Form\n\n");
            myWriter.write("Faculty Name: \n");
            myWriter.write("Contact: \n");
            myWriter.write("Date: \n\n");
            myWriter.write("Original Request: " + this.originalRequestName.toLowerCase() + ", " + this.originalRequestAmount + "\n\n");
            myWriter.write("Items Ordered\n");
            int i;
            for(i = 0; i < this.itemsOrdered.size(); i++) {
                myWriter.write("ID: " + this.itemsOrdered.get(i) + "\n");
                output += (this.itemsOrdered.get(i) + ", ");
            }
            output = output.substring(0,output.length()-2);
			if(i > 1){
				int lastCom = output.lastIndexOf(",");
				String firstHalf = output.substring(0, lastCom + 2);
				String secondHalf = output.substring(lastCom + 1);
				output = firstHalf + "and" + secondHalf;
			}
            output += " ";
            myWriter.write("\nTotal Price: $" + this.totalPrice);
            this.isFormCreated = true;
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error #2: Order Form Processing Could Not Be Completed");
            e.printStackTrace();
        }
        output += ("for $" + this.totalPrice + ".");
        System.out.println(output);
    }

}
