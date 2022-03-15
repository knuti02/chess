package Chess.Pieces;
import java.util.ArrayList;

import Chess.Board.*;


public class Bishop extends Piece{
    
    public Bishop(int color, Type type, BoardState board, Square initPos) {
        super(color, type, board, initPos);
    }

    public ArrayList<Square> getAvailableMoves() {
        ArrayList<Square> moves = new ArrayList<Square>();
        Square squr, squl, sqdr, sqdl;
        boolean squrStop = false, squlStop = false, sqdrStop = false, sqdlStop = false;

        //up-right
        for (int i = 1; i < this.getBoard().getState().getLength()[1]; i++) {
            squr = this.getBoard().getState().getSquare(this.getSquare().getY() + i, this.getSquare().getX() + i);
            if (squr != null && squr.isOccupied()) squrStop = true;
            if (squr != null && (!squr.isOccupied() || 
                (squr.isOccupied() && this.getColor() != squr.getPiece().getColor()))) 
                moves.add(squr);
            if (squrStop) break;
        }

        //up-left
        for (int i = 1; i < this.getBoard().getState().getLength()[1]; i++) {
            squl = this.getBoard().getState().getSquare(this.getSquare().getY() + i, this.getSquare().getX() - i);
            if (squl != null && squl.isOccupied()) squlStop = true;
            if (squl != null && (!squl.isOccupied() || 
                (squl.isOccupied() && this.getColor() != squl.getPiece().getColor()))) 
                moves.add(squl);
            if (squlStop) break;
        }

        //down-right
        for (int i = 1; i < this.getBoard().getState().getLength()[0]; i++) {            
            sqdr = this.getBoard().getState().getSquare(this.getSquare().getY() - i, this.getSquare().getX() + i);
            if (sqdr != null && sqdr.isOccupied()) sqdrStop = true;
            if (sqdr != null && (!sqdr.isOccupied() || 
                (sqdr.isOccupied() && this.getColor() != sqdr.getPiece().getColor()))) 
                moves.add(sqdr);
            if (sqdrStop) break;
        }

        //down-left
        for (int i = 1; i < this.getBoard().getState().getLength()[0]; i++) {            
            sqdl = this.getBoard().getState().getSquare(this.getSquare().getY() - i, this.getSquare().getX() - i);
            if (sqdl != null && sqdl.isOccupied()) sqdlStop = true;
            if (sqdl != null && (!sqdl.isOccupied() || 
                (sqdl.isOccupied() && this.getColor() != sqdl.getPiece().getColor()))) 
                moves.add(sqdl);
            if (sqdlStop) break;
        }
        return moves;
    }
}
