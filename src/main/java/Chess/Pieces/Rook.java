package Chess.Pieces;
import java.util.ArrayList;

import Chess.Board.*;


public class Rook extends Piece {
    
    public Rook(int color, Type type, BoardState board, Square initPos) {
        super(color, type, board, initPos);
    }

    public ArrayList<Square> getAvailableMoves() {
        ArrayList<Square> moves = new ArrayList<Square>();
        Square sqr, sql, squ, sqd;
        boolean sqlStop = false, sqrStop = false, squStop = false, sqdStop = false;

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
        return moves;
    }
}