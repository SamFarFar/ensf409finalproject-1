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
    }

    // supporting function with
    private static boolean isEqual(Path firstFile, Path secondFile) {
        try {
            if (Files.size(firstFile) != Files.size(secondFile)) {
				System.out.println("File 1: " + Files.size(firstFile));
				System.out.println("File 2: " + Files.size(secondFile));
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
    @Test(expected = InvalidRequestException.class)
    public void testInvalidRequestConstructor() throws InvalidRequestException {
        String t = "Task";
        String f = "fff";
        int q = 1;
        Request testReq = new Request(t,f,q);

    }

    // tests Request Constructor & checkValidity with valid case
    @Test
    public void testRequestConstructor() throws InvalidRequestException {
       boolean tester = false;
        String t = "Mesh";
        String f = "chair";
        int q = 1;
        Request testReq = new Request(t,f,q);
        if(testReq.getType().equals(t) && testReq.getFurniture().equals(f) &&
        testReq.getQuantity() == 1){
            tester = true;
        }
        assertTrue(tester);
    }


    // ================================= \\
    // InventoryLink.java
    // ================================= \\

    // ff tt

    //(DATA BASE STUFF CANT DO RIGHT NOW)

    // Tests getValidParts with ID
    @Test
    public void validPartsTest(){
    String ID = "C0914";
    InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","matteo","pasquale");
    IL.initializeConnection();
    boolean[] tester = IL.getValidParts(ID);
    boolean[] actual = {false,false,true,true};
    assertArrayEquals(tester,actual);
    }
    // Tests getPrice with ID
    @Test
    public void validPriceTest(){
        String ID = "C0914";
        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","matteo","pasquale");
        IL.initializeConnection();
        int tester = IL.getPrice(ID);
        int actual = 50;
        assertEquals(tester,actual);
    }
    // Tests getManuID with valid ID
    @Test
    public void validManuIDTest(){
        String ID = "C0914";
        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","matteo","pasquale");
        IL.initializeConnection();
        String tester = IL.getManuID(ID);
        String actual = "002";
        assertEquals(tester,actual);
    }
    // Tests creating any furniture in the database
    @Test
    public void addFurnitureTest() throws InvalidRequestException {
		boolean bool = false;
        Lamp actual = new Lamp("L023","Desk",false,true,
                1,"001");
        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory",
                "matteo","pasquale");
        IL.initializeConnection();
        Request request = new Request("Desk","lamp",1);
        IL.addFurn(actual);
        ArrayList<String> tester = IL.getPossibleItems(request);
        IL.sort(tester);
        Lamp test = new Lamp(tester.get(0),request.getType(),
                IL.getValidParts(tester.get(0))[0],
                IL.getValidParts(tester.get(0))[1],
                IL.getPrice(tester.get(0)),IL.getManuID(tester.get(0)));
        if(test.getID().equals(actual.getID()) && test.getType().equals(actual.getType()) && 
			test.isBase() == actual.isBase() && test.isBulb() == actual.isBulb() && 
			test.getPrice() == actual.getPrice() && test.getManuID().equals(actual.getManuID())){
			bool = true;
		}
        assertTrue(bool);
        IL.deleteFurniture("L023");
    }
    // Tests deleting furniture in the database /
    @Test
    public void deleteFurnitureTest() throws InvalidRequestException {
        boolean test = false;
        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory",
                "matteo","pasquale");
        IL.initializeConnection();
        Request request = new Request("Desk","lamp",1);
        ArrayList<String> tester = IL.sort(IL.getPossibleItems(request));
        IL.deleteFurniture("L132");
        ArrayList<String> afterDelete = IL.sort(IL.getPossibleItems(request));
        if(tester.size() > afterDelete.size())
            test = true;
        assertTrue(test);
        Lamp toBeDeleted = new Lamp("L132","Desk",true,false,
                18,"005");
        IL.addFurn(toBeDeleted);
    }
        // Tests an invalidRequest response ////

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
