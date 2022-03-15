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

    @FXML
    ImageView queen = new ImageView(), knight = new ImageView(), rook = new ImageView(), bishop = new ImageView();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(piece);
        queen.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Queen promoted = new Queen(piece.getColor(), Type.QUEEN, piece.getBoard(), piece.getSquare());

                String c = (piece.getColor() == 1) ? "white" : "black";

                promoted.setImageView(piece.getImageView());
                promoted.getImageView().setImage(new Image("/images/" + c + "_queen.png"));
                piece.getSquare().setPiece(promoted);

                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
        });

        knight.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Knight promoted = new Knight(piece.getColor(), Type.KNIGHT, piece.getBoard(), piece.getSquare());

                String c = (piece.getColor() == 1) ? "white" : "black";

                promoted.setImageView(piece.getImageView());
                promoted.getImageView().setImage(new Image("/images/" + c + "_knight.png"));
                piece.getSquare().setPiece(promoted);

                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
        });

        rook.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Rook promoted = new Rook(piece.getColor(), Type.ROOK, piece.getBoard(), piece.getSquare());

                String c = (piece.getColor() == 1) ? "white" : "black";

                promoted.setImageView(piece.getImageView());
                promoted.getImageView().setImage(new Image("/images/" + c + "_rook.png"));
                piece.getSquare().setPiece(promoted);

                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
        });

        bishop.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Bishop promoted = new Bishop(piece.getColor(), Type.BISHOP, piece.getBoard(), piece.getSquare());

                String c = (piece.getColor() == 1) ? "white" : "black";

                promoted.setImageView(piece.getImageView());
                promoted.getImageView().setImage(new Image("/images/" + c + "_bishop.png"));
                piece.getSquare().setPiece(promoted);

                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
        });

    }

    public PromotionController(Piece piece) {
        this.piece = piece;
        System.out.println(piece);
    }
}