package JUnit;

import edu.ucalgary.ensf409.InvalidRequestException;
import edu.ucalgary.ensf409.OrderForm;
import edu.ucalgary.ensf409.Request;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;


public class FPTest {
    // ================================= \\
    // OrderForm.java
    // ================================= \\

    // tests the constructor setting up OrderForm
    @Test
    public void testOrderFormConstructor(){
        String testStringName = "chair";
        int testAmount = 1;
        ArrayList<String> testList  = new ArrayList<String>();
        testList.add("C123");
        testList.add("C234");
        testList.add("C567");
        int testTotalPrice = 100;
        OrderForm testForm = new OrderForm(testStringName, testAmount, testList, testTotalPrice);
        assertEquals(testStringName, testForm.getOriginalRequestName());
        assertEquals(testAmount,testForm.getOriginalRequestAmount());
        assertEquals(testList, testForm.getItemsOrdered());
        assertEquals(testTotalPrice, testForm.getTotalPrice());
        System.out.println("Test: Order Form Constructor Test Success");
    }

    // tests the creation of the OrderForm.txt file
    @Test
    public void testOrderFormCreation() {
        String testStringName = "chair";
        int testAmount = 1;
        ArrayList<String> testList  = new ArrayList<String>();
        testList.add("C123");
        testList.add("C234");
        testList.add("C567");
        int testTotalPrice = 100;
        OrderForm testForm = new OrderForm(testStringName, testAmount, testList, testTotalPrice);
        testForm.printOrderForm();
        boolean test = testForm.getIsFormCreated();
        assertEquals(test,true);
        System.out.println(".Test: Order Form Creation Test Success");
    }

    // tests content of OrderForm.txt file with a Sample Correct File
    @Test
    public void testOrderFormContent() {
        File SampleFile = new File("OrderForm.txt");
        File OrderFile = new File("./JUnit/Sample.txt");
        boolean isEqual = isEqual(SampleFile.toPath(), OrderFile.toPath());
        assertEquals(isEqual,true);
        System.out.println("Test: Order Form Content Test Successful");
    }

    // supporting function with
    private static boolean isEqual(Path firstFile, Path secondFile) {
        try {
            if (Files.size(firstFile) != Files.size(secondFile)) {
                return false;
            }
            byte[] first = Files.readAllBytes(firstFile);
            byte[] second = Files.readAllBytes(secondFile);
            return Arrays.equals(first, second);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    // ================================= \\
    // Request.java
    // ================================= \\


    // tests Request Constructor & checkValidity with invalid case
    @Test (expected=InvalidRequestException.class)
    public void testInvalidRequestConstructor() throws InvalidRequestException {
        String t = "Task";
        String f = "fff";
        int q = 1;
        System.out.println("Test: Invalid Request Creation Succesful");
        Request testReq = new Request(t,f,q);
    }

    // tests Request Constructor & checkValidity with valid case
    @Test
    public void testRequestConstructor() throws InvalidRequestException {
        String t = "Task";
        String f = "chair";
        int q = 1;
        Request testReq = new Request(t,f,q);
        System.out.println("Test: Request Creation Succesful");
    }

    // tests 4 getter functions in Request
    @Test
    public void testRequestGetters() throws InvalidRequestException {
        String t = "Task";
        String f = "chair";
        int q = 1;
        int partNum = 4;
        Request testGetters = new Request(t,f,q);
        assertEquals(testGetters.getPartNum(),partNum);
        assertEquals(testGetters.getType(), "Task");
        assertEquals(testGetters.getFurniture(), "chair");
        assertEquals(testGetters.getQuantity(), 1);
        System.out.println("Test: Request Getters were Succesful");
    }

    // ================================= \\
    // InventoryLink.java
    // ================================= \\


}

// copy this as a blank test
  /*  @Test
    public void Test(){

    }*/