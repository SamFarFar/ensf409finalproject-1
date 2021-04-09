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
  //setters
    public void setArms(boolean arms) {
        Arms = arms;
    }

    public void setCushion(boolean cushion) {
        Cushion = cushion;
    }

    public void setLegs(boolean legs) {
        Legs = legs;
    }

    public void setSeat(boolean seat) {
        Seat = seat;
    }

    //getters
    public boolean isArms() {
        return Arms;
    }

    public boolean isCushion() {
        return Cushion;
    }

    public boolean isLegs() {
        return Legs;
    }

    public boolean isSeat() {
        return Seat;
    }

}
