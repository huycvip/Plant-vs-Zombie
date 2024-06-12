package Plant;

import Game.GamePlay;
import Game.Sound;
import Zombie.Zombie;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class CherryBomb extends Plant {
    Timer bombTimer;
    Timer counterTimer;
    static Image[] plantImage = new Image[11];
    int counter;

    {
        for (int i = 0; i < 11; i++) {
            plantImage[i] = new ImageIcon(this.getClass().getResource("/Image/PlantImage/" + (i + 1) + ".gif")).getImage();
        }
    }

    public CherryBomb(GamePlay gamePlay) {
        super(112, 81, 150, gamePlay);
        gamePlay.setLayer(this, 6);
        redrawTimer.setDelay(200);
        redrawTimer.restart();
        counterTimer = new Timer(200, (ActionEvent) -> count());
        counterTimer.start();
        super.px = 0;
        super.py = 0;
        counter = 0;
        life = 20;
        bombTimer = new Timer(1600, (ActionEvent) -> bomb());
        bombTimer.start();
    }

    public void paintComponent(Graphics g) {
        if (counter <= 10) {
            g.drawImage(plantImage[counter], px, py, null);
        } else {
            redrawTimer.stop();
        }
    }

    public void count() {
        counter++;
    }

    public void bomb() {
        Sound bombSound = new Sound(this.getClass().getResource("/Music/CherryBomb.wav"), false);
        bombSound.start();
        redrawTimer.stop();
        int row = (this.getY() - 90) / 100 + 1;
        if (row == 1) {
            for (int i = 0; i < 2; i++) {
                Iterator zombiesIterator = gamePlay.getZombieController().getZombieArrayList(row + i).iterator();
                while (zombiesIterator.hasNext()) {
                    Zombie zombie = (Zombie) zombiesIterator.next();
                    if (Math.abs((zombie.getLabelX() - this.getX())) < 100) {
                        zombie.setState(Zombie.State.DEAD);
                        zombiesIterator.remove();
                        gamePlay.remove(zombie);
                        bombTimer.stop();
                        System.gc();
                    }
                }
            }
        } else if (row == 5) {
            for (int i = -1; i < 1; i++) {
                Iterator zombiesIterator = gamePlay.getZombieController().getZombieArrayList(row + i).iterator();
                while (zombiesIterator.hasNext()) {
                    Zombie zombie = (Zombie) zombiesIterator.next();
                    if (Math.abs((zombie.getLabelX() - this.getX())) < 100) {
                        zombie.setState(Zombie.State.DEAD);
                        zombiesIterator.remove();
                        gamePlay.remove(zombie);
                        bombTimer.stop();
                        System.gc();
                    }
                }
            }
        } else {
            for (int i = -1; i < 2; i++) {
                Iterator zombiesIterator = gamePlay.getZombieController().getZombieArrayList(row + i).iterator();
                while (zombiesIterator.hasNext()) {
                    Zombie zombie = (Zombie) zombiesIterator.next();
                    if (zombie.getLabelX() - this.getX() < 150 && zombie.getLabelX() - this.getX() > -100) {
                        zombie.setState(Zombie.State.DEAD);
                        zombiesIterator.remove();
                        gamePlay.remove(zombie);
                        System.gc();
                    }
                }
            }
        }
        bombTimer.stop();
        life = 0;
    }
}
