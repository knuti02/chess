package Chess.Board;

import Chess.Pieces.Piece;

public class Square {
    //1 light | -1 dark
    private int color;
    private Piece piece;
    private int posY;
    private int posX;

    public Square(int color, int y, int x) {
        this.color = color;
        this.piece = null;
        this.posY = y;
        this.posX = x;
    }

    //for movement
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setEmpty() {
        this.piece = null;
    }

    public int getX() {
        return this.posX;
    }

    public int getY() {
        return this.posY;
    }

    public boolean isOccupied() {
        if (this.piece == null) {
            return false;
        }
        return true;
    }

    public String toString() {
        String c = (this.color == 1) ? "w" : "b";
        return "Pos. (row, col): (" + this.posY + ", " + this.posX + ") | Color: " + c + " | Piece: " + this.piece;
    }
}
