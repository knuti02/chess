package Chess.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import Chess.Board.BoardState;
import Chess.Board.Square;
import Chess.Pieces.*;

public class Saver {
    private BoardState board;

    public Saver(BoardState board) {
        this.board = board;
    }

    public void reset(String fileName) {
        String s = "";
        try (PrintWriter writer = new PrintWriter(new File(getFilePath(fileName)))) {
            writer.println(s);
        } catch (Exception e) {
            System.out.println("Failed to save.");
            System.out.println(new File(".").getAbsolutePath());
        }
    }

    public void save(String fileName, boolean turnBack) throws FileNotFoundException {
        String s = String.valueOf(board.getCurrentTurn()) + "\n";
        for (Piece p : board.getPieces()) {
            s += p.toString() + "\n";
        }
        
        try (PrintWriter writer = new PrintWriter(new File(getFilePath(fileName)))) {
            writer.println(s);
            if (!turnBack) System.out.println("Game successfully saved.");
        } catch (Exception e) {
            System.out.println("Failed to save.");
            System.out.println(new File(".").getAbsolutePath());
        }
    }

    public void load(String fileName) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(getFilePath(fileName)))) {
            int turn = 0;
            try { 
                turn = (Integer.valueOf(scanner.nextLine()));
            } catch (Exception e) {
                return ;
            }
            this.board.resetAbsolute();
            this.board.setCurrentTurn(turn);
            while (scanner.hasNextLine()) {
                String[] pieceInfo = scanner.nextLine().split(";");
                if (pieceInfo[0] == "") break;
                String type = pieceInfo[0].split(": ")[1];
                String color = pieceInfo[1].split(": ")[1];
                String[] pos = pieceInfo[2].split(": ")[1].split("|");
                String hasMoved = pieceInfo[3].split(": ")[1];
                String inEnPassant = pieceInfo[4].split(": ")[1];
                String id = pieceInfo[5].split(": ")[1];

                Square sq = board.getState().getSquare(Integer.valueOf(pos[0]), Integer.valueOf(pos[2]));
                Piece p;

                if (type.equals("PAWN") ){
                    Type t = Type.PAWN;
                    p = new Pawn((color.equals("w") ? 1 : -1), t, this.board, sq);
                } else if (type.equals("ROOK") ){
                    Type t = Type.ROOK;
                    p = new Rook((color.equals("w") ? 1 : -1), t, this.board, sq);
                } else if (type.equals("BISHOP")) {
                    Type t = Type.BISHOP;
                    p = new Bishop((color.equals("w") ? 1 : -1), t, this.board, sq);
                } else if (type.equals("KNIGHT")) {
                    Type t = Type.KNIGHT;
                    p = new Knight((color.equals("w") ? 1 : -1), t, this.board, sq);
                } else if (type.equals("QUEEN")) {
                    Type t = Type.QUEEN;
                    p = new Queen((color.equals("w") ? 1 : -1), t, this.board, sq);
                } else if (type.equals("KING") ){
                    Type t = Type.KING;
                    p = new King((color.equals("w") ? 1 : -1), t, this.board, sq);
                } else {System.out.println(type); p = null;}

                p.setEnPassant(inEnPassant.equals("true") ? true : false);
                p.setHasMoved(hasMoved.equals("true") ? true : false);
                p.setId(id);

                board.getState().getSquare(Integer.valueOf(pos[0]), Integer.valueOf(pos[2])).setPiece(p);
            }

            System.out.println("Game Loaded.");
            save("DO_NOT_TOUCH", true);
        }
    }

    public static String getFilePath(String filename) {
		return Saver.class.getResource("/Saves/").getFile() + filename + ".txt";
	}
}