package Chess.Board;

public class ChessBoard {
    //dimension of board
    private int y, x;
    private Square[][] boardDimension;

    public ChessBoard(int y, int x) {
        int color = 1;

        this.y = y;
        this.x = x;

        this.boardDimension = new Square[this.y][this.x];

        for (int row = 0; row < this.y; row++) {
            for (int col = 0; col < this.x; col++) {
                this.boardDimension[row][col] = new Square(color, row, col );
                color = color * -1;
            }
            color = color * -1;
        }
    }

    public int[] getLength() {
        int[] length = {this.boardDimension.length, this.boardDimension[0].length};
        return length;
    }

    public Square getSquare(int y, int x) {
        try {
            return this.boardDimension[y][x];
        } catch (Exception e) {
            return null;
        }
    }
}