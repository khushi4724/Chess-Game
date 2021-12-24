package Chess;

import java.util.Collection;
import java.util.TreeSet;

public class Knight implements Piece {
    private int xCoord;
    private int yCoord;
    private boolean isCaptured;
    private boolean isWhite;

    public Knight(int xCoord, int yCoord, boolean white) {
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

    public boolean isWhite() {
        return isWhite;
    }

    public boolean checkOutOfBounds(int x, int y) {
        return ((x > -1 && x < 8) && (y > -1 && y < 8));
    }

    public Collection<Move> getMoves(int x, int y) {
        Collection<Move> moves = new TreeSet<>();
        for (int newX : new int[]{-2, -1, 1, 2}) {
            for (int newY : new int[]{-2, -1, 1, 2}) {
                if (Math.abs(newX / newY) == 2 || Math.abs(newY / newX) == 2) {
                    int finalX = x + newX;
                    int finalY = y + newY;
                    if (checkOutOfBounds(finalX, finalY)) {
                        moves.add(new Move(finalX, finalY));
                    }
                }
            }
        }
        return moves;
    }

    public String toString() {
        return " N ";
    }

    public void setX(int x) {
        xCoord = x;
    }

    public void setY(int y) {
        yCoord = y;
    }

    @Override
    public Piece constructPiece() {
        return new Knight(xCoord, yCoord, isWhite);
    }

    public String getType() {
        return "Knight";
    }
}