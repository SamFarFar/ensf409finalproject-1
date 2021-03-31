package edu.ucalgary.ensf409;

public class InvalidRequestException extends Exception{
    public  InvalidRequestException(){
        super("This request cannot be fulfilled.");
    }
}
