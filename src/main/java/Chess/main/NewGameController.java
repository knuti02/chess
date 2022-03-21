package Chess.main;

import Chess.Board.BoardState;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class NewGameController {
    BoardState board;

    @FXML
    private void newGame(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        board.getPieces().get(0).getImageView().getScene().getWindow().hide();
        ChessApp game = new ChessApp();
        try {
            game.start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void stay(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public NewGameController(BoardState board) {
        this.board = board;
    }
}
