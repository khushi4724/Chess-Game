package Chess;

import java.util.Collection;
import java.util.TreeSet;

public class Pawn implements Piece {
    private int xCoord;
    private int yCoord;
    private boolean isCaptured;
    private boolean isWhite;
    private boolean captureByEnPassant;

    public Pawn(int xCoord, int yCoord, boolean white) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        isCaptured = false;
        isWhite = white;
        captureByEnPassant = false;
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

    public String toString() {
        return " P ";
    }

    public void setX(int x) {
        xCoord = x;
    }

    public void setY(int y) {
        yCoord = y;
    }

    public void setCaptureByEnPassant(boolean enPassant) {
        captureByEnPassant = enPassant;
    }

    public boolean isCaptureByEnPassant() {
        if (isWhite) {
            return xCoord == 3;
        } else {
            return xCoord == 4;
        }
    }

    @Override
    public Piece constructPiece() {
        return new Pawn(xCoord, yCoord, isWhite);
    }

    public String getType() {
        return "Pawn";
    }

    public Collection<Move> getMoves(int x, int y) {
        Collection<Move> moves = new TreeSet<>();
        if (x == 1) {
            if (isWhite) {
                // pawn can move two places at start of game
                moves.add(new Move(x + 1, y));
                moves.add(new Move(x + 2, y));
            } else {
                moves.add(new Move(x - 1, y));
            }
        } else if (x == 6) {
            if (isWhite) {
                moves.add(new Move(x + 1, y));
            } else {
                moves.add(new Move(x - 1, y));
                moves.add(new Move(x - 2, y));
            }
        } else {
            if (isWhite) {
                int newX = x + 1;
                if (newX <= 6) {
                    moves.add(new Move(newX, y));
                    // have to handle promotion if new position is
                }
            } else {
                int newX = x - 1;
                if (newX >= 1) {
                    moves.add(new Move(newX, y));
                }
            }
        }
        return moves;
    }

}