package vgu.pe2026.ttt.basic;

import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        Board board = new Board(3, System.out);
        Player player1 = new HumanPlayer(2, System.in);
        Player player2 = new HumanPlayer(1, System.in);

        Game game = new Game();
        game.setPlayers(List.of(player1, player2));
        game.setBoard(board);

        if (args.length != 1){
            System.out.println("Please, input a valid option [1-2]");
            return;
        }

        try {
            int turn = Integer.parseInt(args[0]);
            game.setStartingPlayer(turn);
        }
        catch(Exception e){
            System.out.println("Please, input a valid option [1-2]");
            return;
        }
        game.play();
        
    }
}
