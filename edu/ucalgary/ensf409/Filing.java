/**
 @author Matteo Morrone <a href = "mailto:matteo.morrone@ucalgary.ca">
 matteo.morrone@ucalgary.ca</a>
 @author Sam FarzamFar <a href = "mailto:sam.farzamfar@ucalgary.ca">
 sam.farzamfar@ucalgary.ca</a>
 @author Sandip Mishra <a href = "mailto:sandip.mishra@ucalgary.ca">
 sandip.mishra@ucalgary.ca</a>
 @version 9.8
 @since 1.0
 */

package edu.ucalgary.ensf409;

public class Filing extends Furniture{
	
	private boolean Rails;
	private boolean Drawers;
	private boolean Cabinet;
    
    /**
	 * Basic constructor to create an instance of Filing object, 
	 * calls on constructor of Furniture parent class.
	 * @param ID String ID used to identify the item
	 * @param type String type used to classify the item
	 * @param rails boolean value indicating availability of rails
	 * @param drawers boolean value indicating availability of drawers
	 * @param cabinet boolean value indicating availability of cabinet
	 * @param price integer representation of the cost of the item
	 * @param manuID String ManuID used to identify the item's manufacturer
	 */
    public Filing(String ID, String type, boolean rails, boolean drawers, 
					boolean cabinet, int price, String manuID){
        super(ID,type,price,manuID);
        setCabinet(cabinet);
        setDrawers(drawers);
        setRails(rails);
    }
	
	/**
	* Getter for Cabinet
	* @return boolean Cabinet data member
	*/
    public boolean isCabinet() {
        return Cabinet;
    }
	
	/**
	* Getter for Drawers
	* @return boolean Drawers data member
	*/
    public boolean isDrawers() {
        return Drawers;
    }
	
	/**
	* Getter for Rails
	* @return boolean Rails data member
	*/
    public boolean isRails() {
        return Rails;
    }
	
	/**
     * Sets given parameter to Cabinet data member
     * @param cabinet boolean for Cabinet data member
     */
    public void setCabinet(boolean cabinet) {
        Cabinet = cabinet;
    }
	
	/**
     * Sets given parameter to Drawers data member
     * @param drawers boolean for Drawers data member
     */
    public void setDrawers(boolean drawers) {
        Drawers = drawers;
    }
	
	/**
     * Sets given parameter to Rails data member
     * @param rails boolean for Rails data member
     */
    public void setRails(boolean rails) {
        Rails = rails;
    }
    
}
