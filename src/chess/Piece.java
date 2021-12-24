package Chess;

import java.util.Collection;

public interface Piece {

    int getXCoord();

    int getYCoord();

    boolean isCaptured();

    void setCapture(boolean captured);

    boolean isWhite();

    String getType();

    Collection<Move> getMoves(int x, int y);

    void setX(int newX);

    void setY(int newY);

    Piece constructPiece();
}