package digitalwatermarks;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FunctionsTest {
    
    public FunctionsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void stringToASCII() {
         assertTrue(Functions.stringToBinary("АБВ").equals("000100000001000100010010"));
     }
     
     @Test
     public void intToASCII() {
         System.out.println(Functions.intToBinary(16));
         assertTrue(Functions.intToBinary(16).equals("00010000"));
     }
}
