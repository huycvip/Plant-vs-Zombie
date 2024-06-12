package PlantCard;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

// Abstract class for plant cards
public abstract class PlantCard extends JLabel implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    int X;
    int Y;
    int x0;
    int y0;
    int dragCounter = 0;
    ImageIcon PlantCardImage;
    final int WIDTH = 50;
    final int HEIGHT = 70;

    // Constructor
    public PlantCard(int X, int Y, JLayeredPane layeredPane) {
        this.X = X;
        this.Y = Y;
        setSize(WIDTH, HEIGHT);
        setLocation(X, Y);
        setVisible(true);
        this.layeredPane = layeredPane;
        layeredPane.setLayer(this, 3);
        layeredPane.add(this);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    // Implementing card dragging
    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragCounter == 0) {
            x0 = e.getX();
            y0 = e.getY();
            dragCounter++;
        }
        setLocation(getX() + e.getX() - x0, getY() + e.getY() - y0);
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
