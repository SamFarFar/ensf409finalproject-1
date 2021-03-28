package edu.ucalgary.ensf409;

public class Chair extends Furniture {
    private boolean Legs;
    private boolean Arms;
    private boolean Seat;
    private boolean Cushion;

  public Chair(String ID,String type, boolean legs, boolean arms, boolean seat,
               boolean cushion, int price, String manuID){
      super(ID,type,price,manuID);
      setArms(arms);
      setCushion(cushion);
      setLegs(legs);
      setSeat(seat);
  }
	public Chair(String ID,String type, boolean legs, boolean arms, boolean seat,
               boolean cushion, int price){
      super(ID,type,price);
      setArms(arms);
      setCushion(cushion);
      setLegs(legs);
      setSeat(seat);
	}
	
    public Chair(String ID, String type, int price, String manuID){
        super(ID,type,price,manuID);
    }

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
