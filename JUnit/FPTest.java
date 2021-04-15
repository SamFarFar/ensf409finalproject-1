/**
@author Matteo Morrone <a href = "mailto:matteo.morrone@ucalgary.ca">
	matteo.morrone@ucalgary.ca</a>
@author Sam FarzamFar <a href = "mailto:sam.farzamfar@ucalgary.ca">
	sam.farzamfar@ucalgary.ca</a>
@author Sandip Mishra <a href = "mailto:sandip.mishra@ucalgary.ca">
	sandip.mishra@ucalgary.ca</a>
@version 9.8
@since 1.0
*/

package JUnit;
import edu.ucalgary.ensf409.*;

import org.junit.Test;
import static org.junit.Assert.*;


import java.io.*;
import java.util.ArrayList;



public class FPTest {
    // ================================= \\
    // OrderForm.java
    // ================================= \\

    /**
     * Tests the creation of an OrderForm object by using its getters to 
     * compare values with what was passed to it.
     */
    @Test
    public void testOrderFormConstructor(){
        String testStringName = "mesh chair";
        int testAmount = 1;
        ArrayList<String> testList = new ArrayList<>();
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

    /** 
     * tests the creation of the OrderForm.txt file using the getIsFormCreated
     * method.
     */
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
	
	/**
	 * calls compare() function at the end of this file in order to ensure
	 * that the produced order form is exactly as expected.
     * @throws IOException thrown if files cannot be accessed.
	 */
    @Test
    public void testOrderForm() throws IOException {
        assertTrue(compare());
    }

    // ================================= \\
               // Request.java \\
    // ================================= \\


    /**
     * Tests the constructor of the InvalidRequestException exception class 
     * by passing invalid arguments to the Request constructor
     * @throws InvalidRequestException thrown if request is not valid.
     */

    @Test(expected = InvalidRequestException.class)
    public void testInvalidRequestConstructor() throws InvalidRequestException {
        String t = "Task";
        String f = "fff";
        int q = 1;
        Request testReq = new Request(t,f,q);

    }

    /**
     * Tests Request Constructor & checkValidity with a valid test case
     * @throws InvalidRequestException thrown if request is not valid.
     */
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
    
    /**
     * Tests if initializeConnection() function properly connects to 
     * database
     * @throws InvalidRequestException thrown if request is not valid.
     */
    @Test
    public void connectionTest() throws InvalidRequestException {
    boolean bool = false;
    InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","scm","ensf409");
    IL.initializeConnection();
    Request request = new Request("Desk","lamp",1);
    ArrayList<String> tester = IL.getPossibleItems(request);
    if(tester.size() != 0)
        bool = true;
    assertTrue(bool);
	}
	
    /** 
     * Tests getValidParts with a valid ID in the DB
     */
    @Test
    public void validPartsTest(){
    String ID = "C0914";
        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","scm","ensf409");
        IL.initializeConnection();
    boolean[] tester = IL.getValidParts(ID);
    boolean[] actual = {false,false,true,true};
    assertArrayEquals(tester,actual);
    }
    
    /** 
     * Tests getPrice with a valid ID in the DB
     */
    @Test
    public void validPriceTest(){
        String ID = "C0914";
        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","scm","ensf409");
        IL.initializeConnection();
        int tester = IL.getPrice(ID);
        int actual = 50;
        assertEquals(tester,actual);
    }
    
    /** 
     * Tests getManuID with valid ID in the DB
     */
    @Test
    public void validManuIDTest(){
        String ID = "C0914";
        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","scm","ensf409");
        IL.initializeConnection();
        String tester = IL.getManuID(ID);
        String actual = "002";
        assertEquals(tester,actual);
    }
    
    /** 
     * Tests creating any furniture in the database using the addFurn function
     * @throws InvalidRequestException thrown if request is not valid.
     */
    @Test
    public void addFurnitureTest() throws InvalidRequestException {
		boolean bool = false;
        Lamp actual = new Lamp("L023","Desk",false,true,
                1,"001");
        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","scm","ensf409");
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
    
    /** 
     * Tests deleting furniture in the database using the deleteFurniture 
     * function.
     * @throws InvalidRequestException thrown if request is not valid.
     */
    @Test
    public void deleteFurnitureTest() throws InvalidRequestException {
        boolean test = false;
        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","scm","ensf409");
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

	/**
	 * Tests an invalidRequest response when Request cannot be fulfilled by inventory.
     * @throws InvalidRequestException thrown if request is not valid.
     * @throws TerminatorT1000Exception when Request cannot be fulfilled by inventory.
	 */
    @Test(expected = TerminatorT1000Exception.class)
    public void invalidRequestTest() throws InvalidRequestException, TerminatorT1000Exception {
        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","scm","ensf409");
		IL.initializeConnection();
		Request request = new Request("Desk","lamp",1);
		ArrayList<String> tester = IL.getPossibleItems(request);
		IL.invalidRequest(request, new ArrayList<>(), new ArrayList<>(),
        new ArrayList<>(), new ArrayList<>());
	}
	

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

	/**
	 * Test the scenario in which 2 different pieces of furniture must be used
	 * to fulfill a given request
     * @throws InvalidRequestException thrown if request is not valid.
	 */
    @Test
    public void testGet2() throws InvalidRequestException {
        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","scm","ensf409");
        IL.initializeConnection();
        Request request = new Request("Study","lamp",1);
        ArrayList<String> tester = IL.sort(IL.getPossibleItems(request));
        ArrayList<int[]> tester2 = Input.getTwo(tester,IL);
        int[] comp = new int[]{3,3};
        boolean res = true;
        for(int j = 0; j < 2; j++){
			if(tester2.get(0)[j] != comp[j]){
				res = false;
			}
		}
        assertTrue(res);
    }
    /**
     * testGet3 tests the scenario in which there are 3 different pieces of furniture
     * must be used in order to create the specified request.
     * @throws InvalidRequestException thrown if request is not valid.
     */
    @Test
    public void testGet3() throws InvalidRequestException {
        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","scm","ensf409");
        IL.initializeConnection();
        Request request = new Request("Medium","filing",1);
        ArrayList<String> tester = IL.sort(IL.getPossibleItems(request));
        ArrayList<int[]> tester2 = Input.getThree(tester,IL);
        int[] comp = new int[]{0,0,2};
        boolean res = true;
        for(int j = 0; j < 3; j++){
			if(tester2.get(0)[j] != comp[j]){
				res = false;
			}
		}
        assertTrue(res);
    }
    /**
     * testGet4 tests the scenario in which there are 4 different pieces
     * of furniture must be used
     * in order to create the specified request.
     * @throws InvalidRequestException thrown if request is not valid.
     */
    @Test
    public void testGet4() throws InvalidRequestException {
        InventoryLink IL = new InventoryLink("jdbc:mysql://localhost/inventory","scm","ensf409");
        IL.initializeConnection();
        Request request = new Request("Task","chair",1);
        ArrayList<String> tester = IL.sort(IL.getPossibleItems(request));
        ArrayList<int[]> tester2 = Input.getFour(tester,IL);
		int[] comp = new int[]{0,0,0,1};
        boolean res = true;
        for(int j = 0; j < 4; j++){
			if(tester2.get(0)[j] != comp[j]){
				res = false;
			}
		}
        assertTrue(res);
    }
    /**
     * testCombine2 takes in 2 boolean arrays and will test if
     * the combine function that has 2 inputs
     * works properly.
     */
    @Test
    public void testCombine2() {
        boolean[] one = {true, false, true};
        boolean[] two = {false, true, false};
        assertTrue(Input.combine(one,two));
    }
    /**
     * testCombine2Fail takes in 2 boolean arrays and
     * will test if the combine function that has 2 inputs
     * works properly if there is a fail.
     */
    @Test
    public void testCombine2Fail() {
        boolean[] one = {true, false, true};
        boolean[] two = {false, false, false};
        assertFalse(Input.combine(one,two));
    }
    /**
     * testCombine3 takes in 3 boolean arrays and will test
     * if the combine function that has 3 inputs
     * works properly.
     */
    @Test
    public void testCombine3() {
        boolean[] one = {true, false, false};
        boolean[] two = {false, true, false};
        boolean[] three = {false, true, true};
        assertTrue(Input.combine(one,two,three));
    }
    /**
     * testCombine3Fail takes in 3 boolean arrays and will test
     * if the combine function that has 3 inputs
     * works properly if there is a fail.
     */
    @Test
    public void testCombine3Fail() {
        boolean[] one = {true, false, false};
        boolean[] two = {false, true, false};
        boolean[] three = {false, true, false};
        assertFalse(Input.combine(one,two,three));
    }
    /**
     * testCombine4 takes in 4 boolean arrays and will test if
     * the combine function that has 4 inputs
     * works properly.
     */
    @Test
    public void testCombine4() {
        boolean[] one = {true, false, false,false};
        boolean[] two = {true, false, true,false};
        boolean[] three = {true, false, false,false};
        boolean[] four = {true, true, false,true};
        assertTrue(Input.combine(one,two,three,four));
    }
    /**
     * testCombine4Fail takes in 4 boolean arrays and will test if
     * the combine function that has 4 inputs
     * works properly if there is a fail.
     */
    @Test
    public void testCombine4Fail() {
        boolean[] one = {true, false, false,false};
        boolean[] two = {true, false, false,false};
        boolean[] three = {true, false, false,false};
        boolean[] four = {true, false, false,false};
        assertFalse(Input.combine(one,two,three,four));
    }


    // ================================= \\
    // Furniture Constructors
    // ================================= \\
    /**
     * testLampConstructor is a test that makes sure
     * that each Lamp object is being constructed correctly.
     */
    @Test
    public void testChairConstructor() {
        boolean isTrue = false;
        Chair test = new Chair("test","chair",true,true,true,true,1,"123");
        if(test.isArms() && test.isCushion() && test.isLegs() && test.isSeat()
        && test.getManuID().equals("123") && test.getID().equals("test") && test.getManuID().equals("123")) {
            isTrue = true;
        }
        assertTrue(isTrue);
    }
    /**
     * testChairConstructor is a test that makes sure that
     * each Chair object is being constructed correctly.
     */
    @Test
    public void testDeskConstructor() {
        boolean isTrue = false;
        Desk test = new Desk("test","desk",true,true,true,1,"123");
        if(test.isDrawer() && test.isLegs()  && test.isTop() && test.getManuID().equals("123")
            && test.getID().equals("test") && test.getType().equals("desk")) {
            isTrue = true;
         }
        assertTrue(isTrue);
    }
    /**
     * testFilingConstructor is a test that makes sure that each
     * Filing object is being constructed correctly.
     */
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
    /**
     * testLampConstructor is a test that makes sure that each Lamp
     * object is being constructed correctly.
     */
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

    /**
     * The compare method creates an orderForm that can be
     * tested against a pre-made orderForm called Sample.txt.
     * @return returns whether or not the files are the same.
     * @throws IOException thrown if files cannot be accessed.
     */
	public boolean compare() throws IOException {
        String testStringName = "mesh chair";
        int testAmount = 1;
        ArrayList<String> testList  = new ArrayList<>();
        testList.add("C123");
        testList.add("C234");
        testList.add("C567");
        int testTotalPrice = 100;
        OrderForm testForm = new OrderForm(testStringName, testAmount, testList, testTotalPrice);
        testForm.printOrderForm();
        BufferedReader order = new BufferedReader(new FileReader("OrderForm.txt"));
        BufferedReader sample = new BufferedReader(new FileReader("./JUnit/Sample.txt"));
        int a, b;
        while(true){
            a = order.read();
            b = sample.read();
            if(a==-1 && b==-1)
                return true;
            else if(a!=b){
                return false;
            }
        }
    }

}
