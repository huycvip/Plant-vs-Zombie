package Game;

import javax.swing.*;
import java.awt.*;

public class Yard extends JPanel {
    public Yard() {
        setSize(1400, 637);
        setVisible(true);
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon(this.getClass().getResource("/BackgroundImage/yard.jpg")).getImage(), 0, 0, null);
        g.drawImage(new ImageIcon(this.getClass().getResource("/BackgroundImage/sunBoard.png")).getImage(), 0, 0, null);
    }
}
