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

public class Chair extends Furniture {
	
    private boolean Legs;
    private boolean Arms;
    private boolean Seat;
    private boolean Cushion;
	
	/**
	 * Basic constructor to create an instance of Chair object, 
	 * calls on constructor of Furniture parent class.
	 * @param ID String ID used to identify the item
	 * @param type String type used to classify the item
	 * @param legs boolean value indicating availability of legs
	 * @param arms boolean value indicating availability of arms
	 * @param seat boolean value indicating availability of seat
	 * @param cushion boolean value indicating availability of cushion
	 * @param price integer representation of the cost of the item
	 * @param manuID String ManuID used to identify the item's manufacturer
	 */
	public Chair(String ID,String type, boolean legs, boolean arms, boolean seat,
               boolean cushion, int price, String manuID){
		super(ID,type,price,manuID);
		setArms(arms);
		setCushion(cushion);
		setLegs(legs);
		setSeat(seat);
	}
  
	/**
     * Sets given parameter to Arms data member
     * @param arms boolean for Arms data member
     */
    public void setArms(boolean arms) {
        Arms = arms;
    }
	
	/**
     * Sets given parameter to Cushion data member
     * @param cushion boolean for Cushion data member
     */
    public void setCushion(boolean cushion) {
        Cushion = cushion;
    }
	
	/**
     * Sets given parameter to Legs data member
     * @param legs boolean for Legs data member
     */
    public void setLegs(boolean legs) {
        Legs = legs;
    }
	
	/**
     * Sets given parameter to Seat data member
     * @param seat boolean for Seat data member
     */
    public void setSeat(boolean seat) {
        Seat = seat;
    }

    /**
	* Getter for Arms
	* @return boolean Arms data member
	*/
    public boolean isArms() {
        return Arms;
    }

    /**
	* Getter for Cushion
	* @return boolean Cushion data member
	*/
    public boolean isCushion() {
        return Cushion;
    }

    /**
	* Getter for Legs
	* @return boolean Legs data member
	*/
    public boolean isLegs() {
        return Legs;
    }

    /**
	* Getter for Seat
	* @return boolean Seat data member
	*/
    public boolean isSeat() {
        return Seat;
    }

}
