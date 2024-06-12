package Game;

import javax.sound.sampled.*;
import java.io.IOException;

public class BGM extends Thread {
    private static final String BGM_FILE_PATH = "/Music/bgm.wav";
    private static final int BUFFER_SIZE = 1024;

    @Override
    public void run() {
        while (true) {
            playMusic();
        }
    }

    private void playMusic() {
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(BGM_FILE_PATH))) {
            AudioFormat audioFormat = audioInputStream.getFormat();
            try (SourceDataLine sourceDataLine = AudioSystem.getSourceDataLine(audioFormat)) {
                sourceDataLine.open(audioFormat);
                sourceDataLine.start();

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead;

                while ((bytesRead = audioInputStream.read(buffer, 0, buffer.length)) != -1) {
                    sourceDataLine.write(buffer, 0, bytesRead);
                }

                sourceDataLine.drain();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
