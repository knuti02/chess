package pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import Chess.Board.BoardState;
import Chess.Pieces.*;
import Chess.Board.*;
import Chess.Pieces.Type;
import javafx.scene.image.ImageView;

public class KingTest {
    BoardState board = new BoardState();

    Square sqkw = board.getState().getSquare(7, 7);
    Square sqkb = board.getState().getSquare(0, 0);
    Square sqStart = board.getState().getSquare(4, 4);

    King kw = new King(1, Type.KING, board, sqkw);
    King kb = new King(-1, Type.KING, board, sqkb);

    private void init() {
        board.resetAbsolute();

        kw.setImageView(new ImageView());
        kb.setImageView(new ImageView());

        sqkw.setPiece(kw);
        sqkb.setPiece(kb);
    }

    @Test
    public void move() throws FileNotFoundException {
        init();

        Square end = board.getState().getSquare(6, 6);

        boolean b = kw.move(end);

        assertEquals(true, b, "Did not move as expected");
    }

    @Test //cant walk into a spot where he will be in check 
    public void illegalMove() throws FileNotFoundException {
        init();

        Square target1 = board.getState().getSquare(6, 0);
        Rook rook1 = new Rook(-1, Type.ROOK, board, target1);
        rook1.setImageView(new ImageView());
        target1.setPiece(rook1);

        boolean b = kw.move(board.getState().getSquare(6, 7));

        assertEquals(false, b, "Did not move as expected");
    }

    @Test
    public void capture() throws FileNotFoundException {
        init();

        Square end = board.getState().getSquare(6, 6);
        Knight capture = new Knight(-1, Type.KNIGHT, board, end);
        capture.setImageView(new ImageView());
        end.setPiece(capture);

        kw.move(end);

        assertEquals(2, board.getPieces().size(), "Did not capture as expected");
    }

    @Test
    public void castleRight() throws FileNotFoundException {
        init();

        Square sq = kw.getSquare();
        kw.setSquare(board.getState().getSquare(7, 4));
        kw.setHasMoved(false);

        Rook rook = new Rook(1, Type.ROOK, board, sq);
        rook.setImageView(new ImageView());
        sq.setPiece(rook);

        boolean b = kw.move(board.getState().getSquare(7, 6));

        assertEquals(true, b, "Did not move as expected");
        assertEquals(board.getState().getSquare(7, 5), rook.getSquare(), "Did not move as expected");
    }

    @Test
    public void castleLeft() throws FileNotFoundException {
        init();

        Square sq = board.getState().getSquare(7, 0);
        kw.setSquare(board.getState().getSquare(7, 4));
        kw.setHasMoved(false);

        Rook rook = new Rook(1, Type.ROOK, board, sq);
        rook.setImageView(new ImageView());
        sq.setPiece(rook);

        boolean b = kw.move(board.getState().getSquare(7, 2));

        assertEquals(true, b, "Did not move as expected");
        assertEquals(board.getState().getSquare(7, 3), rook.getSquare(), "Did not move as expected");
    }

    @Test
    public void preventCastleRight() throws FileNotFoundException {
        init();

        Square sq = kw.getSquare();
        Square target = board.getState().getSquare(2, 0);
        kw.setSquare(board.getState().getSquare(7, 4));
        kw.setHasMoved(false);

        Rook rook = new Rook(1, Type.ROOK, board, sq);
        rook.setImageView(new ImageView());
        sq.setPiece(rook);

        Bishop bishop = new Bishop(-1, Type.BISHOP, board, target);
        bishop.setImageView(new ImageView());
        target.setPiece(bishop);

        boolean b = kw.move(board.getState().getSquare(7, 6));

        assertEquals(false, b, "Did not move as expected");
    }

