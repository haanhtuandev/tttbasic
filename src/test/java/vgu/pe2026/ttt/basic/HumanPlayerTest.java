package vgu.pe2026.ttt.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class HumanPlayerTest {
    @Test
    void makeMoveTrue() {
        String simulatedInput = "5" + System.lineSeparator(); 
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());

        Player player = new HumanPlayer(1, inputStream);


        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Board testBoard = new Board(3, new PrintStream(out));
        player.makeMove(testBoard);
        // testBoard.getCell(1, 1);
        // testBoard.render();
        
        // String expected = "| 0 | 0 | 0 | " + System.lineSeparator() + "| 0 | 1 | 0 | " + System.lineSeparator() + "| 0 | 0 | 0 | " + System.lineSeparator();
        
        assertEquals(1, testBoard.getCell(1, 1));



        // int[][] actual = testBoard.getGrid();


        // int[][] expected = {{1,0,1},{0,1,0},{0,0,0}};


    }

} 
