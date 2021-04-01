package edu.ucalgary.ensf409;

public class InvalidRequestException extends Exception{
    /**
     * Exception created that will be called if the request is invalid
     */
    public  InvalidRequestException(){
        super("This request cannot be fulfilled.");
    }
}
