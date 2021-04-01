package edu.ucalgary.ensf409;

public class InvalidIDException extends Exception {
    /**
     * exception created for the different furniture adding methods.
     */
    public InvalidIDException(){
        super("ID provided will not create the proper piece of furniture.");
    }
}
