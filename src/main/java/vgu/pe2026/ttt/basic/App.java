package vgu.pe2026.ttt.basic;

import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        Board board = new Board(3);
        Player player1 = new BotPlayer(2);
        Player player2 = new HumanPlayer(1);

        Game game = new Game();
        game.setPlayers(List.of(player1, player2));
        game.setBoard(board);

        if (args.length != 1){
            System.err.println("Invalid arguments (not enough arguments)");
            return;
        }

        try {
            int turn = Integer.parseInt(args[0]);
            game.setTurn(turn);
        }
        catch(NumberFormatException e){
            System.err.println("Invalid arguments");
        }
        game.play();
        
    }
}
