/**
 @author Matteo Morrone <a href = "mailto:matteo.morrone@ucalgary.ca">
 matteo.morrone@ucalgary.ca</a>
 @author Sam FarzamFar <a href = "mailto:sam.farzamfar@ucalgary.ca">
 sam.farzamfar@ucalgary.ca</a>
 @author Sandip Mishra <a href = "mailto:sandip.mishra@ucalgary.ca">
 sandip.mishra@ucalgary.ca</a>
 @version 9.8
 @since 1.0
 */

package edu.ucalgary.ensf409;

public class InvalidRequestException extends Exception{
    /**
     * Exception created that will be called if the request is invalid
     */
    public  InvalidRequestException(){
        super("This request cannot be fulfilled.");
    }
}
