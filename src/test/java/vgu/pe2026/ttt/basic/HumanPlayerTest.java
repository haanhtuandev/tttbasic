package vgu.pe2026.ttt.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HumanPlayerTest {
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
    @DisplayName("Happy case human move")
    void makeMoveTrue() {
        String simulatedInput = "5" + System.lineSeparator(); 
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());

        Player player = new HumanPlayer(1, inputStream);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Board testBoard = new Board(3, new PrintStream(out));
        player.makeMove(testBoard);
        assertEquals(1, testBoard.getCell(1, 1));
    }

    @Test
    @DisplayName("Bad case human move")
    void makeInvalidMove() {
        String simulatedInput = "asd" + System.lineSeparator() + "5" + System.lineSeparator(); 
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());

        Player player = new HumanPlayer(1, inputStream);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Board testBoard = new Board(3, new PrintStream(out));
        player.makeMove(testBoard);
        assertEquals(1, testBoard.getCell(1, 1));
    }

    @Test
    @DisplayName("Human Non-Integer Input")
    void makeNonIntegerMove() {
        String simulatedInput = "x" + System.lineSeparator() + "1" + System.lineSeparator(); 
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());

        Player player = new HumanPlayer(1, inputStream);

        Board testBoard = new Board(3, new PrintStream(out));
        player.makeMove(testBoard);


        String expected = "Please, input a valid number [1-9]" + System.lineSeparator() + "Player1's move: " + System.lineSeparator();
        assertEquals(expected, out.toString());
    }

} 
