package Chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.*;
import java.util.List;

public class GameBoard extends JPanel {
    private final Board board; // board for the game
    private final JLabel status; // current status text
    private boolean pieceSelected = false;
    private int xCoord;
    private int yCoord;

    // game dimensions
    public static final int BOARD_WIDTH = 1400;
    public static final int BOARD_HEIGHT = 800;
    private static final int TOP_OFFSET = 50;
    private static final int LEFT_OFFSET = 180;
    private static final int SQUARE_SIZE = 80;

    public GameBoard(JLabel label) {
        // creates border around the board area
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // lets the component be focused
        setFocusable(true);

        board = new Board(); // initializes model for the game
        status = label; // initializes the status JLabel

        /*
         * Listens for mouse clicks.  Updates the model, then updates the game board
         *
         */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                board.updateCurrentMoves();
                Point p = e.getPoint();
                int y = (p.x - LEFT_OFFSET) / 80;
                int x = (p.y - TOP_OFFSET) / 80;

                if (x < 8 && x >= 0 && y < 8 && y >= 0) {
                    if (pieceSelected) {
                        if (board.isMoveValid(xCoord, yCoord, x, y)) {
                            board.movePiece(xCoord, yCoord, x, y, true);
                        } else {
                            board.unselectPiece(x, y);
                        }
                        pieceSelected = false;
                    } else {
                        boolean selectedSuccessful = board.selectPiece(x, y);
                        if (selectedSuccessful) {
                            pieceSelected = true;
                            xCoord = x;
                            yCoord = y;
                        }
                    }
                }

                // updates the model given the coordinates of the mouseclick
                board.updateCurrentMoves();
                updateStatus(); // updates the status JLabel
                repaint(); // repaints the game board

            }
        });
    }

    public void reset() {
        board.reset();
        status.setText("White to move");
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    private void updateStatus() {
        if (board.getTurn()) {
            status.setText("White To Play");
            if (board.isCheck()) {
                status.setText("White to Play, Under Check");
                if (board.getCheckMate()) {
                    status.setText("Checkmate! Black Wins.");
                }
            }
        } else {
            status.setText("Black To Play");
            if (board.isCheck()) {
                status.setText("Black To Play, Under Check");
                if (board.getCheckMate()) {
                    status.setText("Checkmate! White Wins.");
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draws board grid
        Toolkit t = Toolkit.getDefaultToolkit();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int color = (i + j) % 2;
                Piece p = board.getPiece(i, j);
                boolean isMove = board.existsMove(i, j);

                // colors the board
                if (color == 0) {
                    g.setColor(new Color(252, 204, 116));
                } else {
                    g.setColor(new Color(87, 58, 46));
                }

                if (isMove) {
                    g.setColor(new Color(175, 255, 117));
                }

                g.fillRect(SQUARE_SIZE * j + LEFT_OFFSET, SQUARE_SIZE * i + TOP_OFFSET,
                        SQUARE_SIZE, SQUARE_SIZE);

                // color pieces
                if (p != null) {
                    String imgPath = getImagePath(p.getType(), p.isWhite());
                    Image img = t.getImage(imgPath);
                    g.drawImage(img, SQUARE_SIZE * j + LEFT_OFFSET + 10,
                            SQUARE_SIZE * i + TOP_OFFSET + 10, this);
                }
            }
        }

        // show captured pieces
        List<Piece> whiteCaptured = board.getWhiteCaptured();
        List<Piece> blackCaptured = board.getBlackCaptured();

        int[][] numbers = new int[2][6];

        for (int[] number : numbers) {
            Arrays.fill(number, 0);
        }

        g.setColor(Color.BLACK);
        numbers = updateCapturedNumbers(whiteCaptured, blackCaptured, numbers);
        Map<Integer, String> map = new HashMap<Integer, String>(); // maps the index to the piece type
        map.put(0, "King");
        map.put(1, "Queen");
        map.put(2, "Rook");
        map.put(3, "Knight");
        map.put(4, "Bishop");
        map.put(5, "Pawn");

        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.5F);
        g.setFont(newFont);

        g.drawString("Captured Pieces", 850, 40);
        g.drawString("Captured Pieces", 20, 40);

        for (int i = 0; i < numbers.length; i++) {
            int numDisplayed = 0;
            for (int j = 0; j < numbers[i].length; j++) {
                int number = numbers[i][j];
                String pieceType = map.get(j);
                if (number != 0) {
                    if (i == 0) {
                        String imgPath = getImagePath(pieceType, true);
                        Image img = t.getImage(imgPath);
                        g.drawImage(img, 880, 60 + 70 * numDisplayed, this);
                        String times = "x" + number;
                        g.drawString(times, 940, 100 + 70 * numDisplayed);
                    } else {
                        String imgPath = getImagePath(pieceType, false);
                        Image img = t.getImage(imgPath);
                        g.drawImage(img, 40, 60 + 70 * numDisplayed, this);
                        String times = "x" + number;
                        g.drawString(times, 100, 100 + 70 * numDisplayed);
                    }
                    numDisplayed++;
                }

            }
        }
        g.drawString("Instructions Window", 1050, 60);
        Font oldFont = currentFont.deriveFont(currentFont.getSize() * 1.0F);
        g.setFont(oldFont);
        g.drawString("White starts playing.", 1050, 80);
        g.drawString("Click any of the pieces to see all possible moves.", 1050, 100);
        g.drawString("Click on one of the " + "green squares to play the move.", 1050, 120);
        g.drawString("First player to checkmate wins!", 1050, 140);
    }

    private int[][] updateCapturedNumbers(List<Piece> whiteCaptured,
                                          List<Piece> blackCaptured, int[][] numbers) {
        for (Piece piece : whiteCaptured) {
            switch (piece.getType()) {
                case "King":
                    numbers[0][0]++;
                    break;
                case "Queen":
                    numbers[0][1]++;
                    break;
                case "Rook":
                    numbers[0][2]++;
                    break;
                case "Knight":
                    numbers[0][3]++;
                    break;
                case "Bishop":
                    numbers[0][4]++;
                    break;
                case "Pawn":
                    numbers[0][5]++;
                    break;
                default:
                    break;
            }
        }

        for (Piece piece : blackCaptured) {
            switch (piece.getType()) {
                case "King":
                    numbers[1][0]++;
                    break;
                case "Queen":
                    numbers[1][1]++;
                    break;
                case "Rook":
                    numbers[1][2]++;
                    break;
                case "Knight":
                    numbers[1][3]++;
                    break;
                case "Bishop":
                    numbers[1][4]++;
                    break;
                case "Pawn":
                    numbers[1][5]++;
                    break;
                default:
                    break;
            }
        }
        return numbers;
    }

    // accesses file where all images of chess pieces are stored
    private String getImagePath(String p, boolean isWhite) {
        String pieceName = "chess_pieces/";
        switch (p) {
            case "Pawn":
                pieceName = pieceName + "pawn";
                break;
            case "Queen":
                pieceName = pieceName + "queen";
                break;
            case "Rook":
                pieceName = pieceName + "rook";
                break;
            case "Knight":
                pieceName = pieceName + "knight";
                break;
            case "Bishop":
                pieceName = pieceName + "bishop";
                break;
            case "King":
                pieceName = pieceName + "king";
                break;
            default:
                break;
        }
        if (isWhite) {
            pieceName = pieceName + "_white.png";
        } else {
            pieceName = pieceName + "_black.png";
        }
        return pieceName;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}