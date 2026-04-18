package vgu.pe2026.ttt.basic;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest {
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    @BeforeEach
    void setup(){
        PrintStream printer = new PrintStream(out, true);
        System.setOut(printer);
    }

    @AfterEach
    void tearDown(){
        System.setOut(System.out);
    }


    @Test 
    public void checkMainInputParameter_noArgument(){
        

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = {};
        App.main(args);
        
        String expected = "Please, input a valid option [1-2]";
        assertEquals(expected + System.lineSeparator(), out.toString());
    }
    @Test 
    public void checkMainInputParameter_invalidInput(){

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        
        String[] args = {"a"};
        App.main(args);

        String expected = "Please, input a valid option [1-2]";
        assertEquals(expected + System.lineSeparator(), out.toString());
    }

    @Test
    public void checkMain_run(){

        String[] args = {"1"};
        App.main(args);

        
        String board = "| 0 | 0 | 0 | " + System.lineSeparator() + "| 0 | 0 | 0 | " + System.lineSeparator() + "| 0 | 0 | 0 | " + System.lineSeparator();
        String turn_string = "Player1's move: ";
        String expected = board  + turn_string + System.lineSeparator();
        assertTrue(out.toString().contains(expected));
    }
}
