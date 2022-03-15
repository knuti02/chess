package Chess.Pieces;
import java.util.ArrayList;

import Chess.Board.*;


public class Queen extends Piece{
    
    public Queen(int color, Type type, BoardState board, Square initPos) {
        super(color, type, board, initPos);
    }

    public ArrayList<Square> getAvailableMoves() {  

        ArrayList<Square> moves = new ArrayList<Square>();
        Square sqr, sql, squ, sqd, squr, squl, sqdr, sqdl;
        boolean sqlStop = false, sqrStop = false, squStop = false, sqdStop = false, 
        squrStop = false, squlStop = false, sqdrStop = false, sqdlStop = false;

        //right
        for (int i = 1; i < this.getBoard().getState().getLength()[1]; i++) {
            sqr = this.getBoard().getState().getSquare(this.getSquare().getY(), this.getSquare().getX() + i);
            if (sqr != null && sqr.isOccupied()) sqrStop = true;
            if (sqr != null && (!sqr.isOccupied() || 
                (sqr.isOccupied() && this.getColor() != sqr.getPiece().getColor()))) 
                moves.add(sqr);
            if (sqrStop) break;
        }

        //left
        for (int i = 1; i < this.getBoard().getState().getLength()[1]; i++) {
            sql = this.getBoard().getState().getSquare(this.getSquare().getY(), this.getSquare().getX() - i);
            if (sql != null && sql.isOccupied()) sqlStop = true;
            if (sql != null && (!sql.isOccupied() || 
                (sql.isOccupied() && this.getColor() != sql.getPiece().getColor()))) 
                moves.add(sql);
            if (sqlStop) break;
        }

        //up
        for (int i = 1; i < this.getBoard().getState().getLength()[0]; i++) {            
            squ = this.getBoard().getState().getSquare(this.getSquare().getY() + i, this.getSquare().getX());
            if (squ != null && squ.isOccupied()) squStop = true;
            if (squ != null && (!squ.isOccupied() || 
                (squ.isOccupied() && this.getColor() != squ.getPiece().getColor()))) 
                moves.add(squ);
            if (squStop) break;
        }

        //down
        for (int i = 1; i < this.getBoard().getState().getLength()[0]; i++) {            
            sqd = this.getBoard().getState().getSquare(this.getSquare().getY() - i, this.getSquare().getX());
            if (sqd != null && sqd.isOccupied()) sqdStop = true;
            if (sqd != null && (!sqd.isOccupied() || 
                (sqd.isOccupied() && this.getColor() != sqd.getPiece().getColor()))) 
                moves.add(sqd);
            if (sqdStop) break;
        }

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
