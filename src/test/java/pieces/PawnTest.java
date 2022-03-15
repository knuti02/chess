package pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import Chess.Board.BoardState;
import Chess.Pieces.*;
import Chess.Board.*;
import Chess.Pieces.Type;
import javafx.scene.image.ImageView;


public class PawnTest {
    BoardState board = new BoardState();

    Square sqkw = board.getState().getSquare(7, 7);
    Square sqkb = board.getState().getSquare(0, 0);
    Square sqStart = board.getState().getSquare(6, 4);

    King kw = new King(1, Type.KING, board, sqkw);
    King kb = new King(-1, Type.KING, board, sqkb);
    Pawn testSubject = new Pawn(1, Type.PAWN, board, sqStart);

    private void init() {
        board.resetAbsolute();

        kw.setImageView(new ImageView());
        kb.setImageView(new ImageView());
        testSubject.setImageView(new ImageView());

        sqkw.setPiece(kw);
        sqkb.setPiece(kb);
        sqStart.setPiece(testSubject);
    }


    @Test
    public void testOneForward() throws FileNotFoundException {
        init();

        Square end = board.getState().getSquare(5, 4);

        board.setCurrentTurn(2);
        boolean b = testSubject.move(end);
        assertEquals(false, b, "Not his turn");

        board.setCurrentTurn(1);
        b = testSubject.move(end);
        assertEquals(true, b, "Did not move as planned");
    }
    
    @Test
    public void testTwoForward() throws FileNotFoundException {
        init();

        Square end = board.getState().getSquare(4, 4);
        boolean b = testSubject.move(end);
        
        assertEquals(true, b, "Did not move as planned");
    }

    @Test
    public void testEnPassant() throws FileNotFoundException {
        init();

        Square sqTarget = board.getState().getSquare(6, 5);
        Square end = board.getState().getSquare(5, 5);


        Pawn testSubjectTarget = new Pawn(-1, Type.PAWN, board, sqTarget);
        testSubjectTarget.setEnPassant(true);
        testSubjectTarget.setImageView(new ImageView());
        sqTarget.setPiece(testSubjectTarget);
        
        assertEquals(true, testSubjectTarget.isInEnPassant(), "En passant state wrong");
        assertEquals(true, testSubject.getAvailableMoves().contains(end), "Does not see it can do En passant");

        boolean b = testSubject.move(end);
        assertEquals(true, b, "Did not move as planned");    
    }

    @Test
    public void capture() throws FileNotFoundException {
        init();

        Square sqTarget = board.getState().getSquare(5, 5);
        Pawn testSubjectTarget = new Pawn(-1, Type.PAWN, board, sqTarget);

        testSubjectTarget.setImageView(new ImageView());
        sqTarget.setPiece(testSubjectTarget);

        boolean b = testSubject.move(testSubjectTarget.getSquare());
        assertEquals(true, b, "Did not move as planned");
    }

    @Test
    public void check() throws FileNotFoundException {
        init();

        kb.setSquare(board.getState().getSquare(5, 5));

        assertEquals(true, kb.isKingTargeted(kb.getColor()));
    }
}