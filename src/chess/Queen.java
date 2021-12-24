package Chess;

import java.util.Collection;
import java.util.TreeSet;

public class Queen implements Piece {
    private int xCoord;
    private int yCoord;
    private boolean isCaptured;
    private boolean isWhite;

    public Queen(int xCoord, int yCoord, boolean white) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        isCaptured = false;
        isWhite = white;
    }

    public int getXCoord() {
        return xCoord;
    }

    public int getYCoord() {
        return yCoord;
    }

    public boolean isCaptured() {
        return isCaptured;
    }

    public void setCapture(boolean captured) {
        isCaptured = captured;
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

    public boolean checkOutOfBounds(int x, int y) {
        return ((x > -1 && x < 8) && (y > -1 && y < 8));
    }

    public Collection<Move> getMoves(int x, int y) {
        Collection<Move> moves = new TreeSet<>();
        for (int i = -7; i < 8; i++) {
            int newX = x + i;
            int newY = y + i;
            int newX2 = x - i;
            // check top left bottom right diagonal
            if (newX != x && newY != y) {
                if (checkOutOfBounds(newX, newY)) {
                    moves.add(new Move(newX, newY));
                }
            }
            // check bottom left top right diagonal
            if (newX2 != x && newY != y) {
                if (checkOutOfBounds(newX2, newY)) {
                    moves.add(new Move(newX2, newY));
                }
            }
            if (i != x) {
                if (checkOutOfBounds(i, y)) {
                    moves.add(new Move(i, y));
                }
            }
            if (i != y) {
                if (checkOutOfBounds(x, i)) {
                    moves.add(new Move(x, i));
                }
            }
        }
        return moves;
    }

    public String toString() {
        return " Q ";
    }

    public void setX(int x) {
        xCoord = x;
    }

    public void setY(int y) {
        yCoord = y;
    }

    @Override
    public Piece constructPiece() {
        return new Queen(xCoord, yCoord, isWhite);
    }

    public String getType() {
        return "Queen";
    }
}