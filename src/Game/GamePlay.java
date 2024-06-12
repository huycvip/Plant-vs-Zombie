package Game;

import PlantCard.*;

import javax.swing.*;

// GamePlay class represents the main gameplay panel where the game is played
public class GamePlay extends JLayeredPane {
    ImageIcon imageIcon; // Icon image for the game
    JLabel preImage; // Label for the preparation image
    Grass[][] grasses; // 2D array to hold Grass objects representing the game grid
    Shovel shovel; // Shovel object for removing plants
    SunProducer sunProducer; // SunProducer object for generating sun
    SunLabel sunLabel; // SunLabel object for displaying sun count
    ZombieController zombieController; // ZombieController object for managing zombies
    BGM bgm; // Background music player
    Timer closePreImageTimer; // Timer for closing the preparation image

    // Enumeration for different game modes
    public enum Mode {
        MENU, // Main menu mode
        PLAY, // Gameplay mode
        END,  // End game mode
        WIN   // Game win mode
    }

    // Constructor for the GamePlay class
    public GamePlay(Mode mode) {
        this.setSize(1400, 637); // Set size for the gameplay panel
        this.setVisible(true); // Set the panel to be visible
        imageIcon = new ImageIcon(this.getClass().getResource("/BackgroundImage/IconImage.png")); // Load icon image

        // Switch statement to handle different game modes
        switch (mode) {
            case MENU:
                this.setSize(916, 637); // Set size for the menu mode
                this.add(new Menu()); // Add the menu panel
                break;
            case PLAY:
                setupPlayMode(); // Setup the gameplay mode
                break;
            case END:
                this.add(new End()); // Add the end game panel
                this.setLayer(new End(), 0); // Set the layer for the end game panel
                break;
            case WIN:
                this.add(new Win()); // Add the game win panel
                this.setLayer(new Win(), 0); // Set the layer for the game win panel
                break;
        }
    }

    // Method to setup the gameplay mode
    private void setupPlayMode() {
        Yard yard = new Yard(); // Create a new Yard object
        this.add(yard); // Add the Yard object to the gameplay panel
        this.setLayer(yard, 0); // Set the layer for the Yard object

        // Add preparation image
        preImage = new JLabel();
        preImage.setIcon(new ImageIcon(this.getClass().getResource("/BackgroundImage/PrepareGrowPlants.png")));
        preImage.setSize(255, 324);
        preImage.setLocation(450, 120);
        preImage.setVisible(true);
        preImage.setLayout(null);
        this.add(preImage);
        this.setLayer(preImage, 3);

        grassesInit(); // Initialize grass grid
        shovelInit(); // Initialize shovel
        sunProducerInit(); // Initialize sun producer
        sunLabelInit(); // Initialize sun label
        plantCardInit(); // Initialize plant cards
        zombieControllerInit(); // Initialize zombie controller
        bgm = new BGM(); // Initialize background music
        bgm.start(); // Start playing background music
        closePreImageTimer = new Timer(3000, (ActionEvent) -> closePreImage()); // Setup timer to close preparation image
        closePreImageTimer.start(); // Start the timer
    }

    // Method to initialize the grass grid
    public void grassesInit() {
        grasses = new Grass[5][9];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                grasses[i][j] = new Grass(i, j, this);
            }
        }
    }

    // Method to initialize the shovel
    public void shovelInit() {
        this.shovel = new Shovel(600, 0, this);
    }

    // Method to initialize the sun producer
    public void sunProducerInit() {
        this.sunProducer = new SunProducer(this);
    }

    // Method to initialize the sun label
    public void sunLabelInit() {
        this.sunLabel = new SunLabel(this);
    }

    // Method to initialize plant cards
    public void plantCardInit() {
        new PeaShooterCard(80, 8, this);
        new SunflowerCard(130, 8, this);
        new WallNutCard(180, 8, this);
        new SnowPeaShooterCard(230, 8, this);
        new PotatoMineCard(280, 8, this);
        new CherryBombCard(330, 8, this);
        new ChomperCard(380, 8, this);
        Sound sound = new Sound(this.getClass().getResource("/Music/readysetplant.wav"), false); // Sound effect for ready set plant
        sound.start(); // Start playing sound effect
    }

    // Method to initialize the zombie controller
    public void zombieControllerInit() {
        zombieController = new ZombieController(this);
    }

    // Method to check if a row has zombies
    public boolean isRowHasZombie(int row) {
        return zombieController.isRowHasZombie(row);
    }

    // Method to close the preparation image
    public void closePreImage() {
        this.remove(preImage);
        this.repaint();
        closePreImageTimer.stop();
    }

    // Method to get the background music player
    public BGM getBGM() {
        return bgm;
    }

    // Method to get the grass object at a specific location
    public Grass getGrass(int x, int y) {
        return grasses[(y - 90) / 100][(x - 260) / 80];
    }

    // Method to get the zombie controller
    public ZombieController getZombieController() {
        return zombieController;
    }

    // Method to get the sun label
    public SunLabel getSunLabel() {
        return sunLabel;
    }

    // Method to get a component of a specific class
    public <T> T getComponent(Class<T> componentClass) {
        if (componentClass == Grass.class) {
            return componentClass.cast(grasses[0][0]);
        } else if (componentClass == Shovel.class) {
            return componentClass.cast(shovel);
        } else if (componentClass == SunProducer.class) {
            return componentClass.cast(sunProducer);
        } else if (componentClass == SunLabel.class) {
            return componentClass.cast(sunLabel);
        } else if (componentClass == ZombieController.class) {
            return componentClass.cast(zombieController);
        } else if (componentClass == BGM.class) {
            return componentClass.cast(bgm);
        }
        return null;
    }
}
