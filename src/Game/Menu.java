package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Menu class
public class Menu extends JPanel {
    private static final int MENU_WIDTH = 916;
    private static final int MENU_HEIGHT = 637;
    private static final int START_BUTTON_X = 470;
    private static final int START_BUTTON_Y = 90;
    private static final int START_BUTTON_WIDTH = 280;
    private static final int START_BUTTON_HEIGHT = 150;

    private final Image menuImage;
    private final Image startButtonImage;

    // Menu class constructor
    public Menu() {
        this.setSize(MENU_WIDTH, MENU_HEIGHT);
        this.setVisible(true);
        this.setLayout(null); // Cancel default layout

        menuImage = new ImageIcon(this.getClass().getResource("/BackgroundImage/menu.png")).getImage();
        startButtonImage = new ImageIcon(this.getClass().getResource("/BackgroundImage/start.png")).getImage();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });
    }

    // Draw menu
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuImage, 0, 0, this);
        g.drawImage(startButtonImage, START_BUTTON_X, START_BUTTON_Y, this);
    }

    // Handle mouse click to start the game
    private void handleMouseClick(MouseEvent e) {
        if (isStartButtonClicked(e.getX(), e.getY())) {
            GameWindow.startPlay(); // Call startPlay method to load the game interface
        }
    }

    // Check if the start button was clicked
    private boolean isStartButtonClicked(int x, int y) {
        return x > START_BUTTON_X && x < (START_BUTTON_X + START_BUTTON_WIDTH)
                && y > START_BUTTON_Y && y < (START_BUTTON_Y + START_BUTTON_HEIGHT);
    }
}
