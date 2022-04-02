package Chess.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

import Chess.Board.BoardState;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;

public class WinController  implements Initializable {
    private String winner;
    private ArrayList<String> expressions;
    private boolean isDraw;
    private Animation ani = new Animation();
    private BoardState board;

    @FXML
    Text textField, faces;

    @FXML
    Button newGame;

    public WinController(int winner, boolean isDraw, BoardState board) {
        this.winner = winner == 1 ? "WHITE" : "BLACK";
        this.isDraw = isDraw;

        expressions = new ArrayList<String>();
        expressions.addAll(Arrays.asList(
            "ヾ(•ω•`)o", "(｡･∀･)ﾉﾞ","（＾∀＾●）ﾉｼ", "ヾ(^▽^*)))", "(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧","✪ ω ✪",
            "\\(@^0^@)/", "ヾ(￣▽￣)", "♪(´▽｀)", "(o゜▽゜)o☆", "o(≧∀≦)o", "~\\(≧▽≦)/~", "(⌐■_■)ヾ", "(⌐■_■)ノ♪"
            ));

        this.board = board;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textField.setText(winner + " WON!!!");
        if (isDraw) textField.setText("IT'S A DRAW!!!");
        textField.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 40));
        ani.start();

        newGame.setOnAction(event -> newGame(event));
    }

    private void newGame(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        ((Stage) board.getPieces().get(0).getImageView().getScene().getWindow()).close();
        ChessApp game = new ChessApp();
        try {
            game.start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Animation extends AnimationTimer {
        private int sec;
        private String expression;
        private Random rd = new Random();

        @Override
        public void handle(long a) {
            sec++;
            if (sec > 75) {
                sec = 0;
                expression = expressions.get(rd.nextInt(expressions.size()));
                while (expression.equals(faces.getText())) {
                    expression = expressions.get(rd.nextInt(expressions.size()));
                }
                faces.setText(expression);
                faces.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
            }
        }
    }
}
