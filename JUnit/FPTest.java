package JUnit;

import edu.ucalgary.ensf409.Input;
import edu.ucalgary.ensf409.InvalidRequestException;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FPTest {
    @Test
    public void regexCheckTest() {
        String test1 = "mesh chair, 1";
        Pattern pattern = Pattern.compile(test1);
        Matcher match = pattern.matcher(Input.getREGEX());
        assertTrue(match.find());
    }
    @Test(expected = InvalidRequestException.class)
    public void regexCheckErrTest() {
        String test1 = "mesh chair 1";
        Pattern pattern = Pattern.compile(test1);
        Matcher match = pattern.matcher(Input.getREGEX());
        assertTrue(match.find());
    }
    @Test
    public void Test(){

    }


}

// copy this as a blank test
  /*  @Test
    public void Test(){

    }*/