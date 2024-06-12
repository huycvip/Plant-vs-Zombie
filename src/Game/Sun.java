package Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Sun extends JLabel implements MouseListener {
    // Coordinates of the sun
    int X;
    int Y;
    // Speed of the sun's descent
    int ySpeed = 10;
    // Reference to the layered pane for adding/removing the sun
    JLayeredPane layeredPane;
    // Timer for controlling the sun's descent
    Timer dropTimer;

    // Constructor
    public Sun(int x, int y, JLayeredPane layeredPane) {
        this.layeredPane = layeredPane;
        this.X = x;
        this.Y = y;
        // Initialize and start the timer for sun's descent
        dropTimer = new Timer(25, (ActionEvent) -> drop());
        this.addMouseListener(this); // Add mouse listener to handle sun clicks
        // Set sun icon and properties
        this.setIcon(new ImageIcon(this.getClass().getResource("/BackgroundImage/Sun.gif")));
        this.setSize(72, 65);
        this.setVisible(true);
        this.setLocation(X, Y);
        // Add sun to layered pane
        layeredPane.add(this);
        layeredPane.setLayer(this, 4);
        dropTimer.start();
    }

    // Method to handle sun's descent
    public void drop() {
        if (Y < 500) {
            Y += 2; // Increment Y coordinate for descent
            this.setLocation(X, Y);
        }
    }

    // Getter for the drop timer
    public Timer getDropTimer() {
        return dropTimer;
    }

    // Mouse listener methods
    @Override
    public void mouseClicked(MouseEvent e) {
        // Remove the sun when clicked
        layeredPane.remove(this);
        // Update the sun label's total sun count
        GamePlay gamePlay = (GamePlay) layeredPane;
        gamePlay.getSunLabel().addCurrentSun(25);
        layeredPane.repaint(); // Repaint the layered pane
        // Play click sound effect
        Sound clickSun = new Sound(this.getClass().getResource("/Music/clickSun.wav"), false);
        clickSun.start();
    }

    // Unused mouse listener methods
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
