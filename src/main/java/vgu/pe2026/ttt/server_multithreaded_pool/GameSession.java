package vgu.pe2026.ttt.server_multithreaded_pool;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;

public class GameSession implements Runnable {
    private Socket con;
    private Game game;

    public GameSession(Socket socket) {
        this.con = socket;
    }

    @Override
    public void run() {
        try {
            PrintStream socketPrinter = new PrintStream(con.getOutputStream(), true);
            InputStream in;
            try {
                in = con.getInputStream();
                Board board = new Board(3, socketPrinter);
                Player player1 = new HumanPlayer(1, in, socketPrinter);
                Player player2 = new BotPlayer(2, socketPrinter);

                Game game = new Game();
                game.setPlayers(player1, player2);
                game.setBoard(board);
                game.setStartingPlayer(1);

                game.play(socketPrinter);
            } catch (IOException ex) {
                System.getLogger(GameSession.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            
        } catch (Exception e) {
            System.err.println("Game interrupted.");
        } finally {
            // Always close the socket when the game finishes to free resources
            try {
                con.close();
                System.out.println("Client socket closed.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}