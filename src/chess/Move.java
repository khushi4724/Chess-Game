package Chess;

public class Move implements Comparable {
    private int x;
    private int y;
    private boolean capture;
    private boolean castle;
    private boolean enPassant;

    public Move(int xIn, int yIn, boolean capture) {
        x = xIn;
        y = yIn;
        this.capture = capture;
    }

    public Move(int xIn, int yIn, boolean capture, boolean castle) {
        x = xIn;
        y = yIn;
        this.capture = capture;
        this.castle = castle;
    }

    public Move(int xIn, int yIn, boolean capture, boolean castle, boolean enPassant) {
        x = xIn;
        y = yIn;
        this.capture = capture;
        this.castle = castle;
        this.enPassant = enPassant;
    }

    public Move(int xIn, int yIn) {
        x = xIn;
        y = yIn;
        this.capture = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCapture(boolean capture) {
        this.capture = capture;
    }

    public boolean getCastle() {
        return castle;
    }

    public boolean getEnPassant() {
        return enPassant;
    }

    public boolean getCapture() {
        return capture;
    }

    @Override
    public int compareTo(Object o) {
        Move m = (Move) o;
        if (x < m.getX()) {
            return 1;
        } else if (x > m.getX()) {
            return -1;
        } else {
            if (y < m.getY()) {
                return 1;
            } else if (y > m.getY()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
