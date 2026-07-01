package vgu.pe2026.ttt.server_multithreaded_pool;

import java.io.PrintStream;

public class BotPlayer extends Player {
    private final PrintStream printer;
    public BotPlayer(int symbol, PrintStream printer) {
        super(symbol);
        this.printer = printer;
    }
    @Override
    public boolean makeMove(Board board){
        printer.println("Player#" + this.symbol +"'s move: ");
        for (int cell = 1; cell <= board.getSize()*board.getSize(); cell++){
            if (board.isValidMove(cell)){
                board.setCell(cell, symbol);
                return true;
            }
        }
        return false;
    }
}
