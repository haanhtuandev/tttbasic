package vgu.pe2026.ttt.server;

import java.io.PrintStream;

public abstract class Player {
    protected int symbol;
    private PrintStream printer;

    public int getSymbol(){
        return symbol;
    }

    Player(int symbol){
        this.symbol = symbol;
    }

    public abstract boolean makeMove(Board board);
}


