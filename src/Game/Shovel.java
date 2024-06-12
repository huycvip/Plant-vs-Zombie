package Game;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

// Shovel class represents the shovel tool used to remove plants
public class Shovel extends JLabel implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    int X;
    int Y;
    int x0;
    int y0;
    int dragCounter = 0;
    final int WIDTH = 76;
    final int HEIGHT = 34;

    // Constructor for the Shovel class
    public Shovel(int X, int Y, JLayeredPane layeredPane) {
        this.X = X;
        this.Y = Y;
        this.setIcon(new ImageIcon(this.getClass().getResource("/BackgroundImage/Shovel.png")));
        this.setSize(WIDTH, HEIGHT);
        this.setLocation(X, Y);
        this.setVisible(true);
        this.layeredPane = layeredPane;
        layeredPane.setLayer(this, 3);
        layeredPane.add(this);
        layeredPane.setLayer(this, 3);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    // When the mouse is released, check if there is a plant at the location and remove it
    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.getX() + e.getX() > 260 && this.getY() + e.getY() > 90 && this.getX() + e.getX() < 260 + 80 * 9 && this.getY() + e.getY() < 90 + 100 * 5) {
            GamePlay gamePlay = (GamePlay) layeredPane;
            if (gamePlay.getGrass(this.getX() + e.getX(), this.getY() + e.getY()).isAssigned()) {
                gamePlay.getGrass(this.getX() + e.getX(), this.getY() + e.getY()).removeAssignedPlant();
            }
        }
        this.setLocation(X, Y);
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    // Drag the shovel to move it
    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragCounter == 0) {
            x0 = e.getX();
            y0 = e.getY();
            dragCounter++;
        }
        this.setLocation(this.getX() + e.getX() - x0, this.getY() + e.getY() - y0);
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
