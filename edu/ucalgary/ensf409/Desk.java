package edu.ucalgary.ensf409;

public class Desk extends Furniture{
    private boolean Legs;
    private boolean Top;
    private boolean Drawer;
    private boolean Cushion;

    public Desk(int ID, String type, int price, String manuID){
        super(ID,type,price,manuID);
    }
    public void setLegs(boolean legs) {
        Legs = legs;
    }

    public boolean isLegs() {
        return Legs;
    }
}