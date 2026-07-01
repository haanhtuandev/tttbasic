package vgu.pe2026.ttt.server_multithreaded_pool;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class HumanPlayer extends Player {

    private final Scanner scanner;
    private final PrintStream printer;

    public HumanPlayer(int symbol, InputStream in, PrintStream printer) {
        super(symbol);
        this.scanner = new Scanner(in);
        this.printer = printer;
    }


    @Override
    public boolean makeMove(Board board) {
        while (true) {
            printer.println("Player#" + symbol + "'s move: ");
            try {
                String input = scanner.nextLine();
                if (input.equals("q")) {
                    return false;
                }
                if (!isInteger(input)) {
                    printer.println("Please, input a valid number [1-9]");
                    continue; 
                }
    
                int move = Integer.parseInt(input);
    
                if (!board.isValidMove(move)) {
                    printer.println("The cell is occupied!");
                    continue;
                }
    
                board.setCell(move, symbol);
                return true;
            } catch (Exception e) {
                return false;
            }
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
