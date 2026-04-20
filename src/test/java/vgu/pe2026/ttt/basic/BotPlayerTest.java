package vgu.pe2026.ttt.basic;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BotPlayerTest {
    @Test
    void makeMoveTrue() {
        Player player = new BotPlayer(2);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Board testBoard = new Board(3, new PrintStream(out));
        testBoard.setBoard(new int[][]{
            {1,1,0},
            {0,0,0},
            {0,0,0}
        });
        player.makeMove(testBoard);
        
        assertEquals(2, testBoard.getCell(0, 2));
    }
}
