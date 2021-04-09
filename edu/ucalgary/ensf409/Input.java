package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {
	private static final String WELCOME = "~~~~~~~~ Welcome ~~~~~~~~~";
	private static final String DBURLINPUT = "Please enter a database URL to access:";
	private static final String DBUSERINPUT = " Please enter a database username to access database:";
	private static final String DBPASSINPUT ="Please enter a password to the account:";
	private static final String PROMPTINPUT = "What would you like to make today?" +
			"\nPlease specify below your request in the format of:\n"+
			"<Type> <Furniture>, <Amount>\n" + "Example: mesh chair, 1";
	private final static String REGEX = "([a-zA-z]{1,99})\\s([a-zA-z]{1,99}),\\s([0-9]{1,9})";
	private static String originalRequest;

	/**
	 * Basic main function that runs through the program.
	 * @param args unused arguments that need to be supplied
	 */
	public static void main(String[] args) {
		Scanner scanner = null;
		
		try{
			scanner = new Scanner(System.in);
			System.out.println(WELCOME);
			String url = "jdbc:mysql://localhost/inventory"; //URL
			System.out.println(DBUSERINPUT);
			String user = scanner.nextLine(); // user username
			System.out.println(DBPASSINPUT);
			String pass = scanner.nextLine(); // user password
			System.out.println(PROMPTINPUT);
			String in = scanner.nextLine();
			Pattern pattern = Pattern.compile(REGEX);
			Matcher match = pattern.matcher(in);
			if (match.find()) {
				String swingArm = in.substring(0,9).toLowerCase();
				String type = match.group(1).toLowerCase();
				if(swingArm.equals("swing arm")){
					type = "Swing Arm";
				}
				originalRequest = type + " " + match.group(2).toLowerCase();
				type = type.substring(0,1).toUpperCase() + type.substring(1);
				Request userRequest = new Request(type,
								match.group(2).toLowerCase(),
								Integer.parseInt(match.group(3)));

				InventoryLink inLink = new InventoryLink(url,user,pass);
				inLink.initializeConnection();
				
				ArrayList<Chair> chairs = new ArrayList<>();
				ArrayList<Lamp> lamps = new ArrayList<>();
				ArrayList<Desk> desks = new ArrayList<>();
				ArrayList<Filing> filings = new ArrayList<>();
				ArrayList<String> finalVals = new ArrayList<String>();
				
				int totalPrice = 0;
				for(int furnNum = 0; furnNum < userRequest.getQuantity(); furnNum++){
					// Loop for multiple pieces of furniture starts here
					ArrayList<String> possibleItems = inLink.getPossibleItems(userRequest);
					
					// sort by price first
					possibleItems = inLink.sort(possibleItems);
					ArrayList<int[]> results = getTwo(possibleItems, inLink);
					if(results.get(0)[0] == -1){
						results = getThree(possibleItems, inLink);
						if(results.get(0)[0] == -1){
							results = getFour(possibleItems, inLink);
							if(results.get(0)[0] == -1){
								inLink.invalidRequest(userRequest, chairs, 
										lamps, desks, filings);
							}
						}
					}
					
					for(int i : results.get(0)){
						finalVals.add(possibleItems.get(i));
					}
					if(finalVals.get(0).equals(finalVals.get(1))){
						finalVals.remove(1);
					}
					
					for (int j = 0; j < finalVals.size(); j++) {

						if(userRequest.getFurniture().equals("chair")){

							Chair temp = new Chair(finalVals.get(j),userRequest.getType(),
									inLink.getValidParts(finalVals.get(j))[0],inLink.getValidParts(finalVals.get(j))[1],
									inLink.getValidParts(finalVals.get(j))[2],inLink.getValidParts(finalVals.get(j))[3],
									inLink.getPrice(finalVals.get(j)),inLink.getManuID(finalVals.get(j)));
							chairs.add(temp);
						}
						if(userRequest.getFurniture().equals("lamp")){
							Lamp temp = new Lamp(finalVals.get(j),userRequest.getType(),inLink.getValidParts(finalVals.get(j))[0]
									,inLink.getValidParts(finalVals.get(j))[1],inLink.getPrice(finalVals.get(j)),inLink.getManuID(finalVals.get(j)));
							lamps.add(temp);
						}
						if(userRequest.getFurniture().equals("desk")){
							Desk temp = new Desk(finalVals.get(j),userRequest.getType(),
									inLink.getValidParts(finalVals.get(j))[0],inLink.getValidParts(finalVals.get(j))[1],inLink.getValidParts(finalVals.get(j))[2],
									inLink.getPrice(finalVals.get(j)),inLink.getManuID(finalVals.get(j)));
							desks.add(temp);
						}
						if(userRequest.getFurniture().equals("filing")){
							Filing temp = new Filing(finalVals.get(j),userRequest.getType(),
									inLink.getValidParts(finalVals.get(j))[0],inLink.getValidParts(finalVals.get(j))[1],inLink.getValidParts(finalVals.get(j))[2],
									inLink.getPrice(finalVals.get(j)),inLink.getManuID(finalVals.get(j)));
							filings.add(temp);
						}
						totalPrice += inLink.getPrice(finalVals.get(j));
						inLink.deleteFurniture(finalVals.get(j));
					}
				}

				OrderForm out = new OrderForm(userRequest.getType() + " " + userRequest.getFurniture(),
							userRequest.getQuantity(), finalVals,totalPrice);
				out.printOrderForm();
				inLink.close();

			} else {
				throw new InvalidRequestException();
			}
		}catch(Exception e){
			System.out.println("Error occurred");
			e.printStackTrace();
		}
	}

	/**
	 * Returns the REGEX data member of class
	 * @return REGEX data member
	 */
	public static String getREGEX() {
		return REGEX;
	}

	/**
	 * By providing an ArrayList of potential IDs and a link to the database, 
	 * we are able to 
	 * @param pI String ArrayList of all possible ID's
	 * @param inLink InventoryLink object, containing access to the database 
	 * to be used
	 * @return an ArrayList of integer arrays, wherein the integer arrays contain 
	 * numbers corresponding to specific indexs of the pI parameter to be used
	 */
	public static ArrayList<int[]> getTwo(ArrayList<String> pI, InventoryLink inLink){
		ArrayList<int[]> results = new ArrayList<int[]>();
		for(int i = 0; i < pI.size(); i++){
			for(int j = 0; j < pI.size(); j++){
				boolean[] one = inLink.getValidParts(pI.get(i));
				boolean[] two = inLink.getValidParts(pI.get(j));
				if(combine(one,two)){
					int[] temp = new int[2];
					temp[0] = i;
					temp[1] = j;
					if(i == j){
						results.set(0, temp);
					} else {
						results.add(temp);
					}
				}
			}
		}
		if(results.size() == 0){
			int[] fail = {-1,-1};
			results.add(fail);
		}
		return results;
	}

	/**
	 *
	 * @param pI
	 * @param inLink
	 * @return
	 */
	public static ArrayList<int[]> getThree(ArrayList<String> pI, InventoryLink inLink){
		ArrayList<int[]> results = new ArrayList<>();
		for(int m = 0; m < pI.size(); m++){
			for(int i = 0; i < pI.size(); i++){
				for(int j = 0; j < pI.size(); j++){
					boolean[] one = inLink.getValidParts(pI.get(m));
					boolean[] two = inLink.getValidParts(pI.get(i));
					boolean[] three = inLink.getValidParts(pI.get(j));
					if(combine(one,two,three)){
						int[] temp = new int[3];
						temp[0] = m;
						temp[1] = i;
						temp[2] = j;
						results.add(temp);
					}
				}
			}
		}
		if(results.size() == 0){
			int[] fail = {-1,-1,-1};
			results.add(fail);
		}
		return results;
	}

	public static ArrayList<int[]> getFour(ArrayList<String> pI, InventoryLink inLink){
		ArrayList<int[]> results = new ArrayList<int[]>();
		for(int n = 0; n < pI.size(); n++){
			for(int m = 0; m < pI.size(); m++){
				for(int i = 0; i < pI.size(); i++){
					for(int j = 0; j < pI.size(); j++){
						boolean[] one = inLink.getValidParts(pI.get(m));
						boolean[] two = inLink.getValidParts(pI.get(i));
						boolean[] three = inLink.getValidParts(pI.get(j));
						boolean[] four = inLink.getValidParts(pI.get(n));
						if(combine(one,two,three,four)){
							int[] temp = new int[4];
							temp[0] = n;
							temp[1] = m;
							temp[2] = i;
							temp[3] = j;
							results.add(temp);
						}
					}
				}
			}
		}
		if(results.size() == 0){
			int[] fail = {-1,-1,-1,-1};
			results.add(fail);
		}
		return results;
	}

	private static boolean combine(boolean[] one, boolean[] two){
		boolean retVal = true;
		for(int i = 0; i < one.length; i++){
			if(!(one[i] || two[i])){
				retVal = false;
			}
		}
		return retVal;
	}

	private static boolean combine(boolean[] one, boolean[] two, boolean[] three){
		boolean retVal = true;
		for(int i = 0; i < one.length; i++){
			if(!(one[i] || two[i] || three[i])){
				retVal = false;
			}
		}
		return retVal;
	}
	
	private static boolean combine(boolean[] one, boolean[] two, boolean[] three, boolean[] four){
		boolean retVal = true;
		for(int i = 0; i < one.length; i++){
			if(!(one[i] || two[i] || three[i] || four[i])){
				retVal = false;
			}
		}
		return retVal;
	}
}
