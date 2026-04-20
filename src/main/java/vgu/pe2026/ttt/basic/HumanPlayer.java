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
        String input = scanner.nextLine();

        while(isInteger(input) != true){
            if (input.equals("q")){
                System.exit(0);
            }
            System.out.println("Please, input a valid number [1-9]");
            System.out.println("Player" + symbol +"'s move: ");
            input = scanner.nextLine();
        }
       
        while(board.isValidMove(Integer.parseInt(input)) == false){
            System.out.println("The cell is occupied!");
            System.out.println("Player" + symbol +"'s move: ");
            input = scanner.nextLine();
        }
        int move = Integer.parseInt(input);
        board.setCell(move, symbol);

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
