package vgu.pe2026.ttt.client_singlethread;

public class TTTRequest {
    private String move;
    private String boardString;
    
    public TTTRequest(String move, String boardString) {
        this.move = move;
        this.boardString = boardString;
    }
    public String getMove() {
        return move;
    }
    public void setMove(String move) {
        this.move = move;
    }
    public String getBoardString() {
        return boardString;
    }
    public void setBoardString(String boardString) {
        this.boardString = boardString;
    }
    @Override
    public String toString() {
        return move + "," + boardString;
    }
   
    
    
}
