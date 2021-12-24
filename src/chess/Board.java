package Chess;

import java.util.*;

public class Board {

    private Piece[][] board;
    boolean[][] movesArray;
    List<Piece> whiteCaptured;
    List<Piece> blackCaptured;
    boolean whiteTurn;
    boolean check;
    boolean checkmate;
    boolean stalemate;
    Map<Piece, Collection<Move>> possibleMoves;

    public Board() {
        board = new Piece[8][8];
        movesArray = new boolean[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                movesArray[i][j] = false;
            }
        }
        whiteCaptured = new ArrayList<>();
        blackCaptured = new ArrayList<>();
        possibleMoves = new HashMap<>();
        whiteTurn = true;
        check = false;
        checkmate = false;
        stalemate = false;
    }

    public Board(boolean withPieces) {
        if (withPieces) {
            board = new Piece[8][8];
            whiteCaptured = new ArrayList<>();
            blackCaptured = new ArrayList<>();
            Map<Piece, Collection<Move>> currentMoves = new HashMap<>();
            whiteTurn = true;
            check = false;
            checkmate = false;

            // Initializing white pieces
            // the coordinates are indexed according to a 2D matrix
            board[0][3] = new King(0, 3, true);
            board[0][4] = new Queen(0, 4, true);
            board[0][0] = new Rook(0, 0, true);
            board[0][7] = new Rook(0, 7, true);
            board[0][1] = new Knight(0, 1, true);
            board[0][6] = new Knight(0, 6, true);
            board[0][2] = new Bishop(0, 2, true);
            board[0][5] = new Bishop(0, 5, true);
            board[1][0] = new Pawn(1, 0, true);
            board[1][1] = new Pawn(1, 1, true);
            board[1][2] = new Pawn(1, 2, true);
            board[1][3] = new Pawn(1, 3, true);
            board[1][4] = new Pawn(1, 4, true);
            board[1][5] = new Pawn(1, 5, true);
            board[1][6] = new Pawn(1, 6, true);
            board[1][7] = new Pawn(1, 7, true);

            // Initializing black pieces
            // coordinates are indexed according to a 2D matrix
            board[7][3] = new King(7, 3, false);
            board[7][4] = new Queen(7, 4, false);
            board[7][0] = new Rook(7, 0, false);
            board[7][7] = new Rook(7, 7, false);
            board[7][1] = new Knight(7, 1, false);
            board[7][6] = new Knight(7, 6, false);
            board[7][2] = new Bishop(7, 2, false);
            board[7][5] = new Bishop(7, 5, false);
            board[6][0] = new Pawn(6, 0, false);
            board[6][1] = new Pawn(6, 1, false);
            board[6][2] = new Pawn(6, 2, false);
            board[6][3] = new Pawn(6, 3, false);
            board[6][4] = new Pawn(6, 4, false);
            board[6][5] = new Pawn(6, 5, false);
            board[6][6] = new Pawn(6, 6, false);
            board[6][7] = new Pawn(6, 7, false);

        }
    }

    // when the reset button is pressed, this method is called on the board object
    public void reset() {
        board = new Piece[8][8];
        whiteCaptured = new ArrayList<>();
        blackCaptured = new ArrayList<>();
        possibleMoves = new HashMap<>();
        whiteTurn = true;
        check = false;
        checkmate = false;
        stalemate = false;
        movesArray = new boolean[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                movesArray[i][j] = false;
            }
        }

        // Initializing white pieces
        board[0][0] = new Rook(0, 0, true);
        board[0][7] = new Rook(0, 7, true);
        board[0][1] = new Knight(0, 1, true);
        board[0][6] = new Knight(0, 6, true);
        board[0][2] = new Bishop(0, 2, true);
        board[0][5] = new Bishop(0, 5, true);
        board[0][4] = new Queen(0, 4, true);
        board[0][3] = new King(0, 3, true);
        board[1][0] = new Pawn(1, 0, true);
        board[1][1] = new Pawn(1, 1, true);
        board[1][2] = new Pawn(1, 2, true);
        board[1][3] = new Pawn(1, 3, true);
        board[1][4] = new Pawn(1, 4, true);
        board[1][5] = new Pawn(1, 5, true);
        board[1][6] = new Pawn(1, 6, true);
        board[1][7] = new Pawn(1, 7, true);

        // Initializing black pieces
        board[7][0] = new Rook(7, 0, false);
        board[7][7] = new Rook(7, 7, false);
        board[7][1] = new Knight(7, 1, false);
        board[7][6] = new Knight(7, 6, false);
        board[7][2] = new Bishop(7, 2, false);
        board[7][5] = new Bishop(7, 5, false);
        board[7][4] = new Queen(7, 4, false);
        board[7][3] = new King(7, 3, false);
        board[6][0] = new Pawn(6, 0, false);
        board[6][1] = new Pawn(6, 1, false);
        board[6][2] = new Pawn(6, 2, false);
        board[6][3] = new Pawn(6, 3, false);
        board[6][4] = new Pawn(6, 4, false);
        board[6][5] = new Pawn(6, 5, false);
        board[6][6] = new Pawn(6, 6, false);
        board[6][7] = new Pawn(6, 7, false);
    }

    public boolean getTurn() {
        return whiteTurn;
    }

    public boolean isCheck() {
        return check;
    }

    public boolean getCheckMate() {
        return checkmate;
    }

    public boolean getStalemate() {
        return stalemate;
    }

    public List<Piece> getWhiteCaptured() {
        return whiteCaptured;
    }

    public List<Piece> getBlackCaptured() {
        return blackCaptured;
    }

    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    public boolean existsMove(int x, int y) {
        return (movesArray[x][y]);
    }

    // updating move methods/selecting pieces/moving pieces

    public void updateCurrentMoves() {
        possibleMoves.clear();
        Map<Piece, Collection<Move>> opposingPossibleMoves = new HashMap<Piece, Collection<Move>>();
        for (Piece[] pieces : board) {
            for (Piece p : pieces) {
                if (p != null) {
                    Collection<Move> moves = p.getMoves(p.getXCoord(), p.getYCoord());
                    if (p.isWhite() == whiteTurn) {
                        moves = removeCollisions(p, moves, board);
                        possibleMoves.put(p, moves);
                    } else {
                        moves = removeCollisions(p, moves, board);
                        opposingPossibleMoves.put(p, moves);
                    }
                }
            }
        }
        updateWithPawnPromotion();
        Piece king = getKing(whiteTurn);
        check = isCheck(king, opposingPossibleMoves);
        possibleMoves = checkBlockCheck(king, opposingPossibleMoves, possibleMoves);
        removeCastleUnderCheck(king);
        checkmate = isCheckMate();
        stalemate = isStalemate();
    }

    public boolean selectPiece(int xPos, int yPos) {
        // select the piece to move and show possible moves
        resetMovesArray();
        Piece p = board[xPos][yPos];
        if (p == null) {
            return false;
        }

        if (p.isWhite() == whiteTurn) {
            Collection<Move> moves = possibleMoves.get(p);
            for (Move move : moves) {
                movesArray[move.getX()][move.getY()] = true;
            }
            movesArray[p.getXCoord()][p.getYCoord()] = true;
            return true;
        }
        return false;
    }

    public void unselectPiece(int xPos, int yPos) {
        resetMovesArray();
    }

    public void resetMovesArray() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                movesArray[i][j] = false;
            }
        }
    }

    public boolean isMoveValid(int oldX, int oldY, int newX, int newY) {
        Piece p = board[oldX][oldY];
        Collection<Move> moves = possibleMoves.get(p);
        return (moves.contains(new Move(newX, newY)));
    }

    public boolean isMoveCastle(int oldX, int oldY, int newX, int newY) {
        Piece p = board[oldX][oldY];
        if (p.getType().equals("King")) {
            Collection<Move> moves = possibleMoves.get(p);
            for (Move move : moves) {
                if (move.getX() == newX && move.getY() == newY) {
                    if (move.getCastle()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isMoveEnPassant(int oldX, int oldY, int newX, int newY) {
        Piece p = board[oldX][oldY];
        if (p.getType().equals("Pawn")) {
            Collection<Move> moves = possibleMoves.get(p);
            for (Move move : moves) {
                if (move.getX() == newX && move.getY() == newY) {
                    if (move.getEnPassant()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void movePiece(int oldX, int oldY, int newX, int newY, boolean addToCaptured) {
        if (isMoveValid(oldX, oldY, newX, newY)) {
            if (!isMoveCastle(oldX, oldY, newX, newY)) {
                if (!isMoveEnPassant(oldX, oldY, newX, newY)) {
                    Piece p = board[oldX][oldY];
                    board[oldX][oldY] = null;
                    Piece captured = board[newX][newY];
                    if (captured != null) {
                        if (addToCaptured) {
                            if (captured.isWhite()) {
                                whiteCaptured.add(captured);
                            } else {
                                blackCaptured.add(captured);
                                System.out.println("piece captured");
                            }
                        }
                    }
                    board[newX][newY] = p;
                    board[newX][newY].setX(newX);
                    board[newX][newY].setY(newY);
                    resetMovesArray();
                    whiteTurn = !whiteTurn;
                } else {
                    Piece p = board[oldX][oldY];
                    board[oldX][oldY] = null;
                    Piece captured = null;
                    if (p.isWhite()) {
                        captured = board[newX - 1][newY];
                        board[newX - 1][newY] = null;
                    } else {
                        captured = board[newX + 1][newY];
                        board[newX + 1][newY] = null;
                    }
                    if (captured != null) {
                        if (addToCaptured) {
                            if (captured.isWhite()) {
                                whiteCaptured.add(captured);
                            } else {
                                blackCaptured.add(captured);
                                System.out.println("piece captured");
                            }
                        }
                    }
                    board[newX][newY] = p;
                    board[newX][newY].setX(newX);
                    board[newX][newY].setY(newY);
                    resetMovesArray();
                    whiteTurn = !whiteTurn;
                }
            } else {
                Piece p = board[oldX][oldY];
                board[oldX][oldY] = null;
                if (newY == 1) {
                    Piece rook = board[oldX][0];
                    board[oldX][0] = null;
                    board[newX][2] = rook;
                    board[newX][2].setX(newX);
                    board[newX][2].setY(2);
                } else if (newY == 5) {
                    Piece rook = board[oldX][7];
                    board[oldX][7] = null;
                    board[newX][4] = rook;
                    board[newX][4].setX(newX);
                    board[newX][4].setY(4);
                }
                board[newX][newY] = p;
                board[newX][newY].setX(newX);
                board[newX][newY].setY(newY);
                resetMovesArray();
                whiteTurn = !whiteTurn;
            }
        }
    }

    // private helper methods to modify possible moves

    private Collection<Move> removeCollisions(Piece p, Collection<Move> moves,
                                              Piece[][] boardCopy) {
        switch (p.getType()) {
            case "Pawn":
                moves.removeIf(move -> boardCopy[move.getX()][move.getY()] != null);
                moves = checkPawnCapture(p, moves, boardCopy);
                moves = removeVertHorizCollision(moves, p, boardCopy);
                moves = enPassant(p, moves, boardCopy);
                break;
            case "Knight":
            case "King":
                Iterator<Move> iterator = moves.iterator();
                Collection<Move> movesToRemove = new TreeSet<>();
                while (iterator.hasNext()) {
                    Move move = iterator.next();
                    Piece px = boardCopy[move.getX()][move.getY()];
                    if (px != null) {
                        if (px.isWhite() != p.isWhite()) {
                            move.setCapture(true);
                        } else {
                            movesToRemove.add(move);
                        }
                    }
                }
                for (Move move : movesToRemove) {
                    moves.remove(move);
                }
                if (p.getType().equals("King")) {
                    ((King) p).updateCastle();
                }
                moves = addCastle(p, moves, boardCopy);
                break;
            case "Rook":
                moves = removeVertHorizCollision(moves, p, boardCopy);
                ((Rook) p).updateCastle();
                break;
            case "Queen":
                moves = removeVertHorizCollision(moves, p, boardCopy);
                moves = removeDiagCollision(moves, p, boardCopy);
                break;
            case "Bishop":
                moves = removeDiagCollision(moves, p, boardCopy);
                break;
            default:
                break;
        }
        return moves;
    }

    private Collection<Move> removeVertHorizCollision(Collection<Move> moves, Piece p,
                                                      Piece[][] boardCopy) {
        Iterator<Move> rookIterator = moves.iterator();
        Collection<Move> rookMovesToRemove = new TreeSet<>();
        int topBound = 0;
        int bottomBound = 7;
        int leftBound = 0;
        int rightBound = 7;
        while (rookIterator.hasNext()) {
            Move move = rookIterator.next();
            int xMove = move.getX();
            int yMove = move.getY();
            Piece px = boardCopy[xMove][yMove];
            if (px != null) {
                // adjust left right bounds if it's on the same row
                if (xMove == p.getXCoord()) {
                    // move is on the same row as the piece
                    if (px.isWhite() != p.isWhite()) {
                        move.setCapture(true);
                    } else {
                        rookMovesToRemove.add(move);
                    }

                    // if move is right of current position, want to adjust
                    if (yMove > p.getYCoord()) {
                        if (yMove < rightBound) {
                            rightBound = yMove;
                        }
                    } else {
                        if (yMove > leftBound) {
                            leftBound = yMove;
                        }
                    }
                }

                // adjust top bottom bounds if its on the same col
                if (yMove == p.getYCoord()) {
                    // move is on the same column as the piece
                    // move is on the same row as the piece
                    if (px.isWhite() != p.isWhite()) {
                        move.setCapture(true);
                    } else {
                        rookMovesToRemove.add(move);
                    }

                    // adjusting the left and right bounds of our moves
                    if (xMove > p.getXCoord()) {
                        if (xMove < bottomBound) {
                            bottomBound = xMove;
                        }
                    } else {
                        if (xMove > topBound) {
                            topBound = xMove;
                        }
                    }
                }
            }
        }

        for (Move move : moves) {
            if (!checkOutOfBounds(move.getX(), move.getY(),
                    rightBound, leftBound, topBound, bottomBound)) {
                if (move.getX() == p.getXCoord() || move.getY() == p.getYCoord()) {
                    rookMovesToRemove.add(move);
                }
            }
        }

        for (Move move : rookMovesToRemove) {
            moves.remove(move);
        }
        return moves;
    }

    private Collection<Move> removeDiagCollision(Collection<Move> moves, Piece p,
                                                 Piece[][] boardCopy) {
        Iterator<Move> bishopIterator = moves.iterator();
        Collection<Move> bishopMovesToRemove = new TreeSet<>();
        int topLeftBound = 7;
        int topRightBound = 7;
        int bottomLeftBound = 7;
        int bottomRightBound = 7;
        while (bishopIterator.hasNext()) {
            Move move = bishopIterator.next();
            int xMove = move.getX();
            int yMove = move.getY();
            Piece px = boardCopy[xMove][yMove];
            if (px != null) {
                if (px.isWhite() != p.isWhite()) {
                    move.setCapture(true);
                } else {
                    bishopMovesToRemove.add(move);
                }

                if (yMove > p.getYCoord()) {
                    // right half of the area we are searching
                    if (xMove < p.getXCoord()) {
                        int newInt = yMove - p.getYCoord();
                        if (newInt < topRightBound) {
                            topRightBound = newInt;
                        }
                    } else if (xMove > p.getXCoord()) {
                        int newInt = yMove - p.getYCoord();
                        if (newInt < bottomRightBound) {
                            bottomRightBound = newInt;
                        }
                    }
                } else if (yMove < p.getYCoord()) {
                    // left half of the area we are searching
                    if (xMove < p.getXCoord()) {
                        int newInt = p.getYCoord() - yMove;
                        if (newInt < topLeftBound) {
                            topLeftBound = newInt;
                        }
                    } else if (xMove > p.getXCoord()) {
                        int newInt = p.getYCoord() - yMove;
                        if (newInt < bottomLeftBound) {
                            bottomLeftBound = newInt;
                        }
                    }
                }
            }
        }

        for (Move move : moves) {
            if (!checkDiagBounds(move.getX(), move.getY(), p.getXCoord(), p.getYCoord(),
                    topLeftBound, topRightBound, bottomLeftBound, bottomRightBound)) {
                bishopMovesToRemove.add(move);
            }
        }

        for (Move move : bishopMovesToRemove) {
            moves.remove(move);
        }

        return moves;
    }

    private Collection<Move> checkPawnCapture(Piece p, Collection<Move> moves,
                                              Piece[][] boardCopy) {
        int newX = 0;
        int newY = p.getYCoord() + 1;
        int newY2 = p.getYCoord() - 1;
        if (p.isWhite()) {
            newX = p.getXCoord() + 1;
        } else {
            newX = p.getXCoord() - 1;
        }

        Collection<Move> movesToRemove = new TreeSet<Move>();

        if (moves.size() > 1) {
            for (Move move : moves) {
                if (p.isWhite()) {
                    if (move.getX() == p.getXCoord() + 2) {
                        if (boardCopy[move.getX() - 1][move.getY()] != null) {
                            movesToRemove.add(move);
                        }
                    }
                } else {
                    if (move.getX() == p.getXCoord() - 2) {
                        if (boardCopy[move.getX() + 1][move.getY()] != null) {
                            movesToRemove.add(move);
                        }
                    }
                }
            }
        }

        Piece px1 = null;
        Piece px2 = null;
        if (checkInBounds(newX, newY)) {
            px1 = boardCopy[newX][newY];
        }
        if (checkInBounds(newX, newY2)) {
            px2 = boardCopy[newX][newY2];
        }
        if (px1 != null && p.isWhite() != px1.isWhite()) {
            moves.add(new Move(newX, newY, true));
        }

        if (px2 != null && p.isWhite() != px2.isWhite()) {
            moves.add(new Move(newX, newY2, true));
        }

        for (Move move : movesToRemove) {
            System.out.println(move.getX() + " " + move.getY());
            moves.remove(move);
        }

        return moves;
    }

    // misc methods (checking for pawn promotion, castling, en passant)

    // pawn promotion

    private void updateWithPawnPromotion() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = board[i][j];
                if (p != null) {
                    if (p.getType().equals("Pawn")) {
                        if (p.isWhite()) {
                            if (p.getXCoord() == 7) {
                                board[i][j] = new Queen(i, j, true);
                            }
                        } else {
                            if (p.getXCoord() == 0) {
                                board[i][j] = new Queen(i, j, false);
                            }
                        }
                    }
                }
            }
        }
    }

    // method to enable en passent
    private Collection<Move> enPassant(Piece p, Collection<Move> moves, Piece[][] boardCopy) {
        if (p.getType().equals("Pawn")) {
            int rowToCheck = 0;
            if (p.isWhite()) {
                rowToCheck = 4;
            } else {
                rowToCheck = 3;
            }
            if (p.getXCoord() == rowToCheck) {
                if (p.getYCoord() + 1 < 8) {
                    Piece p1 = board[p.getXCoord()][p.getYCoord() + 1];
                    if (p1 != null) {
                        if (p1.getType().equals("Pawn")) {
                            if (p.isWhite() != p1.isWhite()) {
                                if (rowToCheck == 4) {
                                    moves.add(new Move(p.getXCoord() + 1, p.getYCoord() + 1,
                                            false, true, true));
                                } else if (rowToCheck == 3) {
                                    moves.add(new Move(p.getXCoord() - 1, p.getYCoord() + 1,
                                            false, true, true));
                                }
                            }
                        }
                    }
                }

                if (p.getYCoord() - 1 >= 0) {
                    Piece p2 = board[p.getXCoord()][p.getYCoord() - 1];
                    if (p2 != null) {
                        if (p2.getType().equals("Pawn")) {
                            if (p.isWhite() != p2.isWhite()) {
                                if (rowToCheck == 4) {
                                    moves.add(new Move(p.getXCoord() + 1, p.getYCoord() - 1,
                                            false, true, true));
                                } else if (rowToCheck == 3) {
                                    moves.add(new Move(p.getXCoord() - 1, p.getYCoord() - 1,
                                            false, true, true));
                                }
                            }
                        }
                    }
                }
            }
        }
        return moves;
    }

    // method to enable castling
    private Collection<Move> addCastle(Piece p, Collection<Move> moves, Piece[][] boardCopy) {
        if (p.getType().equals("King")) {
            int castle = checkCastle(p, boardCopy);
            if (castle == 1) {
                //  king side castle
                moves.add(new Move(p.getXCoord(), p.getYCoord() - 2, false, true));
            } else if (castle == 2) {
                // queen side castle
                moves.add(new Move(p.getXCoord(), p.getYCoord() + 2, false, true));
            } else if (castle == 3) {
                // both sides castle
                moves.add(new Move(p.getXCoord(), p.getYCoord() + 2, false, true));
                moves.add(new Move(p.getXCoord(), p.getYCoord() - 2, false, true));
            }
        }
        return moves;
    }

    private int checkCastle(Piece king, Piece[][] boardCopy) {
        int toReturn = 0;
        if (((King) king).eligibleForCastle()) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Piece p = boardCopy[i][j];
                    if (p != null) {
                        if (p.isWhite() == whiteTurn) {
                            if (p.getType().equals("Rook")) {
                                if (((Rook) p).eligibleForCastle()) {
                                    boolean queenSide = false;
                                    boolean kingSide = false;
                                    if (p.getYCoord() == 7) { // queenside castling
                                        if (boardCopy[i][4] == null &&
                                                boardCopy[i][5] == null
                                                && boardCopy[i][6] == null) {
                                            queenSide = true;
                                        }
                                    }

                                    if (p.getYCoord() == 0) { // kingside castling
                                        if (boardCopy[i][2] == null && boardCopy[i][1] == null) {
                                            kingSide = true;
                                        }
                                    }

                                    if (queenSide && kingSide) {
                                        toReturn = 3;
                                    } else if (queenSide && !kingSide) {
                                        toReturn = 2;
                                    } else if (!queenSide && kingSide) {
                                        toReturn = 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return toReturn;
    }


    // private helper methods to check bounds for the board, col/row bounds, and diag bounds

    private boolean checkInBounds(int x, int y) {
        return ((x > -1 && x < 8) && (y > -1 && y < 8));
    }

    private boolean checkOutOfBounds(int x, int y, int rightBound,
                                     int leftBound, int topBound, int bottomBound) {
        return ((x >= topBound && x <= bottomBound) && (y >= leftBound && y <= rightBound));
    }

    private boolean checkDiagBounds(int x, int y, int centerX, int centerY,
                                    int topLeftBound,
                                    int topRightBound,
                                    int bottomLeftBound, int bottomRightBound) {
        if (y > centerY) {
            if (x > centerX) {
                return (y <= centerY + bottomRightBound);
            } else if (x < centerX) {
                return (y <= centerY + topRightBound);
            } else {
                return true;
            }
        } else if (y < centerY) {
            if (x > centerX) {
                return (y >= centerY - bottomLeftBound);
            } else if (x < centerX) {
                return (y >= centerY - topLeftBound);
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    // check methods
    private boolean isCheck(Piece king, Map<Piece, Collection<Move>> opposingPossibleMoves) {
        for (Map.Entry<Piece, Collection<Move>> entry : opposingPossibleMoves.entrySet()) {
            Piece p = entry.getKey();
            Collection<Move> moves = entry.getValue();
            for (Move move : moves) {
                if (move.getY() == king.getYCoord() && move.getX() == king.getXCoord()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCheckMate() {
        if (check) {
            for (Map.Entry<Piece, Collection<Move>> entry : possibleMoves.entrySet()) {
                Collection<Move> moves = entry.getValue();
                if (!moves.isEmpty()) {
                    return false;
                }
            }
            return true; // if the king doesn't have any valid possible moves, then it's a checkmate
        }
        return false;
    }

    private boolean isStalemate() {
        if (!check) {
            for (Map.Entry<Piece, Collection<Move>> entry : possibleMoves.entrySet()) {
                Collection<Move> moves = entry.getValue();
                if (!moves.isEmpty()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // check if we can move the king, block with a piece, or capture

    public Map<Piece, Collection<Move>> checkMoveKing(Piece king,
                                                      Map<Piece, Collection<Move>>
                                                              opposingPossibleMoves,
                                                      Map<Piece, Collection<Move>>
                                                              currentPossibleMoves) {
        Collection<Move> moves = currentPossibleMoves.get(king);
        Collection<Move> movesToRemove = new TreeSet<>();
        if (moves != null) {
            for (Move move : moves) {
                Piece[][] boardCopy = copyArray(board);
                boardCopy = movePiece(king.getXCoord(), king.getYCoord(), move, boardCopy);
                Piece kingCopy = boardCopy[move.getX()][move.getY()];
                Map<Piece, Collection<Move>> updatedMoves = getUpdatedOpposingMoves(boardCopy);
                if (isCheck(kingCopy, updatedMoves)) {
                    movesToRemove.add(move);
                }
            }
        }

        for (Move move : movesToRemove) {
            moves.remove(move);
        }
        currentPossibleMoves.put(king, moves);
        return currentPossibleMoves;
    }

    public Map<Piece, Collection<Move>> checkBlockCheck(Piece king,
                                                        Map<Piece, Collection<Move>>
                                                                opposingPossibleMoves,
                                                        Map<Piece, Collection<Move>>
                                                                currentPossibleMoves) {
        for (Map.Entry<Piece, Collection<Move>> entry : currentPossibleMoves.entrySet()) {
            Piece p = entry.getKey();
            Collection<Move> moves = entry.getValue();
            Collection<Move> movesToRemove = new TreeSet<>();
            for (Move move : moves) {
                Piece[][] boardCopy = copyArray(board);
                boardCopy = movePiece(p.getXCoord(), p.getYCoord(), move, boardCopy);
                Map<Piece, Collection<Move>> updatedMoves = getUpdatedOpposingMoves(boardCopy);
                if (p.getType().equals("King")) {
                    if (isCheck(boardCopy[move.getX()][move.getY()], updatedMoves)) {
                        movesToRemove.add(move);
                    }
                } else {
                    if (isCheck(king, updatedMoves)) {
                        movesToRemove.add(move);
                    }
                }
            }
            for (Move move : movesToRemove) {
                moves.remove(move);
            }

            currentPossibleMoves.put(p, moves);
        }
        return currentPossibleMoves;
    }

    public void removeCastleUnderCheck(Piece king) {
        if (check) {
            Collection<Move> moves = possibleMoves.get(king);
            Collection<Move> movesToRemove = new TreeSet<Move>();
            for (Move move : moves) {
                if (move.getCastle()) {
                    movesToRemove.add(move);
                }
            }

            for (Move move : movesToRemove) {
                moves.remove(move);
            }

            possibleMoves.put(king, moves);
        }
    }

    // check helpers

    private Map<Piece, Collection<Move>> getUpdatedOpposingMoves(Piece[][] boardCopy) {
        Map<Piece, Collection<Move>> updatedMoves = new HashMap<Piece, Collection<Move>>();
        for (Piece[] pieces : boardCopy) {
            for (Piece p : pieces) {
                if (p != null) {
                    Collection<Move> moves = p.getMoves(p.getXCoord(), p.getYCoord());
                    if (p.isWhite() != whiteTurn) {
                        moves = removeCollisions(p, moves, boardCopy);
                        updatedMoves.put(p, moves);
                    }
                }
            }
        }
        return updatedMoves;
    }

    private Piece[][] copyArray(Piece[][] array) {
        Piece[][] copiedArray = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = array[i][j];
                if (p != null) {
                    copiedArray[i][j] = p.clone();
                }
            }
        }
        return copiedArray;
    }

    private Piece[][] movePiece(int oldX, int oldY, Move move, Piece[][] boardCopy) {
        Piece p = boardCopy[oldX][oldY];
        int newX = move.getX();
        int newY = move.getY();
        boardCopy[oldX][oldY] = null;
        boardCopy[newX][newY] = p;
        boardCopy[newX][newY].setX(newX);
        boardCopy[newX][newY].setY(newY);
        return boardCopy;
    }

    private Piece getKing(boolean whiteTurn) {
        for (Piece[] pieces : board) {
            for (Piece p : pieces) {
                if (p != null) {
                    if (p.isWhite() == whiteTurn) {
                        if (p.getType().equals("King")) {
                            return p;
                        }
                    }
                }
            }
        }
        return null;
    }

    // debugging methods to print the board

    private void displayBoard(Piece[][] boardCopy) {
        System.out.println();
        for (Piece[] pArray : boardCopy) {
            for (Piece p : pArray) {
                if (p == null) {
                    System.out.print(" - ");
                } else {
                    System.out.print(p.toString());
                }
            }
            System.out.println();
        }
    }

    public void printDisplay() {
        System.out.println();
        for (Piece[] pArray : board) {
            for (Piece p : pArray) {
                if (p == null) {
                    System.out.print(" - ");
                } else {
                    System.out.print(p.toString());
                }
            }
            System.out.println();
        }
    }

    public void displayMoves(Collection<Move> moves, Piece p) {
        System.out.println();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boolean moveExists = false;
                for (Move move : moves) {
                    if (move.getX() == i && move.getY() == j) {
                        System.out.print(" M ");
                        moveExists = true;
                    }
                }

                if (p.getXCoord() == i && p.getYCoord() == j) {
                    System.out.print(p.toString());
                } else if (!moveExists) {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }

    public void displayMovesArray() {
        System.out.println();
        for (boolean[] pArray : movesArray) {
            for (boolean p : pArray) {
                if (p) {
                    System.out.print(" M ");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }
}
