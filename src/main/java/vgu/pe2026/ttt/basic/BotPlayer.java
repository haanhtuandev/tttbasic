package vgu.pe2026.ttt.basic;
public class BotPlayer extends Player {
    public BotPlayer(int symbol) {
        super(symbol);
    }
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
