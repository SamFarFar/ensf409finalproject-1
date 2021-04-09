package edu.ucalgary.ensf409;

public class Desk extends Furniture{
	
    private boolean Legs;
    private boolean Top;
    private boolean Drawer;

    /**
	 * Basic constructor to create an instance of Desk object, 
	 * calls on constructor of Furniture parent class.
	 * @param ID String ID used to identify the item
	 * @param type String type used to classify the item
	 * @param legs boolean value indicating availability of legs
	 * @param top boolean value indicating availability of top
	 * @param drawer boolean value indicating availability of drawer
	 * @param price integer representation of the cost of the item
	 * @param manuID String ManuID used to identify the item's manufacturer
	 */
    public Desk(String ID,String type, boolean legs,boolean top, boolean drawer,
                int price,String manuID){
        super(ID,type,price,manuID);
        setDrawer(drawer);
        setTop(top);
        setLegs(legs);
    }
    //setters and getters
    public void setLegs(boolean legs) {
        Legs = legs;
    }

    public boolean isLegs() {
        return Legs;
    }

    public boolean isDrawer() {
        return Drawer;
    }

    public boolean isTop() {
        return Top;
    }

    public void setDrawer(boolean drawer) {
        Drawer = drawer;
    }

    public void setTop(boolean top) {
        Top = top;
    }

}
