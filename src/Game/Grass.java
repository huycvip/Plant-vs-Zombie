package Game;

import Plant.Chomper;
import Plant.Peashooter;
import Plant.Plant;
import Plant.PotatoMine;

import javax.swing.*;


//草地类
public class Grass {

    private int X = 260;
    private int Y = 90;

    final int WIDTH = 80;
    final int HEIGHT = 100;
    private Boolean isAssigned = false;
    private Plant assignedPlant = null;
    Timer isRowHasZombieTimer;
    Timer checkStateTimer;
    GamePlay gamePlay;

    public Grass(int i, int j, GamePlay gamePlay) {
        this.gamePlay = gamePlay;
        X += WIDTH * j;
        Y += HEIGHT * i;
        checkStateTimer = new Timer(20, (ActionEvent) -> checkState());
        checkStateTimer.start();
    }


    public void checkState() {
        if (isAssigned) {
            if (assignedPlant.life <= 0) {
                removeAssignedPlant();
            }
        }
    }

    public void removeAssignedPlant() {
        if (assignedPlant instanceof Peashooter) {
            Peashooter peashooter = (Peashooter) assignedPlant;
            peashooter.setIsShooting(false);
        }
        else if (assignedPlant instanceof PotatoMine) {
            PotatoMine potatoMine = (PotatoMine) assignedPlant;
            potatoMine.timerStop();
        }
        else if (assignedPlant instanceof Chomper) {
            Chomper chomper = (Chomper) assignedPlant;
            chomper.timerStop();
        }

        gamePlay.remove(assignedPlant);
        assignedPlant = null;
        isAssigned = false;
        gamePlay.repaint();
    }

    public void setAssignedPlant(Plant plant) {
            assignedPlant = plant;
            isAssigned = true;
            if (assignedPlant instanceof Peashooter) {
                isRowHasZombieTimer = new Timer(25, (ActionEvent) -> setIsShooting());
                isRowHasZombieTimer.start();
            }
            gamePlay.add(assignedPlant);
            if (assignedPlant instanceof PotatoMine) {
                assignedPlant.setLocation(X + 10, Y + 20);
            } else {
                assignedPlant.setLocation(X, Y);
            }
            gamePlay.setLayer(assignedPlant, 3);
            gamePlay.getSunLabel().addCurrentSun(-1 * plant.sunUse);
            Sound plantHit = new Sound(this.getClass().getResource("/Music/plantHit.wav"), false);
            plantHit.start();
    }

    public void loseLife() {
        if (isAssigned) {
            assignedPlant.life--;
        }
    }

    public void setIsShooting() {
        if (isAssigned) {
            if (assignedPlant.getGamePlay().isRowHasZombie((Y - 90) / 100 + 1)) {
                if (assignedPlant instanceof Peashooter) {
                    Peashooter peashooter = (Peashooter) assignedPlant;
                    peashooter.setIsShooting(true);
                }
            } else {
                if (assignedPlant instanceof Peashooter) {
                    Peashooter peashooter = (Peashooter) assignedPlant;
                    peashooter.setIsShooting(false);
                }
            }
        }
    }

    //getter and setter
    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

}
