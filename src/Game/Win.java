package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Win extends JPanel implements MouseListener {

    public Win() {
        setSize(845, 950);
        setLayout(null);
        Sound gameWin = new Sound(this.getClass().getResource("/Music/gameWin.wav"), false);
        gameWin.start();
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon(this.getClass().getResource("/BackgroundImage/PlayerWin.gif")).getImage(), 0, 0, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GameWindow.selectModel();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
