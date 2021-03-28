package edu.ucalgary.ensf409;

public class CommandArgumentNotProvidedException extends Exception{
public CommandArgumentNotProvidedException(){
    super("Incorrect command line input.");
}

}
