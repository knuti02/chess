package Chess.Board;

import Chess.Pieces.*;
import java.util.ArrayList;

public class BoardState {
    ChessBoard board;
    int currentTurn, style;

    public BoardState() {
        this.board = new ChessBoard(8,8);
        this.init();
        this.currentTurn = 1;
        this.style = 0;
    }

    private void init() {
        //place pawns
        for (int i = 0; i < this.board.getLength()[1]; i++) {
            this.board.getSquare(1, i).setPiece(new Pawn(-1, Type.PAWN, this, this.board.getSquare(1, i)));
            this.board.getSquare(6, i).setPiece(new Pawn(1, Type.PAWN, this, this.board.getSquare(6, i)));
        }

        //rooks
        for (int i = 0; i < 8; i+=7) {
            this.board.getSquare(0, i).setPiece(new Rook(-1, Type.ROOK, this, this.board.getSquare(0, i)));
            this.board.getSquare(7, i).setPiece(new Rook(1, Type.ROOK, this, this.board.getSquare(7, i)));
        }

        //knight
        for (int i = 1; i < 8; i+=5) {
            this.board.getSquare(0, i).setPiece(new Knight(-1, Type.KNIGHT, this, this.board.getSquare(0, i)));
            this.board.getSquare(7, i).setPiece(new Knight(1, Type.KNIGHT, this, this.board.getSquare(7, i)));
        }

        //bishops
        for (int i = 2; i < 8; i+=3) {
            this.board.getSquare(0, i).setPiece(new Bishop(-1, Type.BISHOP, this, this.board.getSquare(0, i)));
            this.board.getSquare(7, i).setPiece(new Bishop(1, Type.BISHOP, this, this.board.getSquare(7, i)));
        }

        //king & queen
        this.board.getSquare(0, 4).setPiece(new King(-1, Type.KING, this, this.board.getSquare(0, 4)));
        this.board.getSquare(7, 4).setPiece(new King(1, Type.KING, this, this.board.getSquare(7, 4)));

        this.board.getSquare(0, 3).setPiece(new Queen(-1, Type.QUEEN, this, this.board.getSquare(0, 3)));
        this.board.getSquare(7, 3).setPiece(new Queen(1, Type.QUEEN, this, this.board.getSquare(7, 3)));
    } 

    public void resetAbsolute() {
        for (Piece p : getPieces()) {
            p.getSquare().setEmpty();
        }
        this.currentTurn = 1;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int turn) {
        this.currentTurn = turn;
    }

    public void nextTurn() {
        this.currentTurn++;
    }

    public ChessBoard getState() {
        return this.board;
    }

    public void setStyle(int style) {
        this.style = style;
    }
    
    public int getStyle() {
        return this.style;
    }

    public ArrayList<Piece> getPieces() {
        ArrayList<Piece> pieces = new ArrayList<Piece>();
        for (int row = 0; row < this.board.getLength()[0]; row++) {
            for (int col = 0; col < this.board.getLength()[1]; col++) {
                if (this.board.getSquare(row, col).isOccupied())
                    pieces.add(this.board.getSquare(row, col).getPiece());
            }
        }
        return pieces;
    }

    public String toString() {
        String m = "Following board state:\n Turn: " + this.getCurrentTurn() + "\n";

        for (int i = 0; i < this.board.getLength()[0]; i++) {
            for (int e = 0; e < this.board.getLength()[1]; e++) {
                m += this.board.getSquare(i,e) + "\n";
            }
        }

        return m;
    }
}