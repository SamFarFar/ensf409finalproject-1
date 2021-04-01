package JUnit;

import edu.ucalgary.ensf409.Input;
import edu.ucalgary.ensf409.InvalidRequestException;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FPTest {
    @Test
    public void regexCheckWorking() {
        String test1 = "mesh chair, 1";
        Pattern pattern = Pattern.compile(test1);
        Matcher match = pattern.matcher(Input.getREGEX());
        assertEquals(match.find(), true);
    }
    @Test(expected = InvalidRequestException.class)
    public void regexCheckError() {
        String test1 = "mesh chair 1";
        Pattern pattern = Pattern.compile(test1);
        Matcher match = pattern.matcher(Input.getREGEX());
        assertEquals(match.find(), true);
    }

}
