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
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChessController {
    private BoardState game;
    public static DataFormat dataFormat =  new DataFormat("myCell");
    private int style = 0, numb;
    private Random rd = new Random();
    Animation ani = new Animation();
    private Saver saver;
    
    @FXML
    private ImageView r1c1, r1c2, r1c3, r1c4, r1c5, r1c6, r1c7, r1c8, r2c1, r2c2, r2c3, r2c4, r2c5, r2c6, r2c7, r2c8, 
    r8c1, r8c2, r8c3, r8c4, r8c5, r8c6, r8c7, r8c8, r7c1, r7c2, r7c3, r7c4, r7c5, r7c6, r7c7, r7c8;

    @FXML
    private ImageView style0, style1;

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
            String c = (p.getColor() == 1) ? "white" : "black";
            if (p.getType() == Type.PAWN) {
                if (this.style != 0) {
                    numb = rd.nextInt(2) + 1;
                    if (numb == 1) p.getImageView().setImage(new Image("/images/Custom" + Integer.toString(style) + "/" + c + "_" + p.getType().toString().toLowerCase() + "1.png"));
                    else p.getImageView().setImage(new Image("/images/Custom" + Integer.toString(style) + "/" + c + "_" + p.getType().toString().toLowerCase() + "2.png"));
                } else {
                    p.getImageView().setImage(new Image("/images/Custom" + Integer.toString(style) + "/" + ((p.getColor() == 1) ? "white" : "black") + "_pawn.png"));
                }
            }
        }
    }

    @FXML
    void newGame() {
        try {
            NewGameController newGame = new NewGameController(game);
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("NewGame.fxml"));
            loader.setController(newGame);
            Parent root = loader.load();

            Stage secondaryStage = new Stage();
            secondaryStage.setScene(new Scene(root));
            // Specifies the modality for new window.
            secondaryStage.initModality(Modality.WINDOW_MODAL);

            secondaryStage.initOwner(game.getPieces().
            get(0).getImageView().getScene().getWindow());

            secondaryStage.setTitle("Promote");
            secondaryStage.getIcons().add(new Image("/images/icon.png"));
            secondaryStage.setResizable(false);

            secondaryStage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    void handleSave() throws IOException{
        saver.save("test", false);
    }

    @FXML
    void handleLoad() throws FileNotFoundException{
        for (Piece p : game.getPieces()) {p.getImageView().setVisible(false);}
        saver.load("test");
        connectPiecesLoad();
        adjustImagePosition();
        adjustImages();
    }

    @FXML
    void turnBack() throws FileNotFoundException{
        for (Piece p : game.getPieces()) {p.getImageView().setVisible(false);}
        saver.load("DO_NOT_TOUCH");
        connectPiecesLoad();
        adjustImagePosition();
        adjustImages();
    }

    @FXML
    void changeStyle0() {
        this.style = 0;
        game.setStyle(0);
        changeStyle();
    }
    @FXML
    void changeStyle1() {
        this.style = 1;
        game.setStyle(1);
        changeStyle();
    }

    void changeStyle() {
        if (this.style != 0) {
            ani.start();
            numb = rd.nextInt(2) + 1;
        }
        else ani.stop();

        for (Piece p : game.getPieces()) {
            String c = (p.getColor() == 1) ? "white" : "black";
            if (p.getImageView() != null) {
                if (this.style != 0) {
                    if (numb == 1) p.getImageView().setImage(new Image("/images/Custom" + Integer.toString(style) + "/" + c + "_" + p.getType().toString().toLowerCase() + "1.png"));
                    else p.getImageView().setImage(new Image("/images/Custom" + Integer.toString(style) + "/" + c + "_" + p.getType().toString().toLowerCase() + "2.png"));
                } else {
                    String type = p.getType().toString().toLowerCase();
                    String image = "/images/Custom0/" + c + "_" + type + ".png";
                    p.getImageView().setImage(new Image(image));
                }
            }
        }
    }

    private class Animation extends AnimationTimer {
        private int sec, numb, count = 0;
        private Random rd = new Random();
        private String lst[] = {"pawn", "bishop", "knight", "rook", "queen", "king"};

        @Override
        public void handle(long a) {
            sec++;
            if (sec > 100) {
                sec = 0;
                doHandle();
                shuffle();
            }
        }

        public void doHandle() {
            if (style != 0) {
                for (Piece p : game.getPieces()) {
                    String c = (p.getColor() == 1) ? "white" : "black";
                    numb = rd.nextInt(2) + 1;
                    if (numb == 1) p.getImageView().setImage(new Image("/images/Custom" + Integer.toString(style) + "/" + c + "_" + p.getType().toString().toLowerCase() + "1.png"));
                    else p.getImageView().setImage(new Image("/images/Custom" + Integer.toString(style) + "/" + c + "_" + p.getType().toString().toLowerCase() + "2.png"));
                }
            }
        }

        public void shuffle() {
            count++;
            if (count == 12) count = 0;
            String c = count % 2 == 0 ? "white" : "black";
            style0.setImage(new Image("/images/Custom0/" + c +"_" + lst[count / 2] + ".png"));
            style1.setImage(new Image("/images/Custom1/" + c + "_" + lst[count / 2] + "2.png"));
        }
    }
}