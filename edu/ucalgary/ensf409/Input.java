package edu.ucalgary.ensf409;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {
 private static final String WELCOME = "~~~~~~~~ Welcome ~~~~~~~~~";
 private static final String PROMPTINPUT = "What would you like to make today?" +
         "\nPlease specify below your request in the format of:\n"+
         "<Type> <Furniture>, <Amount> \n" + "Example: mesh chair, 1";
    private final static String REGEX = "([a-zA-z]{1,9})\\s([a-zA-z]{1,9})[,]\\s([0-9])";

 public static void main(String[] args) {
       Scanner scanner = null;

        try{
        scanner = new Scanner(System.in);
        System.out.println(WELCOME);
        System.out.println(PROMPTINPUT);
            Pattern pattern = Pattern.compile(scanner.nextLine());
            Matcher match = pattern.matcher(REGEX);
            if(!match.find()){
                throw new CommandArgumentNotProvidedException();
            }else{
                Request userRequest = new Request(match.group(1),
                        match.group(2),Integer.parseInt(match.group(3)));

            }
       }catch(Exception e){
           System.out.println("Error occurred");

           e.printStackTrace();
       }
    }
}