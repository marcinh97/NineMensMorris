package sample.graphics.game;

public class BoardPlace {
    private char row;
    private char column;

    private boolean isOccupied;

    public BoardPlace(char column, char row) {
        this.column = column;
        this.row = row;
    }

    public char getRow() {
        return row;
    }

    public char getColumn() {
        return column;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    @Override
    public String toString() {
        return "BoardPlace{" +
                "row=" + row +
                ", column=" + column +
                ", isOccupied=" + isOccupied +
                '}';
    }
}
