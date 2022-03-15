package pieces;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import Chess.Board.BoardState;
import Chess.Pieces.*;
import Chess.Board.*;
import Chess.Pieces.Type;
import javafx.scene.image.ImageView;

public class BishopTest {
    BoardState board = new BoardState();

    Square sqkw = board.getState().getSquare(7, 7);
    Square sqkb = board.getState().getSquare(0, 0);
    Square sqStart = board.getState().getSquare(4, 3);

    King kw = new King(1, Type.KING, board, sqkw);
    King kb = new King(-1, Type.KING, board, sqkb);
    Bishop testSubject = new Bishop(1, Type.BISHOP, board, sqStart);

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
    public void move() throws FileNotFoundException {
        init();

        Square end = board.getState().getSquare(3, 4);

        boolean b = testSubject.move(end);

        assertEquals(true, b, "Did not move as expected");
    }

    @Test
    public void capture() throws FileNotFoundException {
        init();

        Square end = board.getState().getSquare(3, 4);
        Knight capture = new Knight(-1, Type.KNIGHT, board, end);
        capture.setImageView(new ImageView());
        end.setPiece(capture);

        testSubject.move(end);

        assertEquals(3, board.getPieces().size(), "Did not capture as expected");
    }

    @Test
    public void check() throws FileNotFoundException {
        init();

        kb.setSquare(board.getState().getSquare(3, 4));

        assertEquals(true, kb.isKingTargeted(kb.getColor()));
    }
}
