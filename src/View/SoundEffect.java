package View;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class SoundEffect {
    private Clip clip;
    private FloatControl volumeControl;

    public SoundEffect(String soundFilePath) {
        try {
            // Load the sound file as a resource
            InputStream audioInputStream = getClass().getResourceAsStream(soundFilePath);
            if (audioInputStream == null) {
                throw new FileNotFoundException("Sound file not found: " + soundFilePath);
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioInputStream);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Get the volume control
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.setFramePosition(0); // Rewind to the beginning
            clip.start();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void close() {
        if (clip != null) {
            clip.close();
        }
    }

    // Method to set volume
    public void setVolume(float volume) {
        if (volumeControl != null) {
            // Volume is in decibels, so we need to convert the volume value (0.0 to 1.0) to decibels
            float dB = (float) (20 * Math.log10(volume));
            volumeControl.setValue(dB);
        }
    }
}