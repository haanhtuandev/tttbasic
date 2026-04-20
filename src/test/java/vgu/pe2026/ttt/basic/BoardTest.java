package vgu.pe2026.ttt.basic;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class BoardTest {
    @Test
    void checkWinTrue() {
        int[][] mockCoordinate = {{1,1,1}, {2,0,2}, {2,2,1}};
        Board testBoard = new Board(3, System.out);
        testBoard.setBoard(mockCoordinate);

        assertEquals(testBoard.checkWin(1), true);
    }
    @Test
    void checkWinFalse() {
        int[][] mockCoordinate = {{0,1,1}, {2,0,2}, {2,2,1}};
        Board testBoard = new Board(3, System.out);
        testBoard.setBoard(mockCoordinate);
        assertEquals(testBoard.checkWin(1), false);
    }

    @Test
    void checkFullTrue() {
        int[][] mockCoordinate = {{1,1,1}, {2,2,2}, {2,2,1}};
        Board testBoard = new Board(3, System.out);
        testBoard.setBoard(mockCoordinate);

        assertEquals(testBoard.checkFull(), true);
    }
    @Test
    void checkFullFalse() {
        int[][] mockCoordinate = {{0,1,1}, {2,2,2}, {2,2,1}};
        Board testBoard = new Board(3, System.out);
        testBoard.setBoard(mockCoordinate);

        assertEquals(testBoard.checkFull(), false);
    }

    @Test
    @DisplayName("Initial Board Mapping")
    void renderTestTrue(){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Board testBoard = new Board(3, new PrintStream(out));
        testBoard.render();
        String expected = "| 0 | 0 | 0 | " + System.lineSeparator() + "| 0 | 0 | 0 | " + System.lineSeparator() + "| 0 | 0 | 0 | " + System.lineSeparator();

        assertEquals(expected, out.toString());

    }
    @Test
    void renderTestFalse(){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Board testBoard = new Board(3, new PrintStream(out));
        testBoard.render();
        String expected = "| 1 | 2 | 1 | " + System.lineSeparator() + "| 0 | 0 | 0 | " + System.lineSeparator() + "| 0 | 0 | 0 | " + System.lineSeparator();

        assertFalse(expected.equals(out.toString()));

    }
}
