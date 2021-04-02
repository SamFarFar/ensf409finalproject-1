package edu.ucalgary.ensf409;

import java.lang.IllegalArgumentException;
/*
enum FurnTypes {
	CHAIR {
		public String toString(){ return "chair"; }
	},
	DESK {
		public String toString(){ return "desk"; }
	},
	FILING {
		public String toString(){ return "filing"; }
	},
	LAMP {
		public String toString(){ return "lamp"; }
	};
	public abstract String toString();
}
*/

public class Request {
	private static String type;
	private static String furniture;
	private static int quantity;
	private static int partNum;

	/**
	 * Standard constructor for the Request class that sets the type, furniture and quantity
	 * @param t type of furniture
	 * @param f what kind of furniture
	 * @param q how many furniture pieces are requested
	 * @throws InvalidRequestException
	 */
	public Request(String t, String f, int q) throws InvalidRequestException {
		checkValidity(t,f,q);
		type = t;
		furniture = f;
		quantity = q;

	}

	/**
	 * This method checks if the request received are for furniture pieces and types
	 * that are valid and checks if there is a quantity less than 1.
	 * @param t type of furniture from request
	 * @param f what kind of furniture from request
	 * @param q how many furniture pieces are requested from request
	 * @throws InvalidRequestException
	 */
	private void checkValidity(String t, String f, int q) throws InvalidRequestException {
		boolean valid = false;
		switch(f){
			case "chair":
				if(t.equals("Task") || t.equals("Mesh") || t.equals("Kneeling") || 
					t.equals("Executive") || t.equals("Ergonomic")){
					valid = true;
				}
				partNum = 4;
				break;
			case "desk":
				if(t.equals("Traditional") || t.equals("Adjustable") || t.equals("Standing")){
					valid = true;
				}
				partNum = 3;
				break;
			case "filing":
				if(t.equals("Small") || t.equals("Medium") || t.equals("Large")){
					valid = true;
				}
				partNum = 3;
				break;
			case "lamp":
				if(t.equals("Desk") || t.equals("Swing Arm") || t.equals("Study")){
					valid = true;
				}
				partNum = 2;
				break;
			default:
				partNum = 0;
				break;
		}
		if(q <= 0){
			valid = false;
		}
		if(!valid){
			throw new InvalidRequestException();
		}
	}

	/**
	 * getter for the type String
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * getter fpr furniture String
	 * @return furniture
	 */
	public String getFurniture() {
		return furniture;
	}

	/**
	 * getter for the quantity
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}

}
