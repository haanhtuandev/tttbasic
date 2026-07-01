package vgu.pe2026.ttt.server_singlethread_multiuser;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class App 
{
    public static void main( String[] args )
    {
        // payload: 1(move),000000000(board)
        HashObject hasher = new HashObject();
        try (ServerSocket ssocket = new ServerSocket(8000)){
            while(true){
                System.out.println("Waiting for requests...");
                try (Socket con = ssocket.accept();
                     PrintStream socketPrinter = new PrintStream(con.getOutputStream(), true);
                     Scanner in = new Scanner(con.getInputStream())) {

                     System.out.println("Processing request ... ");
                     System.out.println("This line is reached 1");
                     String requestString = in.nextLine();
                     if (requestString.equals("Hello")){
                                                
                     }
                     makeRespond(requestString, socketPrinter);
                     System.out.println("Respond sent");

                     con.close();
                     System.out.println("Done serving request");
                } catch (IOException e) {
                    System.err.println("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Could not start server: " + e.getMessage());
        }
    }
    public static void makeRespond(String requestString, PrintStream out){
        int move = Integer.parseInt(requestString.split(",")[0]);
        Board board = loadBoard(requestString);
        board.setCell(move, 1);

        // check human win
        if (board.checkWin(1)) {
            out.println(getBoardString(board));
            out.println("Player#1 won!");
            return;
        }

        // check draw
        if (board.checkFull()) {
            out.println(getBoardString(board));
            out.println("It is a draw!");
            return;
        }

        // computer move
        boolean moved = false;
        for (int cell = 1; cell <= board.getSize()*board.getSize(); cell++){
            if (board.isValidMove(cell)){
                board.setCell(cell, 2);
                moved = true;
                break;
            }
        }

        // check if computer won
        if (board.checkWin(2)) {
            out.println(getBoardString(board));
            out.println("Player#2 won!");
            return;
        }

        // check draw after computer move
        if (!moved && board.checkFull()) {
            out.println(getBoardString(board));
            out.println("It is a draw!");
            return;
        }

        // Return the board state
        out.println(getBoardString(board));
    }

    public static String getBoardString(Board board) {
        StringBuilder sb = new StringBuilder();
        int size = board.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(board.getCell(i, j));
            }
        }
        return sb.toString();
    }

    // takes a board_string 000000000, convert to a Board type
    public static Board loadBoard(String boardString){
        String[] parts = boardString.split(",");
        String boardData = parts[1];
        Board board = new Board(3, null);
        for (int i = 0; i < 9; i++) {
            int symbol = Character.getNumericValue(boardData.charAt(i));
            board.setCell(i + 1, symbol);
        }
        return board;
    }


    

}
