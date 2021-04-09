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

    public boolean isCabinet() {
        return Cabinet;
    }

    public boolean isDrawers() {
        return Drawers;
    }

    public boolean isRails() {
        return Rails;
    }

    public void setCabinet(boolean cabinet) {
        Cabinet = cabinet;
    }

    public void setDrawers(boolean drawers) {
        Drawers = drawers;
    }

    public void setRails(boolean rails) {
        Rails = rails;
    }
    
}
