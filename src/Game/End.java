package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class End extends JPanel {
    private static final int PANEL_WIDTH = 1400;
    private static final int PANEL_HEIGHT = 637;
    private final Image endImage;

    public End() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setLayout(null); // Cancel default layout
        setVisible(true);

        endImage = new ImageIcon(getClass().getResource("/BackgroundImage/ZombiesWon.jpg")).getImage();

        Sound sound = new Sound(getClass().getResource("/Music/gameOver.wav"), false);
        sound.start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameWindow.selectModel();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(endImage, 0, 0, this);
    }
}
