package vgu.pe2026.ttt.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class App 
{
    public static void main( String[] args )
    {
        
        try (ServerSocket ssocket = new ServerSocket(8000)){
            while(true){
                System.out.println("Waiting for TicTacToe player...");
                Socket con = ssocket.accept();

                System.out.println("Player found");

                PrintStream socketPrinter = new PrintStream(con.getOutputStream(), true);
                InputStream in = con.getInputStream();


                Board board = new Board(3, socketPrinter);
                Player player1 = new HumanPlayer(1, in, socketPrinter);
                Player player2 = new BotPlayer(2, socketPrinter);

                Game game = new Game();
                game.setPlayers(player1, player2);
                game.setBoard(board);


                // if (args.length != 1){
                //     System.out.println("Please, input a valid option [1-2]");
                //     return;
                // }

                try {
                    // int turn = Integer.parseInt(args[0]);
                    int turn = 1;
                    game.setStartingPlayer(turn);
                }
                catch(Exception e){
                    socketPrinter.println("Please, input a valid option [1-2]");
                    return;
                }
                game.play(socketPrinter);
            }
        } catch (IOException e) {
            System.err.println("Could not start server: " + e.getMessage());
        }
    }
}
