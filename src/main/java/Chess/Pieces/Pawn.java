package Chess.Pieces;
import java.util.ArrayList;

import Chess.Board.*;


public class Pawn extends Piece {

    public Pawn(int color, Type type, BoardState board, Square initPos) {
        super(color, type, board, initPos);
    }

    //find all available squares it can move to // ignores check
    public ArrayList<Square> getAvailableMoves() {
        ArrayList<Square> moves = new ArrayList<Square>();

        //one forward | BoardState -> ChessBoard -> Square
        Square squareForward = this.getBoard().getState().getSquare(this.getSquare().getY() - 1 * this.getColor() , this.getSquare().getX());
        //two forward
        Square twoForward = this.getBoard().getState().getSquare(this.getSquare().getY() - 2 * this.getColor() , this.getSquare().getX());

        if (squareForward != null && !squareForward.isOccupied()) {
            moves.add(squareForward);
        } 

        if (squareForward != null && !squareForward.isOccupied() && twoForward != null && !this.hasMoved() && !twoForward.isOccupied()) {
            moves.add(twoForward);
        }

        //forward left
        Square squareUpLeft = this.getBoard().getState().getSquare(this.getSquare().getY() - 1 * this.getColor() , this.getSquare().getX() - 1);
        //forward right
        Square squareUpRight = this.getBoard().getState().getSquare(this.getSquare().getY() - 1 * this.getColor() , this.getSquare().getX() + 1);

        if (squareUpLeft != null && squareUpLeft.isOccupied() && squareUpLeft.getPiece().getColor() != this.getColor()) {
            moves.add(squareUpLeft);
        }        

        if (squareUpRight != null && squareUpRight.isOccupied() && squareUpRight.getPiece().getColor() != this.getColor()) {
            moves.add(squareUpRight);
        }  

        //left (for enPassant)
        Square squareLeft = this.getBoard().getState().getSquare(this.getSquare().getY(), this.getSquare().getX() - 1);
        //right (for enPassant)
        Square squareRight = this.getBoard().getState().getSquare(this.getSquare().getY(), this.getSquare().getX() + 1);
        
        if (squareLeft != null && squareLeft.isOccupied() 
        && squareLeft.getPiece().isInEnPassant() && squareLeft.getPiece().getColor() != this.getColor()) moves.add(squareUpLeft);

        if (squareRight != null && squareRight.isOccupied() 
        && squareRight.getPiece().isInEnPassant() && squareRight.getPiece().getColor() != this.getColor()) moves.add(squareUpRight);
        
        return moves;
    }
}
