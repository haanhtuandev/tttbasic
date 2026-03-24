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
            System.err.println("Invalid arguments");
            System.exit(1);
        }

        try {
            int turn = Integer.parseInt(args[0]);
            game.setStartingPlayer(turn);
        }
        catch(Exception e){
            System.err.println("Invalid arguments (only 1 or 2 is accepted)");
            System.exit(1);
        }
        game.play();
        
    }
}
