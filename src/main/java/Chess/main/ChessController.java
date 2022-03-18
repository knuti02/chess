package Chess.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import Chess.Board.*;
import Chess.Pieces.*;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class ChessController {
    private BoardState game;
    public static DataFormat dataFormat =  new DataFormat("myCell");
    private int style = 1, numb;
    private Random rd = new Random();
    Animation ani = new Animation();
    private Saver saver;
    
    @FXML
    private ImageView r1c1, r1c2, r1c3, r1c4, r1c5, r1c6, r1c7, r1c8, r2c1, r2c2, r2c3, r2c4, r2c5, r2c6, r2c7, r2c8, 
    r8c1, r8c2, r8c3, r8c4, r8c5, r8c6, r8c7, r8c8, r7c1, r7c2, r7c3, r7c4, r7c5, r7c6, r7c7, r7c8;

    ArrayList<ImageView> blackPawns = new ArrayList<ImageView>();
    ArrayList<ImageView> whitePawns = new ArrayList<ImageView>();

    ArrayList<ImageView> blackRooks = new ArrayList<ImageView>();
    ArrayList<ImageView> whiteRooks = new ArrayList<ImageView>();

    ArrayList<ImageView> blackKnights = new ArrayList<ImageView>();
    ArrayList<ImageView> whiteKnights = new ArrayList<ImageView>();

    ArrayList<ImageView> blackBishops = new ArrayList<ImageView>();
    ArrayList<ImageView> whiteBishops = new ArrayList<ImageView>();

    ArrayList<ImageView> blackQueens = new ArrayList<ImageView>();
    ArrayList<ImageView> whiteQueens = new ArrayList<ImageView>();

    ArrayList<ImageView> blackKing = new ArrayList<ImageView>();
    ArrayList<ImageView> whiteKing = new ArrayList<ImageView>();

    ArrayList<ImageView> images = new ArrayList<ImageView>();

    @FXML
    public void initialize() {
        initImageView();

        game = new BoardState();
        connectPieces();
        saver = new Saver(this.game);
        saver.reset("DO_NOT_TOUCH");

        System.out.println(game);
    }

    private void initImageView() {
        blackPawns.addAll(Arrays.asList(r2c1, r2c2, r2c3, r2c4, r2c5, r2c6, r2c7, r2c8));
        whitePawns.addAll(Arrays.asList(r7c1, r7c2, r7c3, r7c4, r7c5, r7c6, r7c7, r7c8));

        blackRooks.addAll(Arrays.asList(r1c1, r1c8));
        whiteRooks.addAll(Arrays.asList(r8c1, r8c8));

        blackKnights.addAll(Arrays.asList(r1c2, r1c7));
        whiteKnights.addAll(Arrays.asList(r8c2, r8c7));

        blackBishops.addAll(Arrays.asList(r1c3, r1c6));
        whiteBishops.addAll(Arrays.asList(r8c3, r8c6));

        blackQueens.addAll(Arrays.asList(r1c4));
        whiteQueens.addAll(Arrays.asList(r8c4));

        blackKing.addAll(Arrays.asList(r1c5));
        whiteKing.addAll(Arrays.asList(r8c5));

        images.addAll(Arrays.asList(r2c1, r2c2, r2c3, r2c4, r2c5, r2c6, r2c7, r2c8,
        r7c1, r7c2, r7c3, r7c4, r7c5, r7c6, r7c7, r7c8, r1c1, r1c8, r8c1, r8c8,
        r1c2, r1c7, r8c2, r8c7, r1c3, r1c6, r8c3, r8c6, r1c4, r8c4, r1c5, r8c5
        ));
    }

    private void connectToImageView(Piece p) {
        if (p.getType() == Type.PAWN) {
            for (ImageView i : p.getColor() == 1 ? whitePawns : blackPawns) {
                if (GridPane.getRowIndex(i) - 1 == p.getSquare().getY() && 
                GridPane.getColumnIndex(i) - 1 == p.getSquare().getX()) {
                    p.setImageView(i);
                }
            }
        }

        else if (p.getType() == Type.ROOK) {
            for (ImageView i : p.getColor() == 1 ? whiteRooks : blackRooks) {
                if (GridPane.getRowIndex(i) - 1 == p.getSquare().getY() && 
                GridPane.getColumnIndex(i) - 1 == p.getSquare().getX()) {
                    p.setImageView(i);
                }
            }
        }

        else if (p.getType() == Type.KNIGHT) {
            for (ImageView i : p.getColor() == 1 ? whiteKnights : blackKnights) {
                if (GridPane.getRowIndex(i) - 1 == p.getSquare().getY() && 
                GridPane.getColumnIndex(i) - 1 == p.getSquare().getX()) {
                    p.setImageView(i);
                }
            }
        }

        else if (p.getType() == Type.BISHOP) {
            for (ImageView i : p.getColor() == 1 ? whiteBishops : blackBishops) {
                if (GridPane.getRowIndex(i) - 1 == p.getSquare().getY() && 
                GridPane.getColumnIndex(i) - 1 == p.getSquare().getX()) {
                    p.setImageView(i);
                }
            }
        }

        else if (p.getType() == Type.QUEEN) {
            for (ImageView i : p.getColor() == 1 ? whiteQueens : blackQueens) {
                if (GridPane.getRowIndex(i) - 1 == p.getSquare().getY() && 
                GridPane.getColumnIndex(i) - 1 == p.getSquare().getX()) {
                    p.setImageView(i);
                }
            }
        }

        if (p.getType() == Type.KING) {
            for (ImageView i : p.getColor() == 1 ? whiteKing : blackKing) {
                if (GridPane.getRowIndex(i) - 1 == p.getSquare().getY() && 
                GridPane.getColumnIndex(i) - 1 == p.getSquare().getX()) {
                    p.setImageView(i);
                }
            }
        }  
    }

    private void connectPieces() {
        for (Piece p : game.getPieces()) {
            connectToImageView(p);
        }
    }

    private void connectPiecesLoad() {
        for (Piece p : game.getPieces()) {
            for (ImageView i : images) {
                if (p.getId().equals(i.getId())) {
                    p.setImageView(i);
                    p.getImageView().setVisible(true);
                }
            }
        }
    }

    private void adjustImagePosition() {
        for (Piece p : game.getPieces()) {
            //System.out.println(p.getImageView());
            GridPane.setRowIndex(p.getImageView(), p.getSquare().getY() + 1);
            GridPane.setColumnIndex(p.getImageView(), p.getSquare().getX() + 1);
        }
    }

    private void adjustImages() { //adjust back to pawns if you load to a file before you promoted
        for (Piece p : game.getPieces()) {
            if (p.getType() == Type.PAWN) {
                if (this.style == -1) {
                    numb = rd.nextInt(2) + 1;
                    if (numb == 1) p.getImageView().setImage(new Image("/images/save.png"));
                    else p.getImageView().setImage(new Image("/images/load.png"));
                } else {
                    p.getImageView().setImage(new Image("/images/" + ((p.getColor() == 1) ? "white" : "black") + "_pawn.png"));
                }
            }
        }
    }

    @FXML
    void onMousePressed(MouseEvent event){}

    @FXML
    void onMouseDragged(MouseEvent event){}

    @FXML
    void onMouseReleased(MouseEvent event){}

    @FXML
    void handleSave() throws IOException{
        saver.save("test", false);
    }

    @FXML
    void handleLoad() throws FileNotFoundException{
        saver.load("test");
        connectPiecesLoad();
        adjustImagePosition();
        adjustImages();
    }

    @FXML
    void turnBack() throws FileNotFoundException{
        saver.load("DO_NOT_TOUCH");
        connectPiecesLoad();
        adjustImagePosition();
        adjustImages();
    }

    @FXML 
    void changeStyle() {
        this.style *= -1;

        if (this.style == -1) ani.start();
        else ani.stop();

        for (Piece p : game.getPieces()) {
            if (p.getImageView() != null) {
                if (this.style == -1) {
                    numb = rd.nextInt(2) + 1;
                    if (numb == 1) p.getImageView().setImage(new Image("/images/save.png"));
                    else p.getImageView().setImage(new Image("/images/load.png"));
                } else {
                    String c = (p.getColor() == 1) ? "white" : "black";
                    String type = p.getType().toString().toLowerCase();

                    String image = "/images/" + c + "_" + type + ".png";
                    p.getImageView().setImage(new Image(image));
                }
            }
        }
    }

    private class Animation extends AnimationTimer {
        private int sec, numb;
        private Random rd = new Random();

        @Override
        public void handle(long a) {
            sec++;
            if (sec > 100) {
                sec = 0;
                doHandle();
            }
        }

        public void doHandle() {
            if (style == -1) {
                for (Piece p : game.getPieces()) {
                    numb = rd.nextInt(2) + 1;
                    if (numb == 1) p.getImageView().setImage(new Image("/images/save.png"));
                    else p.getImageView().setImage(new Image("/images/load.png"));
                }
            }
        }
    }
}