package Zombie;

import javax.swing.*;
import java.awt.*;

public abstract class Zombie extends JPanel {
    public int WIDTH;
    public int HEIGHT;
    public int row;
    public int speed;
    public int px;
    public int py;
    public int px0;
    public int py0;
    public int ly;
    public int pw;
    public int ph;

    public enum State {
        ADVANCE, ATTACK, DEAD
    }

    State state;
    public int life;

    JLayeredPane layeredPane;

    Timer redrawTimer;
    Timer advanceTimer;
    Timer resetSpeedTimer;
    public Image zombieNormalAttackImage;
    public Image zombieNormalAdvanceImage;
    public Image zombieFrozenAttackImage;
    public Image zombieFrozenAdvanceImage;
    public Image zombieAdvanceImage;
    public Image zombieAttackImage;
    public Image zombieDeadImage;
    public Image zombieHeadImage;
    int labelX = 0;
    int labelY = 0;

    public Zombie(int labelX, int labelY, int width, int height, JLayeredPane layeredPane) {
        px0 = -62;
        py0 = -21;
        ly = 0;
        pw = 0;
        ph = -5;
        zombieAdvanceImage = zombieNormalAdvanceImage;
        zombieAttackImage = zombieNormalAttackImage;
        this.row = (labelY - 40) / 100;
        this.labelX = labelX;
        this.labelY = labelY;
        state = State.ADVANCE;
        this.layeredPane = layeredPane;
        WIDTH = width;
        HEIGHT = height;
        speed = 2;
        setSize(WIDTH, HEIGHT);
        setOpaque(false);
        setLocation(labelX, labelY);
        redrawTimer = new Timer(25, (e) -> repaint());
        redrawTimer.start();
        advanceTimer = new Timer(100, (e) -> advance());
        advanceTimer.start();
        resetSpeedTimer = new Timer(4000, (e) -> resetSpeed());
        resetSpeedTimer.start();
        setVisible(true);
        layeredPane.add(this);
        layeredPane.setLayer(this, 4);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (state) {
            case ADVANCE:
                g.drawImage(zombieAdvanceImage, px, py, null);
                break;
            case ATTACK:
                g.drawImage(zombieAttackImage, px - 2, py - 3, null);
                break;
            case DEAD:
                g.drawImage(zombieDeadImage, px, py, null);
                g.drawImage(zombieHeadImage, px, py, null);
                break;
        }
    }

    public void advance() {
        labelX -= speed;
        setLocation(labelX, labelY + ly);
        repaint();
    }

    public void resetSpeed() {
        px = px0;
        py = py0;
        setSpeed(2);
        zombieAdvanceImage = zombieNormalAdvanceImage;
        zombieAttackImage = zombieNormalAttackImage;
        resetSpeedTimer.stop();
    }

    public void slow() {
        px = 0;
        py = 0;
        if (speed != 1) {
            zombieAdvanceImage = zombieFrozenAdvanceImage;
            zombieAttackImage = zombieFrozenAttackImage;
            setSpeed(1);
        }
        resetSpeedTimer.restart();
    }

    public int getLabelY() {
        return labelY;
    }

    public int getLabelX() {
        return labelX;
    }

    public Timer getAdvanceTimer() {
        return advanceTimer;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        if (state == State.ATTACK) {
            setSize(WIDTH + pw, HEIGHT + ph);
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
