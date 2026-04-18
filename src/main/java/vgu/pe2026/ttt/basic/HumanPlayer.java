package vgu.pe2026.ttt.basic;
import java.io.InputStream;
import java.util.Scanner;
public class HumanPlayer extends Player {

    private final Scanner scanner;

    public HumanPlayer(int symbol, InputStream in) {
        super(symbol);
        this.scanner = new Scanner(in);
    }


    @Override
    public void makeMove(Board board){
        System.out.println("Player" + symbol +"'s move: ");   
        String input = scanner.nextLine();

        int move;
        if (isInteger(input)){
            move = Integer.parseInt(input);        
            board.setCell(move, symbol);
        }
    }

    public static boolean isInteger(String str){
        if (str == null){
            return false;
        }
        try {
            int input = Integer.parseInt(str);
            return input >= 0 && input <= 9;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
