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
	private final static String REGEX = "([a-zA-z]{1,9})\\s([a-zA-z]{1,9})[,]\\s([0-9])";
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
			// this will be edited out later when the SQL login is supplied
			System.out.println(DBURLINPUT);
			String url = scanner.nextLine();
			System.out.println(DBUSERINPUT);
			String user = scanner.nextLine();
			System.out.println(DBPASSINPUT);
			String pass = scanner.nextLine();
			System.out.println(PROMPTINPUT);
			Pattern pattern = Pattern.compile(scanner.nextLine());
			Matcher match = pattern.matcher(REGEX);

			if (match.find()) {
				
				String type = match.group(1).toLowerCase();
				originalRequest = type + " " + match.group(2).toLowerCase();
						type = type.substring(0,1).toUpperCase() + type.substring(1);
				if(type.equals("Swing arm")) {
					type = "Swing Arm";
				}
				Request userRequest = new Request(type,
								match.group(2).toLowerCase(),
								Integer.parseInt(match.group(3)));

				InventoryLink inLink = new InventoryLink(url,user,pass,userRequest);
				inLink.initializeConnection();

				ArrayList<String> possibleItems = inLink.getPossibleItems(userRequest);

				// sort by price first
				possibleItems = inLink.sort(possibleItems);
				
				ArrayList<int[]> retVal = getTwo(possibleItems, inLink);
				if(retVal.get(0)[0] == -1){
					retVal = getThree(possibleItems, inLink);
					if(retVal.get(0)[0] == -1){
						retVal = getFour(possibleItems, inLink);
						if(retVal.get(0)[0] == -1)
							inLink.invalidRequest(possibleItems);
					}
				}/*
				for(int i = 0; i < possibleItems.size(); i++){
					if(two[0] != -1 && two[0] != i && two[1] != i)
						possibleItems.remove(i);
					else if(three[0] != -1 && three[0] != i && three[1] != i && three[2] != i)
						possibleItems.remove(i);
					else if(four[0] != -1 && four[0] != i && four[1] != i && four[2] != i && four[3] != i)
						possibleItems.remove(i);
				}
*/
				int totalPrice = 0;
				for (int j = 0; j < possibleItems.size(); j++) {
					totalPrice = inLink.getPrice(possibleItems.get(j));
				}

				OrderForm out = new OrderForm(userRequest.getType() + userRequest.getFurniture(),
							userRequest.getQuantity(), possibleItems,totalPrice);
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
	 * By providing an arraylist of potential IDs and a link to the database
	 * @param pI
	 * @param inLink
	 * @return
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
					results.add(temp);
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
		ArrayList<int[]> results = new ArrayList<int[]>();
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
