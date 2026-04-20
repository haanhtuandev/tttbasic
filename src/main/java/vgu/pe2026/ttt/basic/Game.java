package vgu.pe2026.ttt.basic;

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
    
    public void play(){
        System.out.print("Hello!" + System.lineSeparator());
        board.render();
        Thread gameThread = new Thread(
            () -> {
                while (true){
                    System.out.println("Player#" + currentPlayingPlayer.symbol +"'s move: ");
                    currentPlayingPlayer.makeMove(board);
                    board.render();
                    if (board.checkWin(currentPlayingPlayer.symbol)){
                        System.out.println( "Player#" + currentPlayingPlayer.getSymbol() + " won!");
                        return;
                    }
                    if (board.checkFull()){
                        System.out.println("It is a draw!");
                        return;
                    }
                    switchTurn();
                }
            }
        );
        gameThread.start();   
    }   
}
