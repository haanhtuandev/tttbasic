package vgu.pe2026.ttt.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        final String hostname = "localhost";
        final int port = 8000;

        try (Socket socket = new Socket(hostname, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner keyboard = new Scanner(System.in)) {

            System.out.println("Connected to Tic-Tac-Toe server.");

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                
                if (line.contains("Player#1's move:")) {
                    if (keyboard.hasNextLine()) {
                        String move = keyboard.nextLine();
                        out.println(move);
                    }
                }
                
                // Exit condition: game over
                if (line.contains("won!") || line.contains("draw!") || line.contains("End of the game")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
    // public static voidprintResponse(BufferedReader in){
    //     String line;
    //     try {
    //         while ((line = in.readLine()) != null) {
    //             System.out.println(line);
    //         }
    //     } catch (Exception e) {
    //         System.out.println("Something went wrong at reading response");
    //     }
        

    // }
}
