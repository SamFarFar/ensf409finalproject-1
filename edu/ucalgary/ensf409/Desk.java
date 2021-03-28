package edu.ucalgary.ensf409;

public class Desk extends Furniture{
    private boolean Legs;
    private boolean Top;
    private boolean Drawer;
    private boolean Cushion;

    public Desk(String ID, String type, int price, String manuID){
        super(ID,type,price,manuID);
    }
    public Desk(String ID,String type, boolean legs,boolean top, boolean drawer,
                boolean cushion,int price,String manuID){
        super(ID,type,price,manuID);
        setCushion(cushion);
        setDrawer(drawer);
        setTop(top);
        setLegs(legs);
    }
    public void setLegs(boolean legs) {
        Legs = legs;
    }

    public boolean isLegs() {
        return Legs;
    }

    public boolean isCushion() {
        return Cushion;
    }

    public void setCushion(boolean cushion) {
        Cushion = cushion;
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