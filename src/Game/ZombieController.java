package Game;

import Zombie.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ZombieController {
    int producedZombies = 0;
    Timer zombieProducerTimer;
    Timer zombieStateCheckTimer;
    Timer attackTimer;
    Timer gameWinCheckTimer;
    GamePlay gamePlay;
    Random yRandom;
    Random kindOfZombie;
    boolean hasFirstZombie = false;
    boolean hasZombieEating = false;
    ArrayList<Zombie> zombieArrayList1;
    ArrayList<Zombie> zombieArrayList2;
    ArrayList<Zombie> zombieArrayList3;
    ArrayList<Zombie> zombieArrayList4;
    ArrayList<Zombie> zombieArrayList5;
    Sound zombieEating = new Sound(this.getClass().getResource("/Music/zombieEat.wav"), true);
    JLabel waveImage;
    Timer closeImage;

    ZombieController(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
        yRandom = new Random();
        kindOfZombie = new Random();
        zombieArrayList1 = new ArrayList();
        zombieArrayList2 = new ArrayList();
        zombieArrayList3 = new ArrayList();
        zombieArrayList4 = new ArrayList();
        zombieArrayList5 = new ArrayList();
        zombieEating.start();//开启线程
        zombieProducerTimer = new Timer(10000, (ActionEvent) -> nextZombie());
        zombieStateCheckTimer = new Timer(10, (ActionEvent) -> zombieStateCheck());
        attackTimer = new Timer(800, (ActionEvent) -> attack());
        gameWinCheckTimer = new Timer(10000, (AboutEvent) -> gameWinCheck());
        zombieProducerTimer.start();
        zombieStateCheckTimer.start();
        attackTimer.start();
    }

    public void nextZombie() {
        if (producedZombies == 50) {
            gameWinCheckTimer.start();
            zombieProducerTimer.stop();
            return;
        }
        int y = yRandom.nextInt(5);
        if (!hasFirstZombie) {
            Sound zombieComing = new Sound(this.getClass().getResource("/Music/zombieComing.wav"), false);
            zombieComing.start();
            hasFirstZombie = true;
            getZombieArrayList(y + 1).add(new FlagZombie(1400, 40 + 100 * y, gamePlay));
            producedZombies++;
        } else {
            if (producedZombies <= 10) {
                getZombieArrayList(y + 1).add(new NormalZombie(1400, 40 + 100 * y, gamePlay));
                producedZombies++;
            } else if (producedZombies <= 30) {
                if(producedZombies == 11){
                    waveImage = new JLabel();
                    waveImage.setIcon(new ImageIcon(this.getClass().getResource("/BackgroundImage/hugeWave.gif")));
                    waveImage.setSize(286,34);
                    waveImage.setLocation(450,200);
                    waveImage.setVisible(true);
                    waveImage.setLayout(null);
                    gamePlay.add(waveImage);
                    gamePlay.setLayer(waveImage,5);
                    closeImage = new Timer(3000,action->closeImage());
                    closeImage.start();
                    Sound hugeWave = new Sound(this.getClass().getResource("/Music/hugeWave.wav"),false);
                    hugeWave.start();
                }
                zombieProducerTimer.setDelay(6000);
                int zombieKind = kindOfZombie.nextInt(2);
                switch (zombieKind) {
                    case 0:
                        getZombieArrayList(y + 1).add(new NormalZombie(1400, 40 + 100 * y, gamePlay));
                        producedZombies++;
                        break;
                    default:
                        getZombieArrayList(y + 1).add(new ConeheadZombie(1400, 40 + 100 * y, gamePlay));
                        producedZombies++;
                }
            } else {
                if(producedZombies == 31){
                    waveImage = new JLabel();
                    waveImage.setIcon(new ImageIcon(this.getClass().getResource("/BackgroundImage/finalWave.png")));
                    waveImage.setSize(246,67);
                    waveImage.setLocation(450,200);
                    waveImage.setVisible(true);
                    waveImage.setLayout(null);
                    gamePlay.add(waveImage);
                    gamePlay.setLayer(waveImage,5);
                    closeImage.restart();
                    Sound finalWave = new Sound(this.getClass().getResource("/Music/finalWave.wav"),false);
                    finalWave.start();
                }

                zombieProducerTimer.setDelay(3000);
                int zombieKind = kindOfZombie.nextInt(9);
                switch (zombieKind) {
                    case 0:
                        getZombieArrayList(y + 1).add(new NormalZombie(1400, 40 + 100 * y, gamePlay));
                        producedZombies++;
                        break;
                    case 1:
                    case 2:
                        getZombieArrayList(y + 1).add(new ConeheadZombie(1400, 40 + 100 * y, gamePlay));
                        producedZombies++;
                        break;
                    default:
                        getZombieArrayList(y + 1).add(new BucketheadZombie(1400, 40 + 100 * y, gamePlay));
                        producedZombies++;
                }
            }
        }
    }

    public void zombieStateCheck() {
        hasZombieEating = false;
        for (int i = 1; i <= 5; i++) {
            ArrayList<Zombie> zombieArrayList = getZombieArrayList(i);
            Iterator iterator = zombieArrayList.iterator();
            while (iterator.hasNext()) {
                Zombie zombie = (Zombie) iterator.next();
                if (zombie.life <= 0) {
                    zombie.setSpeed(2);
                    zombie.setState(Zombie.State.DEAD);
                    iterator.remove();
                    gamePlay.remove(zombie);
                    System.gc();
                } else if (
                        zombie.getLabelX() < 970 &&
                                zombie.getLabelX() > 260 &&
                                gamePlay.getGrass(zombie.getLabelX(), zombie.getLabelY() + 50).isAssigned() &&
                                (zombie.getLabelX() - gamePlay.getGrass(zombie.getLabelX(), zombie.getLabelY() + 50).getX()) < 30 &&
                                (zombie.getLabelX() - gamePlay.getGrass(zombie.getLabelX(), zombie.getLabelY() + 50).getX()) > 0
                ) {
                    zombie.setState(Zombie.State.ATTACK);
                    zombie.getAdvanceTimer().stop();
                    hasZombieEating = true;
                } else if (zombie.getLabelX() <= 210) {
                    zombieStateCheckTimer.stop();
                    gamePlay.getBGM().stop();
                    GameWindow.gameEnd();
                } else {
                    zombie.setState(Zombie.State.ADVANCE);
                    zombie.getAdvanceTimer().start();//僵尸前进
                }
            }
        }
        if (hasZombieEating) {
            if (zombieEating.isStop != false) {
                zombieEating.setStop(false);
            }
        } else {
            if (zombieEating.isStop != true)
                zombieEating.setStop(true);
        }
    }

    public void attack() {
        for (int i = 1; i <= 5; i++) {
            ArrayList<Zombie> zombieArrayList = getZombieArrayList(i);
            Iterator iterator = zombieArrayList.iterator();
            while (iterator.hasNext()) {
                Zombie zombie = (Zombie) iterator.next();
                if (zombie.getState() == Zombie.State.ATTACK) {
                    if (gamePlay.getGrass(zombie.getLabelX(), zombie.getLabelY() + 50).isAssigned()) {
                        gamePlay.getGrass(zombie.getLabelX(), zombie.getLabelY() + 50).loseLife();
                    }
                }
            }
        }
    }

    public boolean isRowHasZombie(int row) {
        Iterator iterator = getZombieArrayList(row).iterator();
        while (iterator.hasNext()) {
            Zombie zombie = (Zombie) iterator.next();
            if (zombie.getLabelX() < 1000) {
                return true;
            }
        }
        return false;
    }

    public void gameWinCheck() {
        for (int i = 1; i <= 5; i++) {
            if (isRowHasZombie(i)) {
                return;
            }
        }
        gamePlay.getBGM().stop();
        gameWinCheckTimer.stop();
        GameWindow.gameWin();
    }

    public void closeImage(){
        gamePlay.remove(waveImage);
        gamePlay.repaint();
        closeImage.stop();
    }
    //getter
    public ArrayList<Zombie> getZombieArrayList(int row) {
        if (row == 1) return zombieArrayList1;
        else if (row == 2) return zombieArrayList2;
        else if (row == 3) return zombieArrayList3;
        else if (row == 4) return zombieArrayList4;
        else if (row == 5) return zombieArrayList5;
        else return null;
    }
}
