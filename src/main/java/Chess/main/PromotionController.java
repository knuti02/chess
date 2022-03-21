package Chess.main;

import Chess.Pieces.*;

import java.util.ResourceBundle;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.event.EventHandler;

public class PromotionController implements Initializable {
    Piece piece;
    String c, v;
    int style;

    @FXML
    ImageView queen = new ImageView(), knight = new ImageView(), rook = new ImageView(), bishop = new ImageView();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(piece);
        v = style == 0 ? "" : "1";

        queen.setImage(new Image("/images/Custom" + Integer.toString(this.style) + "/" + c + "_queen" + v +".png"));
        knight.setImage(new Image("/images/Custom" + Integer.toString(this.style) + "/" + c + "_knight" + v +".png"));
        rook.setImage(new Image("/images/Custom" + Integer.toString(this.style) + "/" + c + "_rook" + v +".png"));
        bishop.setImage(new Image("/images/Custom" + Integer.toString(this.style) + "/" + c + "_bishop" + v +".png"));

        queen.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Queen promoted = new Queen(piece.getColor(), Type.QUEEN, piece.getBoard(), piece.getSquare());

                promoted.setImageView(piece.getImageView());
                promoted.getImageView().setImage(new Image("/images/Custom" + Integer.toString(style) + "/" + c + "_queen" + v +".png"));
                piece.getSquare().setPiece(promoted);

                ((Node)(event.getSource())).getScene().getWindow().hide();

                if (!piece.isKingTargeted(piece.getColor() * -1) && piece.noMoves(piece.getColor() * -1)) piece.endScreen(piece.getColor(), true);
                if (piece.isInCheckMate(piece.getColor() * -1)) piece.endScreen(piece.getColor(), false);
           }
        });

        knight.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Knight promoted = new Knight(piece.getColor(), Type.KNIGHT, piece.getBoard(), piece.getSquare());

                promoted.setImageView(piece.getImageView());
                promoted.getImageView().setImage(new Image("/images/Custom" + Integer.toString(style) + "/" + c + "_knight" + v +".png"));
                piece.getSquare().setPiece(promoted);

                ((Node)(event.getSource())).getScene().getWindow().hide();

                if (!piece.isKingTargeted(piece.getColor() * -1) && piece.noMoves(piece.getColor() * -1)) piece.endScreen(piece.getColor(), true);
                if (piece.isInCheckMate(piece.getColor() * -1)) piece.endScreen(piece.getColor(), false);
           }
        });

        rook.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Rook promoted = new Rook(piece.getColor(), Type.ROOK, piece.getBoard(), piece.getSquare());

                promoted.setImageView(piece.getImageView());
                promoted.getImageView().setImage(new Image("/images/Custom" + Integer.toString(style) + "/" + c + "_rook" + v +".png"));
                piece.getSquare().setPiece(promoted);

                ((Node)(event.getSource())).getScene().getWindow().hide();

                if (!piece.isKingTargeted(piece.getColor() * -1) && piece.noMoves(piece.getColor() * -1)) piece.endScreen(piece.getColor(), true);
                if (piece.isInCheckMate(piece.getColor() * -1)) piece.endScreen(piece.getColor(), false);
           }
        });

        bishop.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Bishop promoted = new Bishop(piece.getColor(), Type.BISHOP, piece.getBoard(), piece.getSquare());

                promoted.setImageView(piece.getImageView());
                promoted.getImageView().setImage(new Image("/images/Custom" + Integer.toString(style) + "/" + c + "_bishop" + v +".png"));
                piece.getSquare().setPiece(promoted);

                ((Node)(event.getSource())).getScene().getWindow().hide();

                if (!piece.isKingTargeted(piece.getColor() * -1) && piece.noMoves(piece.getColor() * -1)) piece.endScreen(piece.getColor(), true);
                if (piece.isInCheckMate(piece.getColor() * -1)) piece.endScreen(piece.getColor(), false);
           }
        });

    }

    public PromotionController(Piece piece, int color, int style) {
        this.piece = piece;
        System.out.println(piece);

        this.c = color == 1 ? "white" : "black";
        this.style = style;
    }
}