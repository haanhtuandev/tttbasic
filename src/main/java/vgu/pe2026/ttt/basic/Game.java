package vgu.pe2026.ttt.basic;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Game {
    private int turn;
    private Board board;
    private List<Player> players;

    public Game(){
        this.players = new ArrayList<>();
    }

    public void setTurn(int turn){
        if (players == null) {
            throw new IllegalStateException("Players list must be set before sorting!");
        }
        if (turn == 1){
            players.sort(Comparator.comparing(Player::getSymbol));
        }
        else if (turn == 2) {
            players.sort(Comparator.comparing(Player::getSymbol).reversed());
        }
    }

    public int getTurn(){
        return this.turn;
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
        while (true){
            for (Player player : players) {
                player.makeMove(board);
                board.render();
                if (checkWin(player)){
                    System.out.println( "Player" + player.getSymbol() + " won");
                    return;
                }
                if (checkFull()){
                    System.out.println("Draw");
                    return;
                }
            }
        }
    }

    public boolean checkWin(Player player){
        int size = board.getSize();
        int symbol = player.getSymbol();
        
        // Check rows
        for (int row = 0; row < size; row++) {
            boolean rowMatch = true;
            for (int col = 0; col < size; col++) {
                if (board.getCell(row, col) != symbol) {
                    rowMatch = false;
                    break;
                }
            }
            if (rowMatch) return true;
        }
        
        // Check columns
        for (int col = 0; col < size; col++) {
            boolean colMatch = true;
            for (int row = 0; row < size; row++) {
                if (board.getCell(row, col) != symbol) {
                    colMatch = false;
                    break;
                }
            }
            if (colMatch) return true;
        }
        
        // Check main diagonal (top-left to bottom-right)
        boolean mainDiag = true;
        for (int i = 0; i < size; i++) {
            if (board.getCell(i, i) != symbol) {
                mainDiag = false;
                break;
            }
        }
        if (mainDiag) return true;
        
        // Check anti-diagonal (top-right to bottom-left)
        boolean antiDiag = true;
        for (int i = 0; i < size; i++) {
            if (board.getCell(i,size-i-1) != symbol) {
                antiDiag = false;
                break;
            }
        }
        if (antiDiag) return true;
        
        return false;
    }

    public boolean checkFull(){
        for (int row = 0; row < board.getSize(); row++){
            for (int col = 0; col < board.getSize(); col++){
                if (board.getCell(row, col) == 0){
                    return false;
                }
            }
        }
        return true;
    }

    
}
