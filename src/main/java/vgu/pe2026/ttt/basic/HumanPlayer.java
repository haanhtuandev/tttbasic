package vgu.pe2026.ttt.basic;
import java.util.Scanner;
public class HumanPlayer extends Player {

    

    public HumanPlayer(int symbol) {
        super(symbol);
    }

    @Override
    public void makeMove(Board board){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Player" + symbol +"'s move: ");
        int move = scanner.nextInt();
        
        // loop to handle invalid input
        while (board.setCell(move, symbol) != true){
            System.out.println("Invalid move!");     
            System.out.println("Player" + symbol +"'s move: ");            
            move = scanner.nextInt();
        }
    }
}
