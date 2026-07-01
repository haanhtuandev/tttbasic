package vgu.pe2026.ttt.server_multithreaded;

import java.io.PrintStream;

public class Game {
    
    private Board board;

    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayingPlayer;

    public void setStartingPlayer(int turn){
        if (turn == 1){
            currentPlayingPlayer = playerOne;
        } else {
            currentPlayingPlayer = playerTwo;
        }
    }

    public Board getBoard(){
        return this.board;
    }

    public void setPlayers(Player playerOne, Player playerTwo){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public void switchTurn(){
        if (currentPlayingPlayer == playerOne){
            currentPlayingPlayer = playerTwo;
        } else {
            currentPlayingPlayer = playerOne;
        }
    }
    
    public void play(PrintStream printer){
        printer.print("Hello!" + System.lineSeparator());
        board.render();

        while (true){
            if (currentPlayingPlayer.makeMove(board) == false){
                printer.println("End of the game");
                return;
            }
            board.render();
            if (board.checkWin(currentPlayingPlayer.getSymbol())){
                printer.println( "Player#" + currentPlayingPlayer.getSymbol() + " won!");
                return;
            }
            if (board.checkFull()){
                printer.println("It is a draw!");
                return;
            }
            switchTurn();
        }
    }   
}

