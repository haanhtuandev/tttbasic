package vgu.pe2026.ttt.basic;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;

    public void setStartingPlayer(int turn){
        if (players == null) {
            throw new IllegalStateException("Players list must be set before sorting!");
        }
        switch (turn) {
            case 1  -> players.sort(Comparator.comparing(Player::getSymbol));
            case 2  -> players.sort(Comparator.comparing(Player::getSymbol).reversed());
            default -> throw new IllegalArgumentException("Invalid argument: " + turn);
        }
    }


    public Board getBoard(){
        return this.board;
    }



    public void setPlayers(List<Player> players){
        this.players = new ArrayList<>(players);
    }

    public void setBoard(Board board){
        this.board = board;
    }

    

    public void play(){
        board.render();
        Thread gameThread = new Thread(
            () -> {
                while (true){
                    for (Player player : players) {
                        player.makeMove(board);
                        board.render();
                        if (board.checkWin(player.symbol)){
                            System.out.println( "Player" + player.getSymbol() + " won");
                            return;
                        }
                        if (board.checkFull()){
                            System.out.println("Draw");
                            return;
                        }
                    }
                }
            }
        );
        gameThread.start();


        
    }


    
}
