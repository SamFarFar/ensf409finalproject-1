package edu.ucalgary.ensf409;

public class Request {
	private static String type;
	private static String furniture;
	private static int quantity;

	
	public Request(String t, String f, int q){
		type = t;
		furniture = f;
		quantity = q;
	}
	
	public String getType() {
		return type;
	}
	
	public String getFurniture() {
		return furniture;
	}
	
	public int getQuantity() {
		return quantity;
	}

}
