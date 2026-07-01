package vgu.pe2026.ttt.server_multithreaded_pool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App 
{
    private static final int THREAD_POOL_SIZE = 4;
    public static void main( String[] args )
    {
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        
        try (ServerSocket ssocket = new ServerSocket(8000)){
            while(true){
                System.out.println("Waiting for TicTacToe player...");
                Socket con = ssocket.accept();

                System.out.println("Player found");

                GameSession gameSession = new GameSession(con);
                threadPool.submit(gameSession);
            }
        } catch (IOException e) {
            System.err.println("Could not start server: " + e.getMessage());
        }
    }
}
