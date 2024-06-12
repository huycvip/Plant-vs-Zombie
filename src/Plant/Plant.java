package Plant;

import Game.GamePlay;

import javax.swing.*;
import java.awt.*;

public abstract class Plant extends JPanel {

    protected int WIDTH;
    protected int HEIGHT;
    public int sunUse;
    public int life;
    GamePlay gamePlay;
    Timer redrawTimer;
    protected Image plantImage;
    int px;
    int py;

    public Plant(int width, int height, int sunUse, GamePlay gamePlay) {
        px = 0;
        py = 0;
        this.gamePlay = gamePlay;
        this.sunUse = sunUse;
        WIDTH = width;
        HEIGHT = height;
        setSize(WIDTH, HEIGHT);
        setOpaque(false);
        setVisible(true);
        redrawTimer = new Timer(25, (ActionEvent) -> repaint());
        redrawTimer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(plantImage, px, py, null);
    }

    public GamePlay getGamePlay() {
        return gamePlay;
    }

    public int getSunUse() {
        return sunUse;
    }
}
