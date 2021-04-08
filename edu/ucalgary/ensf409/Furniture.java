package edu.ucalgary.ensf409;

public abstract class Furniture {
    private String ID;
    private String type;
    private int price;
    private String manuID;

    public Furniture(String ID,String type,int price,String manuID){
        setID(ID);
        setType(type);
        setPrice(price);
        setManuID(manuID);
    }
    public Furniture(String ID,String type,int price){
        setID(ID);
        setType(type);
        setPrice(price);
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