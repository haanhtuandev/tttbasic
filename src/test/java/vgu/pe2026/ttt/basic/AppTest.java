package vgu.pe2026.ttt.basic;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest {
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
    @DisplayName("Startup Without Argument")
    public void checkMainInputParameter_noArgument(){
        String[] args = {};
        App.main(args);
        String expected = "Please, input a valid option [1-2]";
        assertEquals(expected + System.lineSeparator(), out.toString());
    }
    @Test 
    @DisplayName("Startup With Invalid Argument")
    public void checkMainInputParameter_invalidInput(){        
        String[] args = {"a"};
        App.main(args);
        String expected = "Please, input a valid option [1-2]";
        assertEquals(expected + System.lineSeparator(), out.toString());
    }

    @Test 
    @DisplayName("Startup With Extra Arguments")
    public void checkMainInputParameter_extraArguments(){        
        String[] args = {"1", "2"};
        App.main(args);
        String expected = "Please, input a valid option [1-2]";
        assertEquals(expected + System.lineSeparator(), out.toString());
    }

    @Test
    @DisplayName("Startup Message and Order")
    public void checkMain_run(){
        String[] args = {"1"};
        App.main(args);
        String hello = "Hello!" + System.lineSeparator();
        String board = "| 0 | 0 | 0 | " + System.lineSeparator() + "| 0 | 0 | 0 | " + System.lineSeparator() + "| 0 | 0 | 0 | " + System.lineSeparator();
        String turn_string = "Player#1's move: ";
        String expected = hello + board  + turn_string + System.lineSeparator();
        assertEquals(expected, out.toString());
    }
}
