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
	
	public Request(String t, String f, int q){
		checkValidity(t,f,q);
		type = t;
		furniture = f;
		quantity = q;

	}
	
	private void checkValidity(String t, String f, int q){
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
		if(q < 0){
			valid = false;
		}
		if(!valid){
			throw new IllegalArgumentException("Invalid Request");
		}
	}
	
	public int getPartNum(){
		return partNum;
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
