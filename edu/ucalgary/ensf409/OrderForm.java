package edu.ucalgary.ensf409;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OrderForm {

    private String facultyName;
    private String contact;
    private String date;
    private String originalRequestName;
    private int originalRequestAmount;
    private String[] itemsOrdered;
    private int totalPrice;

    public OrderForm(String facultyName, String contact, String date, String originalRequestName,
    int originalRequestAmount, String[] itemsOrdered, int totalPrice) {
        this.facultyName = facultyName;
        this.contact = contact;
        this.date = date;
        this.originalRequestName = originalRequestName;
        this.originalRequestAmount = originalRequestAmount;
        this.itemsOrdered = itemsOrdered;
        this.totalPrice = totalPrice;

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
            myWriter.write("Furniture Order Form\n");
            myWriter.write('\n');
            myWriter.write("Faculty Name: " + this.facultyName + "\n");
            myWriter.write("Contact: " + this.contact+ "\n");
            myWriter.write("Date: " + this.date + "\n");
            myWriter.write('\n');
            myWriter.write("Original Request: " + this.originalRequestName + ", " + this.originalRequestAmount + '\n');
            myWriter.write('\n');
            myWriter.write("Items Ordered\n");
            for(int i = 0; i < this.itemsOrdered.length; i++) {
                myWriter.write("ID: " + this.itemsOrdered[i] + "\n");
            }
            myWriter.write('\n');
            myWriter.write("Total Price: $" + this.totalPrice);
            myWriter.close();
            System.out.println("Order Form Processing Completed.");
          } catch (IOException e) {
            System.out.println("Error #2: Order Form Processing Could Not Be Completed");
            e.printStackTrace();
          }
    }

    public OrderForm(String originalRequestName,
    int originalRequestAmount, String[] itemsOrdered, int totalPrice) {
        this.originalRequestName = originalRequestName;
        this.originalRequestAmount = originalRequestAmount;
        this.itemsOrdered = itemsOrdered;
        this.totalPrice = totalPrice;

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
            myWriter.write("Furniture Order Form\n");
            myWriter.write('\n');
            myWriter.write("Faculty Name: \n");
            myWriter.write("Contact: \n");
            myWriter.write("Date: \n");
            myWriter.write('\n');
            myWriter.write("Original Request: " + this.originalRequestName + ", " + this.originalRequestAmount + '\n');
            myWriter.write('\n');
            myWriter.write("Items Ordered\n");
            for(int i = 0; i < this.itemsOrdered.length; i++) {
                myWriter.write("ID: " + this.itemsOrdered[i] + "\n");
            }
            myWriter.write('\n');
            myWriter.write("Total Price: $" + this.totalPrice);
            myWriter.close();
            System.out.println("Order Form Processing Completed.");
          } catch (IOException e) {
            System.out.println("Error #2: Order Form Processing Could Not Be Completed");
            e.printStackTrace();
          }
    }
}
