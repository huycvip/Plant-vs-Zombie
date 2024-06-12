package Game;

import javax.swing.*;

public class GameWindow {

    private static JFrame gameWindow;

    public enum GameState {
        MENU(GamePlay.Mode.MENU, 916, 637),
        PLAY(GamePlay.Mode.PLAY, 1400, 637),
        END(GamePlay.Mode.END, 1370, 620),
        WIN(GamePlay.Mode.WIN, 845, 950);

        private final GamePlay.Mode mode;
        private final int width;
        private final int height;

        GameState(GamePlay.Mode mode, int width, int height) {
            this.mode = mode;
            this.width = width;
            this.height = height;
        }

        public GamePlay.Mode getMode() {
            return mode;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }

    public static void main(String[] args) {
        setGameState(GameState.MENU);
    }

    public static void setGameState(GameState state) {
        if (gameWindow != null) {
            gameWindow.dispose();
        }
        System.gc();
        gameWindow = new JFrame("PlantsVsZombies");
        GamePlay gamePlay = new GamePlay(state.getMode());
        gameWindow.setIconImage(gamePlay.imageIcon.getImage());
        gameWindow.add(gamePlay);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(state.getWidth(), state.getHeight());
        gameWindow.setVisible(true);
        gameWindow.setResizable(false);
    }

    public static void selectModel() {
        setGameState(GameState.MENU);
    }

    public static void startPlay() {
        setGameState(GameState.PLAY);
    }

    public static void gameEnd() {
        setGameState(GameState.END);
    }

    public static void gameWin() {
        setGameState(GameState.WIN);
    }
}
