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

	public static void main(String[] args) {
		Scanner scanner = null;
		
		try{
			scanner = new Scanner(System.in);
			System.out.println(WELCOME);
			System.out.println(DBURLINPUT);
			String url = scanner.nextLine();
			System.out.println(DBUSERINPUT);
			String user = scanner.nextLine();
			System.out.println(DBPASSINPUT);
			String pass = scanner.nextLine();
			System.out.println(PROMPTINPUT);
			Pattern pattern = Pattern.compile(scanner.nextLine());
			Matcher match = pattern.matcher(REGEX);
			if(!match.find()){
				throw new CommandArgumentNotProvidedException();
			}else{
				String type = match.group(1).toLowerCase();
				type = type.substring(0,1).toUpperCase() + type.substring(1);
				Request userRequest = new Request(type,
								match.group(2).toLowerCase(),
								Integer.parseInt(match.group(3)));
				InventoryLink inLink = new InventoryLink(url,user,pass);
				inLink.initializeConnection();
				ArrayList<String> possibleItems = inLink.getPossibleItems(userRequest);
				for (int i = 0; i < possibleItems.size(); i++) {
					
					boolean[] parts = inLink.getValidParts(possibleItems.get(i));
					if(userRequest.getFurniture().equals("chair")){
						Chair ch = new Chair();
					}
					
				}
			}
		}catch(Exception e){
			System.out.println("Error occurred");
			e.printStackTrace();
		}
	}

	public static String getREGEX() {
		return REGEX;
	}
}
