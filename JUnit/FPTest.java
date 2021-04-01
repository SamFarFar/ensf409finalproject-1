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
    @Test
    public void ChairConstructor1(){
        Chair chair1 = new Chair("C1234","standing",50,"w2123");
        String[] expected = {"C1234","standing","50","w2123"};
       String[] actual = new String[4];
       actual[0] = chair1.getID();
        actual[1] = chair1.getType();
        actual[2] = String.valueOf(chair1.getPrice());
        actual[3] = chair1.getManuID();
        assertArrayEquals(expected,actual);
    }

}
