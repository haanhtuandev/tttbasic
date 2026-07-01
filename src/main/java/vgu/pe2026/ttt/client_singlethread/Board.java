package vgu.pe2026.ttt.client_singlethread;

public class Board {
    private final int[][] grid;
    private int size;
    public Board(int size){
        this.size = size;
        grid = new int[size][size];
    }

    public void setCell(int cell, int symbol) {
        int row = (cell  - 1)/ size;
        int col = (cell - 1) % size;
        grid[row][col] = symbol;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++){
                sb.append(grid[i][j]);
            }
        }
        return sb.toString();
    }
    public void render(){
        for (int row = 0; row < size; row++){
            System.out.print("| ");
            for (int col = 0; col < size; col++){
                System.out.print(grid[row][col] + " | ");
            }
            System.out.print(System.lineSeparator());
        }
    }
}
