package vgu.pe2026.ttt.basic;


public class Board {
    private int[][] grid;
    private int size;

    public Board(int size){
        this.size = size;
        grid = new int[size][size];
    }

    public boolean setCell(int move, int symbol) {
        int row = mapMoveToCoordinate(move, this.size)[0];
        int col = mapMoveToCoordinate(move, this.size)[1];
        if (row < 0 || row >= size || col < 0 || col >= size) {            
            return false;
        }         
        if (grid[row][col] != 0) {             
            return false;  // Cell already occupied
        }
        grid[row][col] = symbol;
        return true;
    }


    public int getCell(int row, int col){
        return grid[row][col];
    }

    public void setSize(int size){
        this.size = size;
    }

    // public int[][] getGrid(){
    //     return this.grid;
    // }
    public int getSize(){
        return this.size;
    }
    private int[] mapMoveToCoordinate(int choice, int size){
        int row = (choice - 1) / size;
        int col = (choice - 1) % size;
        return new int[]{row, col};
    }


    public void render(){
        // System.out.println("-------------------\nTIC TAC TOE\n");
        System.err.println("\n");
        for (int row = 0; row < size; row++){
            System.out.print("| ");
            for (int col = 0; col < size; col++){
                System.out.print(grid[row][col] + " | ");
            }
            System.out.println("\n");
        }
        System.err.println("\n");
        // System.out.println("\n-------------------");

    }
 
}
