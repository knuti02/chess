package Chess.Pieces;

import Chess.Board.*;
import Chess.main.PromotionController;
import Chess.main.Saver;
import Chess.main.WinController;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class Piece {
    //1 light | -1 dark
    private int color;
    private Type type;
    private Square pos;
    private BoardState board;
    private ImageView imageView;
    private boolean hasMoved, inEnPassant;
    private String id;
    private Saver turnBack;

    public Piece(int color, Type type, BoardState board, Square initPos) {
        this.board = board;
        this.color = color;
        this.type = type;
        this.pos = initPos;
        hasMoved = false;
        turnBack = new Saver(this.board);
    }

    public boolean castle(Square end) throws FileNotFoundException {
        if (this.getType() == Type.KING && Math.abs(this.getSquare().getX() - end.getX()) == 2 ) {
            if (isKingTargeted(this.getColor())) return false;

            //right
            if ((this.getSquare().getX() - end.getX()) == -2) {
                if (isTargeted(this.getBoard().getState().getSquare(this.getSquare().getY(), this.getSquare().getX() + 1), this.getColor() * -1)) return false;
                if (isTargeted(this.getBoard().getState().getSquare(this.getSquare().getY(), this.getSquare().getX() + 2), this.getColor() * -1)) return false;

                turnBack.save("DO_NOT_TOUCH", true);
                this.setSquare(end);
                this.getBoard().getState().getSquare(this.getSquare().getY(), this.getSquare().getX() + 1).
                getPiece().setSquare(this.getBoard().getState().getSquare(this.getSquare().getY(), this.getSquare().getX() - 1));
                
                return true;
            }
            
            //left
            if (isTargeted(this.getBoard().getState().getSquare(this.getSquare().getY(), this.getSquare().getX() - 1), this.getColor() * -1)) return false;
            if (isTargeted(this.getBoard().getState().getSquare(this.getSquare().getY(), this.getSquare().getX() - 2), this.getColor() * -1)) return false;

            turnBack.save("DO_NOT_TOUCH", true);
            this.setSquare(end);
            this.getBoard().getState().getSquare(this.getSquare().getY(), this.getSquare().getX() - 2)
            .getPiece().setSquare(this.getBoard().getState().getSquare(this.getSquare().getY(), this.getSquare().getX() + 1));
            
            return true;                
        }
        return false;
    }

    //finds king
    public Piece kingPos(int c) {
        for (Piece p : board.getPieces()) {
            if (p.getType() == Type.KING && p.getColor() == c) return p;
        } return null;
    }

    //see if square is targeted by any piece of color c
    public boolean isTargeted(Square sq, int c) {
        for (Piece p : board.getPieces()) {
            if (p.getColor() == c && p.getAvailableMoves().contains(sq)) return true;
        } return false;
    }

    //see if king is targeted by using kingPos and isTargeted, where c = c * -1 
    public boolean isKingTargeted(int c) {
        return isTargeted(kingPos(c).getSquare(), c * -1);
    }

    //see if Square end is a valid move
    public boolean isValidMove(Piece p, Square end, int c) {
        Square originalSquare = p.getSquare();
        Piece target = end.getPiece();
        boolean validMove;

        if (p.getType() == Type.PAWN && p.getSquare().getX() != end.getX() && !end.isOccupied()) {
            target = p.getBoard().getState().getSquare(end.getY() + 1 * c, end.getX()).getPiece();

            p.getBoard().getState().getSquare(end.getY() + 1 * c, end.getX()).setEmpty();
            p.setSquare(end);
            validMove = !isKingTargeted(c);
            p.getBoard().getState().getSquare(end.getY() + 1 * c, end.getX()).setPiece(target);
            p.setSquare(originalSquare);
        }

        else {
            p.simulateMove(end);
            validMove = !isKingTargeted(c);
            p.simulateMove(originalSquare);
            end.setPiece(target);
        }

        return validMove;
    }

    public boolean noMoves(int c) {
        //see if king can move
        for (Square sq : kingPos(c).getAvailableMoves()) {
            if (isValidMove(kingPos(c), sq, c))  return false;
        }

        //se if any other piece can move
        for (Piece p : board.getPieces()) {
            if (p.getColor() == c && p.getType() != Type.KING) {
                for (Square sq : p.getAvailableMoves()) {
                    if (isValidMove(p, sq, c)) return false;
                }
            }
        }

        return true;
    }

    public boolean isInCheckMate(int c) {
        return (isKingTargeted(c) && noMoves(c));
    }

    public void setEnPassant(boolean bool) {
        this.inEnPassant = bool;
    }
    public boolean isInEnPassant() {
        return this.inEnPassant;
    }
    public boolean enPassant(Square end) throws FileNotFoundException {
        if (this.getType() == Type.PAWN && this.getSquare().getX() != end.getX() && !end.isOccupied()) { 
            this.getBoard().getState().getSquare(end.getY() + 1 * this.getColor(), end.getX()).getPiece().setEnPassant(true);  
            turnBack.save("DO_NOT_TOUCH", true);
            this.getBoard().getState().getSquare(end.getY() + 1 * this.getColor(), end.getX()).getPiece().setEnPassant(false);  
            
            this.getBoard().getState().getSquare(end.getY() + 1 * this.getColor(), end.getX()).getPiece().getImageView().setVisible(false);
            this.getBoard().getState().getSquare(end.getY() + 1 * this.getColor(), end.getX()).setEmpty();

            this.setSquare(end);
            return true;
        }
        return false;
    }

    public void promote() {
        // New window (Stage)
        try {
            PromotionController promotion = new PromotionController(this, this.getColor(), board.getStyle());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Promotion.fxml"));
            loader.setController(promotion);
            Parent root = loader.load();

            Stage secondaryStage = new Stage(StageStyle.UNDECORATED);
            secondaryStage.setScene(new Scene(root));
            // Specifies the modality for new window.
            secondaryStage.initModality(Modality.WINDOW_MODAL);

            //YESSSS
            secondaryStage.initOwner(this.getBoard().getPieces().
            get(0).getImageView().getScene().getWindow());
            //YESSS

            secondaryStage.setTitle("Promote");
            secondaryStage.setResizable(false);

            secondaryStage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void endScreen(int winner, boolean isDraw) {
        try {
            WinController winScreen = new WinController(this.getColor(), isDraw, this.getBoard());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("WinScreen.fxml"));
            loader.setController(winScreen);
            Parent root = loader.load();
            

            Stage secondaryStage = new Stage(); //StageStyle.UNDECORATED
            secondaryStage.setScene(new Scene(root));
            // Specifies the modality for new window.
            secondaryStage.initModality(Modality.WINDOW_MODAL);

            // Specifies the owner Window (parent) for new window
            //secondaryStage.initOwner(((Node) (event.getSource())).getScene().getWindow());

            //YESSSS
            secondaryStage.initOwner(this.getImageView().getScene().getWindow());
            //YESSS

            secondaryStage.getIcons().add(new Image("/images/icon.png"));
            secondaryStage.setTitle(this.getColor() == 1 ? "WHITE WON!!!" : "BLACK WON!!!");
            if (isDraw) secondaryStage.setTitle("IT'S A DRAW!!!");
            secondaryStage.setResizable(false);
            secondaryStage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        } 
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
        this.id = imageView.getId();

        this.imageView.setOnMousePressed(event -> imagePressed(event, this));
        this.imageView.setOnMouseDragged(event -> imageDrag(event, this));
        this.imageView.setOnMouseReleased(event -> {
            try {
                imageReleased(event, this);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public void setSquare(Square end) {
        this.pos.setEmpty();
        this.pos = end;
        end.setPiece(this);
        this.hasMoved = true;

        GridPane.setRowIndex(this.getImageView(), this.getSquare().getY() + 1);
        GridPane.setColumnIndex(this.getImageView(), this.getSquare().getX() + 1);
    }

    //for check-mechanics
    public void simulateMove(Square end) {
        this.pos.setEmpty();
        this.pos = end;
        end.setPiece(this);
    }

    public BoardState getBoard() {
        return this.board;
    }
    public Square getSquare() {
        return this.pos;
    }
    public Type getType() {
        if (this.type == null) return null;
        return this.type;
    }
    public int getColor() {
        return this.color;
    };
    public boolean hasMoved() {
        return this.hasMoved;
    }
    public void setHasMoved(boolean bool) {
        this.hasMoved = bool;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public abstract ArrayList<Square> getAvailableMoves(); //find all squares it can move to with custom rule check. return an array of squares this can move to.

    public boolean move(Square end) throws FileNotFoundException {//if success (in available moves, wont set king in check, etc.): do this.setSquare(Square end)
        if (this.getAvailableMoves().contains(end) && 
        (this.getBoard().getCurrentTurn() % 2 == 0 ? this.getColor() == -1 : this.getColor() == 1)) {
            
            if (!isValidMove(this, end, this.getColor())) return false;

            //remove enPassants
            for (Piece piece : this.board.getPieces()) {
                if (piece.getType() == Type.PAWN) piece.setEnPassant(false);
            }

            if (castle(end)) { 

                if (!isKingTargeted(this.getColor() * -1) && noMoves(this.getColor() * -1)) endScreen(this.getColor(), true);
                if (isInCheckMate(this.getColor() * -1)) endScreen(this.getColor(), false);

                this.board.nextTurn();
                return true;
            } else if (this.getType() == Type.KING && Math.abs(this.getSquare().getX() - end.getX()) == 2) return false;

            if (enPassant(end)) {
                if (!isKingTargeted(this.getColor() * -1) && noMoves(this.getColor() * -1)) endScreen(this.getColor(), true);
                if (isInCheckMate(this.getColor() * -1)) endScreen(this.getColor(), false);

                this.board.nextTurn();
                return true;
            }

            //twoForward (for enPassant)
            if (this.getType() == Type.PAWN && Math.abs(this.getSquare().getY() - end.getY()) == 2) this.setEnPassant(true);

            turnBack.save("DO_NOT_TOUCH", true);

            if (end.isOccupied()) end.getPiece().getImageView().setVisible(false);
            this.setSquare(end);

            //pawn upgrade
            if (this.getType() == Type.PAWN && 
            ((this.getColor() == 1 && this.getSquare().getY() == 0) 
            || this.getColor() == -1 && this.getSquare().getY() == 7)) this.promote(); //noe show and wait ting?

            //check after release that it will lead to checkmate or draw
            if (!isKingTargeted(this.getColor() * -1) && noMoves(this.getColor() * -1)) endScreen(this.getColor(), true);
            if (isInCheckMate(this.getColor() * -1)) endScreen(this.getColor(), false);

            this.board.nextTurn();
            return true;
        }
        return false;
    }

    //ready the squares for printing || only for testing purposes
    public ArrayList<ArrayList<Integer>> movesForPrint() {
        ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();
        
        int e = 0;
        for (Square i : this.getAvailableMoves()) {
            moves.add(new ArrayList<Integer>());
            moves.get(e).add(i.getY());
            moves.get(e).add(i.getX());
            e++;
        }
        return moves;
    }
    
    //for dragging mechanics
    public void imagePressed(MouseEvent event, Piece p) {
        System.out.println(p);
    }

    public void imageDrag(MouseEvent event, Piece p) {
        p.imageView.setX(event.getX() - p.imageView.getFitWidth() / 2);
        p.imageView.setY(event.getY() - p.imageView.getFitHeight() / 2);

        p.imageView.setTranslateX(p.imageView.getX());
        p.imageView.setTranslateY(p.imageView.getY());
    }

    public void imageReleased(MouseEvent event, Piece p) throws FileNotFoundException {
        if (p.move(p.getBoard().getState().getSquare(
            p.getSquare().getY() + (int) Math.round(p.imageView.getY() / 50),
            p.getSquare().getX() + (int) Math.round(p.imageView.getX() / 50))
            )) {
                p.imageView.setX(Math.round(p.imageView.getX() / 50) * 50);
                p.imageView.setY(Math.round(p.imageView.getY() / 50) * 50);
                p.imageView.setTranslateX(p.imageView.getX());
                p.imageView.setTranslateY(p.imageView.getY());
        }       
                
        p.imageView.setX(0);
        p.imageView.setY(0);
        p.imageView.setTranslateX(p.imageView.getX());
        p.imageView.setTranslateY(p.imageView.getY());

        System.out.println(
            String.valueOf(p.getSquare().getY() + Math.round(p.imageView.getY() / 50) + " | " +
            String.valueOf(p.getSquare().getX() + Math.round(p.imageView.getX() / 50))
            ));
    }

    //toString
    public String toString() {
        String c;
        c = (this.getColor() == 1) ? "w" : "b";

        return "Type: " + this.getType()+ "; color: " + c + 
        "; position (Y|X): "  + this.getSquare().getY()+"|"+ this.getSquare().getX() + 
        "; has moved: " + this.hasMoved() + "; inEnPassant: " + this.inEnPassant + 
        "; Id: " + this.getId() + "; moves: " + movesForPrint();
    }
}
