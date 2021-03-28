package edu.ucalgary.ensf409;

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
