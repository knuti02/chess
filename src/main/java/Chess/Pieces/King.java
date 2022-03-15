package Chess.Pieces;
import java.util.ArrayList;

import Chess.Board.*;


public class King extends Piece{
    boolean inCheck = false;
 
    public King(int color, Type type, BoardState board, Square initPos) {
        super(color, type, board, initPos);
    }
    
    public ArrayList<Square> getAvailableMoves() {

        ArrayList<Square> moves = new ArrayList<Square>();
        Square up, right, down, left, ur, dr, dl, ul, sq;

        //up-right
        ur = this.getBoard().getState().getSquare(this.getSquare().getY() + 1, this.getSquare().getX() + 1);
        if (ur != null && (!ur.isOccupied() || (ur.isOccupied() && ur.getPiece().getColor() != this.getColor()))) moves.add(ur);

        //right
        right = this.getBoard().getState().getSquare(this.getSquare().getY(), this.getSquare().getX() + 1);
        if (right != null && (!right.isOccupied() || (right.isOccupied() && right.getPiece().getColor() != this.getColor()))) moves.add(right);

        //down-right
        dr = this.getBoard().getState().getSquare(this.getSquare().getY() - 1, this.getSquare().getX() + 1);
        if (dr != null && (!dr.isOccupied() || (dr.isOccupied() && dr.getPiece().getColor() != this.getColor()))) moves.add(dr);

        //down
        down = this.getBoard().getState().getSquare(this.getSquare().getY() - 1, this.getSquare().getX());
        if (down != null && (!down.isOccupied() || (down.isOccupied() && down.getPiece().getColor() != this.getColor()))) moves.add(down);

        //down-left
        dl = this.getBoard().getState().getSquare(this.getSquare().getY() - 1, this.getSquare().getX() - 1);
        if (dl != null && (!dl.isOccupied() || (dl.isOccupied() && dl.getPiece().getColor() != this.getColor()))) moves.add(dl);

        //left
        left = this.getBoard().getState().getSquare(this.getSquare().getY(), this.getSquare().getX() - 1);
        if (left != null && (!left.isOccupied() || (left.isOccupied() && left.getPiece().getColor() != this.getColor()))) moves.add(left);

        //up
        up = this.getBoard().getState().getSquare(this.getSquare().getY() + 1, this.getSquare().getX() );
        if (up != null && (!up.isOccupied() || (up.isOccupied() && up.getPiece().getColor() != this.getColor()))) moves.add(up);

        //up-left
        ul = this.getBoard().getState().getSquare(this.getSquare().getY() + 1, this.getSquare().getX() - 1);
        if (ul != null && (!ul.isOccupied() || (ul.isOccupied() && ul.getPiece().getColor() != this.getColor()))) moves.add(ul);


        //castle right
        for (int i = 1; i < 4; i++) {
            sq = this.getBoard().getState().getSquare(this.getSquare().getY(), this.getSquare().getX() + i);
            if (i < 3 && sq != null && sq.isOccupied()) break;

            if (i == 3 && sq != null && sq.getPiece() != null && sq.getPiece().getType() == Type.ROOK 
            && !sq.getPiece().hasMoved() 
            && !this.hasMoved()
            && sq.getPiece().getColor() == this.getColor()) {
                moves.add(this.getBoard().getState().getSquare(this.getSquare().getY(), this.getSquare().getX() + 2));
            }
        }
        
        //castle left
        for (int i = 1; i < 5; i++) {
            sq = this.getBoard().getState().getSquare(this.getSquare().getY(), this.getSquare().getX() - i);
            if (i < 4 && sq != null && sq.isOccupied()) break;

            if (i == 4 && sq != null && sq.getPiece() != null && sq.getPiece().getType() == Type.ROOK 
            && !sq.getPiece().hasMoved() 
            && !this.hasMoved()
            && sq.getPiece().getColor() == this.getColor()) {
                moves.add(this.getBoard().getState().getSquare(this.getSquare().getY(), this.getSquare().getX() - 2));
            }
        
        }
        return moves;
    }
}