    @Test
    public void preventCastleLeft() throws FileNotFoundException {
        init();

        Square sq = board.getState().getSquare(7, 0);
        Square target = board.getState().getSquare(3, 7);
        kw.setSquare(board.getState().getSquare(7, 4));
        kw.setHasMoved(false);

        Rook rook = new Rook(1, Type.ROOK, board, sq);
        rook.setImageView(new ImageView());
        sq.setPiece(rook);

        Bishop bishop = new Bishop(-1, Type.BISHOP, board, target);
        bishop.setImageView(new ImageView());
        target.setPiece(bishop);

        boolean b = kw.move(board.getState().getSquare(7, 2));

        assertEquals(false, b, "Did not move as expected");
    }

    @Test
    public void inCheck() throws FileNotFoundException {
        init();
        
        Square target = board.getState().getSquare(1, 1);
        Bishop bishop = new Bishop(-1, Type.BISHOP, board, target);
        bishop.setImageView(new ImageView());
        target.setPiece(bishop);

        boolean b = kw.isKingTargeted(kw.getColor());

        assertEquals(true, b, "Was not targeted as expected");
    }

    @Test
    public void willCauseCheck() throws FileNotFoundException {
        init();

        Square target = board.getState().getSquare(1, 1);
        Bishop bishop = new Bishop(-1, Type.BISHOP, board, target);
        bishop.setImageView(new ImageView());
        target.setPiece(bishop);

        Square target3 = board.getState().getSquare(6, 6);
        Pawn pawn = new Pawn(1, Type.PAWN, board, target3);
        pawn.setImageView(new ImageView());
        target3.setPiece(pawn);

        boolean b = pawn.move(board.getState().getSquare(4, 6));

        assertEquals(false, b, "Did not interact as expected");
    }

    @Test //player is in checkmate
    public void inCheckmate1() throws FileNotFoundException {
        init();

        Square target1 = board.getState().getSquare(7, 0);
        Rook rook1 = new Rook(-1, Type.ROOK, board, target1);
        rook1.setImageView(new ImageView());
        target1.setPiece(rook1);

        Square target2 = board.getState().getSquare(6, 0);
        Rook rook2 = new Rook(-1, Type.ROOK, board, target2);
        rook2.setImageView(new ImageView());
        target2.setPiece(rook2);

        Square target3 = board.getState().getSquare(2, 6);
        Pawn pawn = new Pawn(1, Type.PAWN, board, target3);
        pawn.setImageView(new ImageView());
        target3.setPiece(pawn);

        boolean b = kw.isInCheckMate(kw.getColor());

        assertEquals(true, b, "Was not targeted as expected");
    }

    @Test //king can capture to escape checkmate
    public void inCheckmate2() throws FileNotFoundException {
        init();

        Square target1 = board.getState().getSquare(7, 6);
        Rook rook1 = new Rook(-1, Type.ROOK, board, target1);
        rook1.setImageView(new ImageView());
        target1.setPiece(rook1);

        Square target2 = board.getState().getSquare(6, 0);
        Rook rook2 = new Rook(-1, Type.ROOK, board, target2);
        rook2.setImageView(new ImageView());
        target2.setPiece(rook2);

        boolean b = kw.isInCheckMate(kw.getColor());

        assertEquals(false, b, "Was not targeted as expected");
    }

    @Test //player can block with another piece (rook on g6)
    public void inCheckmate3() throws FileNotFoundException {
        init();

        Square target1 = board.getState().getSquare(7, 6);
        Rook rook1 = new Rook(-1, Type.ROOK, board, target1);
        rook1.setImageView(new ImageView());
        target1.setPiece(rook1);

        Square target2 = board.getState().getSquare(6, 0);
        Rook rook2 = new Rook(-1, Type.ROOK, board, target2);
        rook2.setImageView(new ImageView());
        target2.setPiece(rook2);

        Square target3 = board.getState().getSquare(2, 6);
        Rook rook3 = new Rook(1, Type.ROOK, board, target3);
        rook3.setImageView(new ImageView());
        target3.setPiece(rook3);

        boolean b = kw.isInCheckMate(kw.getColor());

        assertEquals(false, b, "Was not targeted as expected");
    }
} 