package vgu.pe2026.ttt.basic;


public abstract class Player {
    protected int symbol;

    public int getSymbol(){
        return symbol;
    }

    Player(int symbol){
        this.symbol = symbol;
    }

    public abstract void makeMove(Board board);
}


