package vgu.pe2026.ttt.client_singlethread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class App {
    
    public static void main(String[] args) {

        Board board = new Board(3);
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Tic Tac Toe Client Started!");
        board.render();

        while (true) { 
            System.out.println("Enter move (1-9):");
            String move = keyboard.nextLine();

            TTTRequest request = new TTTRequest(move, board.toString());
            
            String respond = makeRequest(request);
            if (respond.isEmpty()) {
                System.out.println("Server connection failed.");
                break;
            }

            Board newBoard = parseResponse(respond);
            if (newBoard == null) {
                // parseResponse already prints the win/draw message
                break;
            }
            board = newBoard;
            board.render();
        }
        keyboard.close();
    }
    public static String makeRequest(TTTRequest request){
        String requestString = request.toString();
        try (Socket socket = new Socket("localhost", 8000);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); 
        ){
            out.println(requestString);
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line).append("\n");
            }
            return response.toString();

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
        return "";
    }

    public static Board parseResponse(String respondString){
        String[] lines = respondString.split("\n");
        String boardData = lines[0].trim();
        
        Board board = new Board(3);
        if (boardData.length() >= 9) {
            for (int i = 0; i < 9; i++) {
                int symbol = Character.getNumericValue(boardData.charAt(i));
                board.setCell(i + 1, symbol);
            }
        }

        if (respondString.contains("Player#1 won!")){
            board.render();
            System.out.println("Congratulations! You won!");
            return null;
        }
        if (respondString.contains("Player#2 won!")){
            board.render();
            System.out.println("Computer won!");
            return null;
        }
        if (respondString.contains("draw")){
            board.render();
            System.out.println("It's a draw!");
            return null;
        }
        if (respondString.contains("Invalid move")) {
            System.out.println("Invalid move, try again.");
            return board; 
        }

        return board;
    }
}
