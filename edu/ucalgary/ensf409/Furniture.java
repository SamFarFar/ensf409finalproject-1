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

public abstract class Furniture {
	
    private String ID;
    private String type;
    private int price;
    private String manuID;
	
	/**
	 * Basic constructor to be used in creation of child class objects.
	 * @param ID String ID used to identify the item
	 * @param type String type used to classify the item
	 * @param price integer representation of the cost of the item
	 * @param manuID String ManuID used to identify the item's manufacturer
	 */
    public Furniture(String ID,String type,int price,String manuID){
        setID(ID);
        setType(type);
        setPrice(price);
        setManuID(manuID);
    }
    
    /**
     * Sets given parameter to manuID data member
     * @param manuID String for manufacturer ID
     */
    public void setManuID(String manuID) {
        this.manuID = manuID;
    }
	
	/**
	 * Getter for manuID
	 * @return String manuID data member
	 */
    public String getManuID() {
        return manuID;
    }
		
		 /**
		* Sets given parameter to ID data member
		* @param ID String for ID
		*/
        public void setID(String ID) {
            this.ID = ID;
        }
		
		 /**
		 * Sets given parameter to price data member
		 * @param price int for cost of item
		 */
        public void setPrice(int price) {
            this.price = price;
        }
		
		 /**
		 * Sets given parameter to Type data member
		 * @param type String for item's type
		 */
        public void setType(String type) {
            this.type = type;
        }
		
		/**
		 * Getter for price
		 * @return int price data member
		 */
        public int getPrice() {
            return price;
        }
		
		/**
		 * Getter for type
		 * @return String type data member
		 */
        public String getType() {
            return type;
        }
		
		/**
		 * Getter for ID
		 * @return String ID data member
		 */
        public String getID() {
            return ID;
        }
    
}
