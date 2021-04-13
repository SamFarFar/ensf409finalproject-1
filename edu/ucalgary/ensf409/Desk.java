/**
@author Matteo Morrone <a href = "mailto:matteo.morrone@ucalgary.ca">
	matteo.morrone@ucalgary.ca</a>
@author Sam FarzamFar <a href = "mailto:sam.farzamfar@ucalgary.ca">
	sam.farzamfar@ucalgary.ca</a>
@author Sandip Mishra <a href = "mailto:matteo.morrone@ucalgary.ca">
	matteo.morrone@ucalgary.ca</a>
@version 9.8
@since 1.0
*/

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
    
    /**
     * Sets given parameter to Legs data member
     * @param legs boolean for Legs data member
     */
    public void setLegs(boolean legs) {
        Legs = legs;
    }
	
	/**
	* Getter for Legs
	* @return boolean Legs data member
	*/
    public boolean isLegs() {
        return Legs;
    }
	
	/**
	* Getter for Drawer
	* @return boolean Drawer data member
	*/
    public boolean isDrawer() {
        return Drawer;
    }
	
	/**
	* Getter for Top
	* @return boolean Top data member
	*/
    public boolean isTop() {
        return Top;
    }
	
	/**
     * Sets given parameter to Drawer data member
     * @param drawer boolean for Drawer data member
     */
    public void setDrawer(boolean drawer) {
        Drawer = drawer;
    }
	
	/**
     * Sets given parameter to Top data member
     * @param top boolean for Top data member
     */
    public void setTop(boolean top) {
        Top = top;
    }

}
