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
	
	/**
	* Getter for Base
	* @return boolean Base data member
	*/
    public boolean isBase() {
        return Base;
    }
	
	/**
	* Getter for Bulb
	* @return boolean Bulb data member
	*/
    public boolean isBulb() {
        return Bulb;
    }
	
	/**
     * Sets given parameter to Base data member
     * @param base boolean for Base data member
     */
    public void setBase(boolean base) {
        Base = base;
    }

	/**
     * Sets given parameter to Bulb data member
     * @param bulb boolean for Bulb data member
     */
    public void setBulb(boolean bulb) {
        Bulb = bulb;
    }
    
}
