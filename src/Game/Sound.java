package Game;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound extends Thread {
    public boolean isStop = true; // Flag to control whether the sound is stopped or not
    private URL url; // URL of the audio file
    private boolean loopNeed; // Flag indicating if the sound should loop or not

    // Setter method to update the value of isStop
    public void setStop(boolean isStoop) {
        this.isStop = isStoop;
    }

    // Constructor with two parameters: URL of the audio file and whether it needs to loop
    public Sound(URL url, boolean loopNeed) {
        this.url = url;
        this.loopNeed = loopNeed;
    }

    // Overriding the run method
    @Override
    public void run() {
        if (loopNeed) {
            // If looping is needed
            while(true){
                // Continuously check if the sound should stop
                if(isStop){
                    // If the sound is stopped, the thread keeps looping until it's resumed
                    while(isStop){
                        try {
                            Thread.sleep(10);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
                playSound(); // Play the sound
            }
        } else {
            // If looping is not needed, play the sound once
            playSound();
        }
    }

    // Method to play the sound
    public void playSound() {
        try {
            // Get the audio input stream from the provided URL
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            AudioFormat af = ais.getFormat(); // Get the format of the audio input stream
            SourceDataLine sdl = AudioSystem.getSourceDataLine(af); // Get a source data line that can be used to play audio data in the specified format
            sdl.open(af); // Open the data line with the specified format
            sdl.start(); // Allow the data line to perform I/O operations

            int nBytesRead; // Variable to hold the number of bytes read from the audio stream
            byte[] Data = new byte[1024]; // Array to hold audio data
            // Read audio data from the stream and write it to the data line for playback
            while ((nBytesRead = ais.read(Data, 0, Data.length)) != -1) {
                sdl.write(Data, 0, nBytesRead);
            }
            sdl.close(); // Close the data line after playback
        } catch (Exception e) {
            e.printStackTrace(); // Print any exceptions that occur during playback
        }
    }
}
