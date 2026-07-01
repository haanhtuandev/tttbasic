package vgu.pe2026.ttt.basic;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest {

    @BeforeEach
    void setup(){
        // PrintStream printer = new PrintStream(out, true);
        // System.setOut(printer);
    }

    @AfterEach
    void cleanUp(){
        System.setOut(System.out);
        System.setIn(System.in);
    }

    @Test
    @DisplayName("Startup Without Argument")
    public void checkMainInputParameter_noArgument(){
        // Set up out
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Set up in 
        String[] args = {};
        App.main(args);
        String expected = "Please, input a valid option [1-2]";
        assertEquals(expected + System.lineSeparator(), out.toString());
    }
    @Test 
    @DisplayName("Startup With Invalid Argument")
    public void checkMainInputParameter_invalidInput(){     
        // Set up out
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = {"a"};
        App.main(args);
        String expected = "Please, input a valid option [1-2]";
        assertEquals(expected + System.lineSeparator(), out.toString());
    }

    @Test 
    @DisplayName("Startup With Extra Arguments")
    public void checkMainInputParameter_extraArguments(){        
        // Set up out
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String[] args = {"1", "2"};
        App.main(args);
        String expected = "Please, input a valid option [1-2]";
        assertEquals(expected + System.lineSeparator(), out.toString());
    }

    @Test
    @DisplayName("Startup Message and Order")
    public void checkMain_run() throws Exception{
        // Set up out
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        

        // Set up in
        String input = "q" + System.lineSeparator();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String[] args = {"1"};
        App.main(args);

        // Capture out 
        byte[] printout = out.toByteArray();
        ByteArrayInputStream capturedOutputAsInput = new ByteArrayInputStream(printout);
        BufferedReader reader = new BufferedReader(new InputStreamReader(capturedOutputAsInput));
        try {
            assertEquals("Hello!", reader.readLine());
            assertEquals("| 0 | 0 | 0 | ", reader.readLine());
            assertEquals("| 0 | 0 | 0 | ", reader.readLine());
            assertEquals("| 0 | 0 | 0 | ", reader.readLine());
            assertEquals("Player#1's move: ", reader.readLine());    
        } catch (Exception e) {
            throw new Exception("Error reading line");
        }
    }
    @Test
    @DisplayName("Start with computer first")
    public void checkMain_bot_first() throws Exception{
        // Set up out
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Set up in
        String input = "q" + System.lineSeparator();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String[] args = {"2"};
        App.main(args);
        
        // Capture out 
        byte[] printout = out.toByteArray();
        ByteArrayInputStream capturedOutputAsInput = new ByteArrayInputStream(printout);
        BufferedReader reader = new BufferedReader(new InputStreamReader(capturedOutputAsInput));
        try {
            assertEquals("Hello!", reader.readLine());
            assertEquals("| 0 | 0 | 0 | ", reader.readLine());
            assertEquals("| 0 | 0 | 0 | ", reader.readLine());
            assertEquals("| 0 | 0 | 0 | ", reader.readLine());
            assertEquals("Player#2's move: ", reader.readLine());    
        } catch (Exception e) {
            throw new Exception("Error reading line");
        }
    }
    @Test
    @DisplayName("Check board renders 3x3")
    public void check_board_renders_3x3() throws Exception{
        // Set up out
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Set up in
        String input = "5" + System.lineSeparator() + "q" + System.lineSeparator();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String[] args = {"1"};
        App.main(args);
        
        // Capture out 
        byte[] printout = out.toByteArray();
        ByteArrayInputStream capturedOutputAsInput = new ByteArrayInputStream(printout);
        BufferedReader reader = new BufferedReader(new InputStreamReader(capturedOutputAsInput));
        skipLine(9, reader);
        try {
            assertEquals("| 2 | 0 | 0 | ", reader.readLine());
            assertEquals("| 0 | 1 | 0 | ", reader.readLine());
            assertEquals("| 0 | 0 | 0 | ", reader.readLine());
        } catch (Exception e) {
            throw new Exception("Error reading line");
        }
    }
    @Test
    @DisplayName("Handle non-integer input")
    public void handle_non_integer_input() throws Exception{
        // Set up out
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Set up in
        String input = "abc" + System.lineSeparator() + "@" + System.lineSeparator() + "q" + System.lineSeparator();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String[] args = {"1"};
        App.main(args);
        
        // Capture out 
        byte[] printout = out.toByteArray();
        ByteArrayInputStream capturedOutputAsInput = new ByteArrayInputStream(printout);
        BufferedReader reader = new BufferedReader(new InputStreamReader(capturedOutputAsInput));
        skipLine(4, reader);
        try {
            assertEquals("Player#1's move: ", reader.readLine());
            assertEquals("Please, input a valid number [1-9]", reader.readLine());
            assertEquals("Player#1's move: ", reader.readLine());
            assertEquals("Please, input a valid number [1-9]", reader.readLine());
            assertEquals("Player#1's move: ", reader.readLine());
            assertEquals("End of the game", reader.readLine());
        } catch (Exception e) {
            throw new Exception("Error reading line");
        }
    }
    @Test
    @DisplayName("Quit with q")
    public void quit_game_with_q() throws Exception{
        // Set up out
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Set up in
        String input = "q" + System.lineSeparator();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String[] args = {"1"};
        App.main(args);
        
        // Capture out 
        byte[] printout = out.toByteArray();
        ByteArrayInputStream capturedOutputAsInput = new ByteArrayInputStream(printout);
        BufferedReader reader = new BufferedReader(new InputStreamReader(capturedOutputAsInput));
        skipLine(4, reader);
        try {
            assertEquals("Player#1's move: ", reader.readLine());
            assertEquals("End of the game", reader.readLine());
        } catch (Exception e) {
            throw new Exception("Error reading line");
        }
    }
    @Test
    @DisplayName("Reject integer outside 1-9")
    public void reject_integer_outside_range() throws Exception{
        // Set up out
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Set up in
        String input = "10" + System.lineSeparator() + "q" + System.lineSeparator();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String[] args = {"1"};
        App.main(args);
        
        // Capture out 
        byte[] printout = out.toByteArray();
        ByteArrayInputStream capturedOutputAsInput = new ByteArrayInputStream(printout);
        BufferedReader reader = new BufferedReader(new InputStreamReader(capturedOutputAsInput));
        skipLine(4, reader);
        try {
            assertEquals("Player#1's move: ", reader.readLine());
            assertEquals("Please, input a valid number [1-9]", reader.readLine());
            assertEquals("Player#1's move: ", reader.readLine());
            assertEquals("End of the game", reader.readLine());
        } catch (Exception e) {
            throw new Exception("Error reading line");
        }
    }

    @Test
    @DisplayName("Quit with q case sensitve")
    public void quit_game_with_q_case_sensitive() throws Exception{
        // Set up out
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Set up in
        String input = "Q" + System.lineSeparator() + "  q" + System.lineSeparator() + "q  " + System.lineSeparator() + "q" + System.lineSeparator();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String[] args = {"1"};
        App.main(args);
        
        // Capture out 
        byte[] printout = out.toByteArray();
        ByteArrayInputStream capturedOutputAsInput = new ByteArrayInputStream(printout);
        BufferedReader reader = new BufferedReader(new InputStreamReader(capturedOutputAsInput));
        skipLine(4, reader);
        try {
            assertEquals("Player#1's move: ", reader.readLine());
            assertEquals("Please, input a valid number [1-9]", reader.readLine());
            assertEquals("Player#1's move: ", reader.readLine());
            assertEquals("Please, input a valid number [1-9]", reader.readLine());
            assertEquals("Player#1's move: ", reader.readLine());
            assertEquals("Please, input a valid number [1-9]", reader.readLine());
            assertEquals("Player#1's move: ", reader.readLine());
            assertEquals("End of the game", reader.readLine());
        } catch (Exception e) {
            throw new Exception("Error reading line");
        }
    }
    @Test
    @DisplayName("Reject move to occupied cell")
    public void reject_move_to_occupied() throws Exception{
        // Set up out
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Set up in
        String input = "1" + System.lineSeparator() + "q" + System.lineSeparator();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String[] args = {"2"};
        App.main(args);
        
        // Capture out 
        byte[] printout = out.toByteArray();
        ByteArrayInputStream capturedOutputAsInput = new ByteArrayInputStream(printout);
        BufferedReader reader = new BufferedReader(new InputStreamReader(capturedOutputAsInput));
        skipLine(9, reader);
        try {
            assertEquals("Player#1's move: ", reader.readLine());
            assertEquals("The cell is occupied!", reader.readLine());
            assertEquals("Player#1's move: ", reader.readLine());
            assertEquals("End of the game", reader.readLine());
        } catch (Exception e) {
            throw new Exception("Error reading line");
        }
    }



    void skipLine(int number, BufferedReader reader) throws IOException{
        for (int i = 0; i < number; i++){
            reader.readLine();
        }
    }   
}
