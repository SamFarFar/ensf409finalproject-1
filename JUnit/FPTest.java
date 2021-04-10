package JUnit;

import edu.ucalgary.ensf409.*;

import org.junit.Test;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;


/*
Sam's rules for JUnits:

1. only 1 assert per test

2. when adding to a database must follow a delete immediately afterwards to not
mess up database of usage. test using constructors of the objects.

3. tests must chronologically make sense. meaning if u want to test something and it uses a
testable function then u must have previously tested the function above.

4. dont use any System.out.println() because JUnit 4 does it for us already

5. comment above your test what you are testing

6. Supporting methods need to go outside of FPTest and be set to public

silly thing to consider
                 Better ---|--- Worse
 assertTrue(randomTest)               assertEquals(randomTest, true);

 */

public class FPTest {
    // ================================= \\
    // OrderForm.java
    // ================================= \\

    // tests the constructor setting up OrderForm
    @Test
    public void testOrderFormConstructor(){
        String testStringName = "mesh chair";
        int testAmount = 1;
        ArrayList<String> testList  = new ArrayList<>();
        testList.add("C123");
        testList.add("C234");
        testList.add("C567");
        int testTotalPrice = 100;
        OrderForm testForm = new OrderForm(testStringName, testAmount, testList, testTotalPrice);
        boolean isWorking = false;
        if(testStringName.equals(testForm.getOriginalRequestName())
                && testAmount == testForm.getOriginalRequestAmount()
                && testList == testForm.getItemsOrdered()
                && testTotalPrice == testForm.getTotalPrice()
        ){
            isWorking = true;
        }
        assertTrue(isWorking);
    }

    // tests the creation of the OrderForm.txt file
    @Test
    public void testOrderFormCreation() {
        String testStringName = "mesh chair";
        int testAmount = 1;
        ArrayList<String> testList  = new ArrayList<>();
        testList.add("C123");
        testList.add("C234");
        testList.add("C567");
        int testTotalPrice = 100;
        OrderForm testForm = new OrderForm(testStringName, testAmount, testList, testTotalPrice);
        testForm.printOrderForm();
        boolean test = testForm.getIsFormCreated();
        assertTrue(test);
    }

    // tests content of OrderForm.txt file with a Sample Correct File
    @Test
    public void testOrderFormContent() {
        File SampleFile = new File("OrderForm.txt");
        File OrderFile = new File("./JUnit/Sample.txt");
        boolean isEqual = isEqual(SampleFile.toPath(), OrderFile.toPath());
        assertTrue(isEqual);
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
        boolean tester = false;
        String t = "Task";
        String f = "fff";
        int q = 1;
        Request testReq = new Request(t,f,q);
        if(testReq.getFurniture().equals(f) && testReq.getFurniture().equals("chair") &&
                testReq.getQuantity() == 1){
            tester = true;
        }
        assertTrue(tester);
    }

    // tests Request Constructor & checkValidity with valid case
    @Test
    public void testRequestConstructor() throws InvalidRequestException {
       boolean tester = false;
        String t = "mesh";
        String f = "chair";
        int q = 1;
        Request testReq = new Request(t,f,q);
        if(testReq.getFurniture().equals(f) && testReq.getFurniture().equals("chair") &&
        testReq.getQuantity() == 1){
            tester = true;
        }
        assertTrue(tester);
    }


    // ================================= \\
    // InventoryLink.java
    // ================================= \\



    //(DATA BASE STUFF CANT DO RIGHT NOW)
    // Tests Initialize Connection

    // Tests Improper Initialize Connection running

    // Tests getPossibleItems with Request  running

    // Tests getValidParts with ID

    // Tests getPrice with ID

    // Tests getManuID with valid ID

    // Tests creating any furniture in the database

    // Tests deleting furniture in the database /

    // Tests stripping duplicates with an ArrayList that has duplicates /

    // Tests stripping duplicates with an ArrayList that has no duplicates /

    // Tests an invalidRequest response ////
    // when there is 0,1,2,* manufacturers

    // Tests the filter with valid ArrayList /

    // Tests the sort algorithm with valid ArrayList /



    // ================================= \\
    // InvalidIDException.java
    // ================================= \\

    // (Isnt utilized yet)
    //Tests a scenario where an InvalidIDException is thrown

    // ================================= \\
    // Input.java
    // ================================= \\

    // (Functions need to be changed)


}

// copy this as a blank test
  /*  @Test
    public void Test(){

    }*/