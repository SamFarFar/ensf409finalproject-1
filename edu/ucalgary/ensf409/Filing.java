package edu.ucalgary.ensf409;
public class Filing extends Furniture{
private boolean Rails;
private boolean Drawers;
private boolean Cabinet;
    public Filing(String ID, String type, int price, String manuID){
        super(ID,type,price,manuID);
    }
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