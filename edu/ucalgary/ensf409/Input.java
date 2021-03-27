package edu.ucalgary.ensf409;
import java.util.*;
public class Input {
    public static void main(String[] args) {
       Scanner scanner = null;
        try{
        scanner = new Scanner(System.in);
       }catch(Exception e){
           System.out.println("Error occurred");
           e.printStackTrace();
       }
    }
}