package vgu.pe2026.ttt.basic;
public class BotPlayer extends Player {
    public BotPlayer(int symbol) {
        super(symbol);
    }
    
    // public void makeMove(Board board){
    //     for (int row = 0; row < board.getSize(); row ++){
    //         for (int col = 0; col < board.getSize(); col++){
    //             if (board.getCell(row, col) == 0){
    //                 System.out.println("Player" + symbol +"'s move: ");
    //                  try {
    //                     Thread.sleep(2000);
    //                     board.setCell(row, col, symbol);
    //                  } catch (InterruptedException e) {
    //                     return;
    //                 }
                    
    //                 return;
    //             }
    //         }
    //     }
    // }
    @Override
    public void makeMove(Board board){
        System.out.println("Player" + symbol +"'s move: ");
        for (int cell = 1; cell <= board.getSize()*board.getSize(); cell++){
            if (board.setCell(cell, symbol)){
                return;
            }
        }
    }
}
