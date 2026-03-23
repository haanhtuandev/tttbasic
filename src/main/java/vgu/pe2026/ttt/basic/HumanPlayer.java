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

        try {
            int move = scanner.nextInt();
            int[] coords = mapMoveToCoordinate(move, board.getSize());
            int x = coords[0];
            int y = coords[1];

            if (move > board.getSize() * board.getSize()
                || move < 1
                || (board.getCell(x, y) != 0))
            {
                System.err.println("Illegal move");
                return;
            }

            board.setCell(x, y, symbol);
        } catch (Exception e) {
            System.err.println("Invalid input");
        }
        // Don't close scanner - closing System.in prevents future reads
    }


    private int[] mapMoveToCoordinate(int choice, int size){
        int row = (choice - 1) / size;
        int col = (choice - 1) % size;
        return new int[]{row, col};
    }
}
