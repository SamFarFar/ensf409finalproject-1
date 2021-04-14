package JUnit;
import edu.ucalgary.ensf409.*;

import org.junit.Test;
import static org.junit.Assert.*;


import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;


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
    @Test
    public void connectionTest() throws InvalidRequestException {
    boolean bool =false;
    InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","root","Kathmandu1");
    IL.initializeConnection();
    Request request = new Request("Desk","lamp",1);
    ArrayList<String> tester = IL.getPossibleItems(request);
    if(tester.size() != 0)
        bool = true;
    assertTrue(bool);
}
    // Tests getValidParts with ID
    @Test
    public void validPartsTest(){
    String ID = "C0914";
        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","root","Kathmandu1");
        IL.initializeConnection();
    boolean[] tester = IL.getValidParts(ID);
    boolean[] actual = {false,false,true,true};
    assertArrayEquals(tester,actual);
    }
    // Tests getPrice with ID
    @Test
    public void validPriceTest(){
        String ID = "C0914";
        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","root","Kathmandu1");
        IL.initializeConnection();
        int tester = IL.getPrice(ID);
        int actual = 50;
        assertEquals(tester,actual);
    }
    // Tests getManuID with valid ID
    @Test
    public void validManuIDTest(){
        String ID = "C0914";
        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","root","Kathmandu1");
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
        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","root","Kathmandu1");
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
        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","root","Kathmandu1");
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
    // Not done dont know how to test if program does System.exit(1);

//    @Test
//    public void invalidRequestTest() throws InvalidRequestException {
//        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","root","Kathmandu1");
//    IL.initializeConnection();
//    Request request = new Request("Desk","lamp",1);
//    ArrayList<String> tester = IL.getPossibleItems(request);
//    IL.invalidRequest(request, new ArrayList<>(), new ArrayList<>(),
//            new ArrayList<>(), new ArrayList<>());
//}

    // Tests the filter with valid ArrayList /

    // Tests the sort algorithm with valid ArrayList /



    // ================================= \\
    // InvalidIDException.java
    // ================================= \\

    // (Isn't utilized yet)
    //Tests a scenario where an InvalidIDException is thrown

    // ================================= \\
    // Input.java
    // ================================= \\

    // get2

    @Test
    public void testGet2() throws InvalidRequestException {
        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","root","Kathmandu1");
        IL.initializeConnection();
        IL.deleteFurniture("L223");
        IL.deleteFurniture("L928");
        Request request = new Request("Study","lamp",1);
        ArrayList<String> tester = IL.sort(IL.getPossibleItems(request));
        ArrayList<int[]> tester2 = Input.getTwo(tester,IL);
        ArrayList<int[]> compare = new ArrayList<int[]>();
        compare.add(new int[]{1,0});
        compare.add(new int[]{0,1});
        ArrayList<String> one = convertToStringList2(tester2);
        ArrayList<String> two = convertToStringList2(compare);
        Collections.sort(one);
        Collections.sort(two);
        Lamp deleted1 = new Lamp("L223","Study",false,true, 2,"005");
        Lamp deleted2 = new Lamp("L928","Study",true,true, 10,"002");
        IL.addFurn(deleted1);
        IL.addFurn(deleted2);
        assertTrue(one.equals(two));
    }

    public ArrayList<String> convertToStringList2(ArrayList<int[]> sample) {
        ArrayList<String> returning = new ArrayList<String>();
        for(int i = 0; i<sample.size();i++) {
            int[] tempArr = sample.get(i);
            String a = Integer.toString(tempArr[0]) + Integer.toString(tempArr[1]);
            returning.add(a);
        }
        return returning;
    }

    @Test
    public void testGet3() throws InvalidRequestException {
        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","root","Kathmandu1");
        IL.initializeConnection();
        IL.deleteFurniture("F002");
        IL.deleteFurniture("F009");
        IL.deleteFurniture("F014");
        Request request = new Request("Medium","filing",1);
        ArrayList<String> tester = IL.sort(IL.getPossibleItems(request));
        ArrayList<int[]> tester2 = Input.getThree(tester,IL);
        ArrayList<int[]> compare = new ArrayList<int[]>();
        compare.add(new int[]{0,0,1});
        compare.add(new int[]{0,1,0});
        compare.add(new int[]{0,1,1});
        compare.add(new int[]{1,0,0});
        compare.add(new int[]{1,0,1});
        compare.add(new int[]{1,1,0});
        ArrayList<String> one = convertToStringList3(tester2);
        ArrayList<String> two = convertToStringList3(compare);
        Collections.sort(one);
        Collections.sort(two);
        Filing deleted1 = new Filing("F002","Medium",false,false,true,100,"004");
        Filing deleted2 = new Filing("F009","Medium",true,true,false,150,"004");
        Filing deleted3 = new Filing("F014","Medium",true,true,true,200,"002");
        IL.addFurn(deleted1);
        IL.addFurn(deleted2);
        IL.addFurn(deleted3);
        assertTrue(one.equals(two));
    }

    public ArrayList<String> convertToStringList3(ArrayList<int[]> sample) {
        ArrayList<String> returning = new ArrayList<String>();
        for(int i = 0; i<sample.size();i++) {
            int[] tempArr = sample.get(i);
            String a = Integer.toString(tempArr[0]) + Integer.toString(tempArr[1]) +  Integer.toString(tempArr[2]);
            returning.add(a);
        }
        return returning;
    }

    @Test
    public void testGet4() throws InvalidRequestException {
        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","root","Kathmandu1");
        IL.initializeConnection();
        IL.deleteFurniture("C1148");
        Request request = new Request("Task","chair",1);
        ArrayList<String> tester = IL.sort(IL.getPossibleItems(request));
        ArrayList<int[]> tester2 = Input.getFour(tester,IL);
        ArrayList<int[]> compare = new ArrayList<int[]>();
        compare.add(new int[]{0,0,0,1});
        compare.add(new int[]{0,0,1,0});
        compare.add(new int[]{0,0,1,1});
        compare.add(new int[]{0,1,0,0});
        compare.add(new int[]{0,1,0,1});
        compare.add(new int[]{0,1,1,0});
        compare.add(new int[]{0,1,1,1});
        compare.add(new int[]{1,0,0,0});
        compare.add(new int[]{1,0,0,1});
        compare.add(new int[]{1,0,1,0});
        compare.add(new int[]{1,0,1,1});
        compare.add(new int[]{1,1,0,0});
        compare.add(new int[]{1,1,0,1});
        compare.add(new int[]{1,1,1,1});
        ArrayList<String> one = convertToStringList3(tester2);
        ArrayList<String> two = convertToStringList3(compare);
        Collections.sort(one);
        Collections.sort(two);
        Chair deleted1 = new Chair("C1148","Task",true,false,true,true,125,"003");
        IL.addFurn(deleted1);
        assertTrue(one.equals(two));
    }

    public ArrayList<String> convertToStringList4(ArrayList<int[]> sample) {
        ArrayList<String> returning = new ArrayList<String>();
        for(int i = 0; i<sample.size();i++) {
            int[] tempArr = sample.get(i);
            String a = Integer.toString(tempArr[0]) + Integer.toString(tempArr[1]) +  Integer.toString(tempArr[2]) +  Integer.toString(tempArr[3]);
            returning.add(a);
        }
        return returning;
    }


    @Test
    public void testCombine2() {
        boolean one[] = {true, false, true};
        boolean two[] = {false, true, false};
        assertTrue(Input.combine(one,two));
    }

    @Test
    public void testCombine2Fail() {
        boolean one[] = {true, false, true};
        boolean two[] = {false, false, false};
        assertFalse(Input.combine(one,two));
    }

    @Test
    public void testCombine3() {
        boolean one[] = {true, false, false};
        boolean two[] = {false, true, false};
        boolean three[] = {false, true, true};
        assertTrue(Input.combine(one,two,three));
    }

    @Test
    public void testCombine3Fail() {
        boolean one[] = {true, false, false};
        boolean two[] = {false, true, false};
        boolean three[] = {false, true, false};
        assertFalse(Input.combine(one,two,three));
    }

    @Test
    public void testCombine4() {
        boolean one[] = {true, false, false,false};
        boolean two[] = {true, false, true,false};
        boolean three[] = {true, false, false,false};
        boolean four[] = {true, true, false,true};
        assertTrue(Input.combine(one,two,three,four));
    }

    @Test
    public void testCombine4Fail() {
        boolean one[] = {true, false, false,false};
        boolean two[] = {true, false, false,false};
        boolean three[] = {true, false, false,false};
        boolean four[] = {true, false, false,false};
        assertFalse(Input.combine(one,two,three,four));
    }


    // ================================= \\
    // Furniture Constructors
    // ================================= \\

    @Test
    public void testChairConstructor() {
        boolean isTrue = false;
        Chair test = new Chair("test","chair",true,true,true,true,1,"123");
        if(test.isArms() == true && test.isCushion() == true && test.isLegs() == true && test.isSeat() == true
        && test.getManuID().equals("123") && test.getID().equals("test") && test.getManuID().equals("123")) {
            isTrue = true;
        }
        assertTrue(isTrue);
    }

    @Test
    public void testDeskConstructor() {
        boolean isTrue = false;
        Desk test = new Desk("test","desk",true,true,true,1,"123");
        if(test.isDrawer() == true && test.isLegs() == true && test.isTop() == true && test.getManuID().equals("123")
            && test.getID().equals("test") && test.getType().equals("desk")) {
            isTrue = true;
         }
        assertTrue(isTrue);
    }

    @Test
    public void testFilingConstructor() {
        boolean isTrue = false;
        Filing test = new Filing("test","filing",true,true,true,1,"123");
        if(test.isCabinet() == true && test.isDrawers() == true && test.isRails() == true && test.getManuID().equals("123")
        && test.getID().equals("test") && test.getType().equals("filing")) {
            isTrue = true;
        }
        assertTrue(isTrue);
    }

    @Test
    public void testLampConstructor() {
        boolean isTrue = false;
        Lamp test = new Lamp("test", "lamp",true,true,1,"123");
        if(test.isBase() == true && test.isBulb() == true && test.getID().equals("test") && test.getPrice() == 1
        && test.getManuID().equals("123") && test.getType().equals("lamp")) {
            isTrue = true;
        }
        assertTrue(isTrue);
    }



}
// copy this as a blank test
  /*  @Test
    public void Test(){

    }*/
