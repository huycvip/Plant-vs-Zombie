package Bullet;
import Game.GamePlay;
import Game.Sound;
import Game.ZombieController;
import Zombie.Zombie;

import javax.swing.*;
import java.util.Iterator;

public abstract class Bullet extends JLabel {
    int labelX;
    int labelY;
    int xSpeed;
    int row;
    GamePlay gamePlay;
    Timer advanceTimer;
    Timer collisionTimer;
    boolean isLive;


    public Bullet(int x, int y, GamePlay g) {
        xSpeed = 10;
        this.gamePlay = g;
        this.labelX = x;
        this.labelY = y;
        row = ((y - 90) / 100) + 1;
        isLive = true;
        advanceTimer = new Timer(16, (ActionEvent) -> advance());
        collisionTimer = new Timer(25, (ActionEvent) -> collisionDetection());
        advanceTimer.start();
        collisionTimer.start();
        this.setSize(56, 34);
        this.setVisible(true);
        this.setLocation(x, y);
        gamePlay.add(this);
        gamePlay.setLayer(this, 4);
    }


    public void collisionDetection() {
        if(this.labelX > 1400){
            gamePlay.remove(this);
            advanceTimer.stop();
            collisionTimer.stop();
            System.gc();
            return;
        }
        ZombieController zombieController = gamePlay.getZombieController();
        Iterator iterator = zombieController.getZombieArrayList(row).iterator();
        while (iterator.hasNext()) {
            Zombie zombie = (Zombie) iterator.next();

            if ((zombie.getLabelX() - this.labelX) < 5 && zombie.life > 0 && (zombie.getLabelX() - this.labelX) > -5) {
                Sound sound = new Sound(this.getClass().getResource("/Music/tap.wav"),false);
                sound.start();
                zombie.life -= 1;
                gamePlay.remove(this);
                isLive = false;
                advanceTimer.stop();
                collisionTimer.stop();
                System.gc();
            }
        }
    }


    private void advance() {
        labelX += 4;
        this.setLocation(labelX, labelY);
        this.repaint();
    }

}
