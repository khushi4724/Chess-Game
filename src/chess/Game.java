package Chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Game implements Runnable {

    @Override
    public void run() {
        final JFrame frame = new JFrame("Chess");
        frame.setLocation(100, 100);
        // status panel
        final JPanel statusPanel = new JPanel();
        frame.add(statusPanel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Chess Game");
        statusPanel.add(status);

        final GameBoard board = new GameBoard(status);
        frame.add(board, BorderLayout.CENTER);

        // creating panel for reset button
        final JPanel controlPanel = new JPanel();
        frame.add(controlPanel, BorderLayout.NORTH);

        // added an action listener for the reset button
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.reset();
            }
        });
        controlPanel.add(reset);

        // sizes the frame
        frame.pack();
        // exits the program when the user closes the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // makes the frame appear on screen
        frame.setVisible(true);
        // starts the game
        board.reset();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}
