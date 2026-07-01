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
    public boolean makeMove(Board board) {
        while (true) {
            System.out.println("Player#" + symbol + "'s move: ");
            String input = scanner.nextLine();

            if (input.equals("q")) {
                return false;
            }
            if (!isInteger(input)) {
                System.out.println("Please, input a valid number [1-9]");
                continue; 
            }

            int move = Integer.parseInt(input);

            if (!board.isValidMove(move)) {
                System.out.println("The cell is occupied!");
                continue;
            }

            board.setCell(move, symbol);
            return true;
        }
    }


    public static boolean isInteger(String str){
        if (str == null){
            return false;
        }
        try {
            int input = Integer.parseInt(str);
            return input > 0 && input <= 9;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
