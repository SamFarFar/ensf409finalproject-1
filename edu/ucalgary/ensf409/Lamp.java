package edu.ucalgary.ensf409;
public class Lamp extends Furniture {
    private boolean Base;
    private boolean Bulb;

    public Lamp(String ID, String type, int price, String manuID) {
        super(ID, type, price, manuID);
    }

    public Lamp(String ID, String type, boolean base, boolean bulb, 
					int price, String manuID) {
        super(ID, type, price, manuID);
        setBase(base);
        setBulb(bulb);
    }
    
    public Lamp(String ID, String type, boolean base, boolean bulb, int price) {
        super(ID, type, price);
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
