package Chess.Pieces;
import java.util.ArrayList;

import Chess.Board.*;


public class Knight extends Piece{
    
    public Knight(int color, Type type, BoardState board, Square initPos) {
        super(color, type, board, initPos);
    }

    public ArrayList<Square> getAvailableMoves() {
        Square ur, ru, rd, dr, dl, ld, lu, ul;
        ArrayList<Square> moves = new ArrayList<Square>();

        //up 2 right 1
        ur = this.getBoard().getState().getSquare(this.getSquare().getY() + 2, this.getSquare().getX() + 1);
        if (ur != null && (!ur.isOccupied() || (ur.isOccupied() && ur.getPiece().getColor() != this.getColor()))) moves.add(ur);

        //right 2 up 1
        ru = this.getBoard().getState().getSquare(this.getSquare().getY() + 1, this.getSquare().getX() + 2);
        if (ru != null && (!ru.isOccupied() || (ru.isOccupied() && ru.getPiece().getColor() != this.getColor()))) moves.add(ru);

        //right 2 down 1
        rd = this.getBoard().getState().getSquare(this.getSquare().getY() - 1, this.getSquare().getX() + 2);
        if (rd != null && (!rd.isOccupied() || (rd.isOccupied() && rd.getPiece().getColor() != this.getColor()))) moves.add(rd);

        //down 2 right 1
        dr = this.getBoard().getState().getSquare(this.getSquare().getY() - 2, this.getSquare().getX() + 1);
        if (dr != null && (!dr.isOccupied() || (dr.isOccupied() && dr.getPiece().getColor() != this.getColor()))) moves.add(dr);

        //down 2 left 1
        dl = this.getBoard().getState().getSquare(this.getSquare().getY() - 2, this.getSquare().getX() - 1);
        if (dl != null && (!dl.isOccupied() || (dl.isOccupied() && dl.getPiece().getColor() != this.getColor()))) moves.add(dl);

        //left 2 down 1
        ld = this.getBoard().getState().getSquare(this.getSquare().getY() - 1, this.getSquare().getX() - 2);
        if (ld != null && (!ld.isOccupied() || (ld.isOccupied() && ld.getPiece().getColor() != this.getColor()))) moves.add(ld);

        //left 2 up 1
        lu = this.getBoard().getState().getSquare(this.getSquare().getY() + 1, this.getSquare().getX() - 2);
        if (lu != null && (!lu.isOccupied() || (lu.isOccupied() && lu.getPiece().getColor() != this.getColor()))) moves.add(lu);

        //up 2 left 1
        ul = this.getBoard().getState().getSquare(this.getSquare().getY() + 2, this.getSquare().getX() - 1);
        if (ul != null && (!ul.isOccupied() || (ul.isOccupied() && ul.getPiece().getColor() != this.getColor()))) moves.add(ul);

        return moves;
    }
}
