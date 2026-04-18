package vgu.pe2026.ttt.basic;
import java.io.PrintStream;

public class Board {
    private final int[][] grid;
    private int size;
    PrintStream printer;
    public Board(int size, PrintStream printer){
        this.size = size;
        this.printer = printer;
        grid = new int[size][size];
    }

    public boolean setCell(int move, int symbol) {
        int row = (move  - 1)/ 3;
        int col = (move - 1) % 3;
        // if (isValidMove(row, col)){
        grid[row][col] = symbol;
        //     return true;
        // }
        return true;
        
    }

    public boolean isValidMove(int row, int col){
        if (row < 0 || row >= size || col < 0 || col >= size) {            
            return false;
        }
        return grid[row][col] != 0;
        
    }

    public void setBoard(int[][] mockCoordinate){
        for (int i = 0; i < size ; i++) { 
            for (int j = 0; j < size; j++){
                grid[i][j] = mockCoordinate[i][j];
            }
        }
        
    }

    public int[][] getGrid(){
        return this.grid;
    }


    public int getCell(int row, int col){
        return grid[row][col];
    }

    public void setSize(int size){
        this.size = size;
    }

    public int getSize(){
        return this.size;
    }

    public void render(){
        // printer.print(System.lineSeparator());
        for (int row = 0; row < size; row++){
            printer.print("| ");
            for (int col = 0; col < size; col++){
                printer.print(grid[row][col] + " | ");
            }
            printer.print(System.lineSeparator());
        }
    }
    public boolean checkFull(){
        for (int row = 0; row < size; row++){
            for (int col = 0; col < size; col++){
                if (grid[row][col] == 0){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean checkWin(int symbol){

        // Check rows
        for (int row = 0; row < size; row++) {
            boolean rowMatch = true;
            for (int col = 0; col < size; col++) {
                if (grid[row][col] != symbol) {
                    rowMatch = false;
                    break;
                }
            }
            if (rowMatch) return true;
        }
        
        // Check columns
        for (int col = 0; col < size; col++) {
            boolean colMatch = true;
            for (int row = 0; row < size; row++) {
                if (grid[row][col] != symbol) {
                    colMatch = false;
                    break;
                }
            }
            if (colMatch) return true;
        }
        
        // Check main diagonal (top-left to bottom-right)
        boolean mainDiag = true;
        for (int i = 0; i < size; i++) {
            if (grid[i][i] != symbol) {
                mainDiag = false;
                break;
            }
        }
        if (mainDiag) return true;
        
        // Check anti-diagonal (top-right to bottom-left)
        boolean antiDiag = true;
        for (int i = 0; i < size; i++) {
            if (grid[i][size-i-1]!= symbol) {
                antiDiag = false;
                break;
            }
        }
        if (antiDiag) return true;
        
        return false;
    }
}
