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
    
    public void setManuID(String manuID) {
        this.manuID = manuID;
    }

    public String getManuID() {
        return manuID;
    }

        public void setID(String ID) {
            this.ID = ID;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getPrice() {
            return price;
        }

        public String getType() {
            return type;
        }

        public String getID() {
            return ID;
        }
    
}
