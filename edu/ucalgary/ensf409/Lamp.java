package edu.ucalgary.ensf409;

public class Lamp extends Furniture {
	
    private boolean Base;
    private boolean Bulb;
	
	/**
	 * Basic constructor to create an instance of Lamp object, 
	 * calls on constructor of Furniture parent class.
	 * @param ID String ID used to identify the item
	 * @param type String type used to classify the item
	 * @param base boolean value indicating availability of base
	 * @param bulb boolean value indicating availability of bulb
	 * @param price integer representation of the cost of the item
	 * @param manuID String ManuID used to identify the item's manufacturer
	 */
    public Lamp(String ID, String type, boolean base, boolean bulb, 
					int price, String manuID) {
        super(ID, type, price, manuID);
        setBase(base);
        setBulb(bulb);
    }

    public boolean isBase() {
        return Base;
    }

    public boolean isBulb() {
        return Bulb;
    }

    public void setBase(boolean base) {
        Base = base;
    }

    public void setBulb(boolean bulb) {
        Bulb = bulb;
    }
    
}
