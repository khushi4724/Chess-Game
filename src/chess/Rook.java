package Chess;

import java.util.Collection;
import java.util.TreeSet;

public class Rook implements Piece {
    private int xCoord;
    private int yCoord;
    private boolean isCaptured;
    private boolean isWhite;
    private boolean eligibleCastle;

    public Rook(int xCoord, int yCoord, boolean white) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        isCaptured = false;
        isWhite = white;
        eligibleCastle = true;
    }

    public void updateCastle() {
        if (eligibleCastle) {
            eligibleCastle = ((xCoord == 0) && (xCoord == 0)) ||
                    ((xCoord == 0) && (yCoord == 7)) ||
                    ((xCoord == 7) && (yCoord == 0)) ||
                    ((xCoord == 7) && (yCoord == 7));
        }
    }

    public boolean eligibleForCastle() {
        return eligibleCastle;
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

    public Collection<Move> getMoves(int x, int y) {
        Collection<Move> moves = new TreeSet<>();
        for (int i = 0; i < 8; i++) {
            if (i != x) {
                moves.add(new Move(i, y));
            }
        }
        for (int i = 0; i < 8; i++) {
            if (i != y) {
                moves.add(new Move(x, i));
            }
        }
        return moves;
    }

    public String toString() {
        return " R ";
    }

    public void setX(int x) {
        xCoord = x;
    }

    public void setY(int y) {
        yCoord = y;
    }

    @Override
    public Piece constructPiece() {
        return new Rook(xCoord, yCoord, isWhite);
    }

    public String getType() {
        return "Rook";
    }
}
