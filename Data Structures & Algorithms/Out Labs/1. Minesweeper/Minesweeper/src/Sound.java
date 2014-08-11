/**
 *
 * @author Gunnar and Josh
 */

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

public class Sound {
    
    
        public Sound() {

        }
        
        public void playExplosion()
        {
            try{
                // Open an audio input stream.
                 URL url = this.getClass().getClassLoader().getResource("battle003.wav");
                 AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                 // Get a sound clip resource.
                 Clip clip = AudioSystem.getClip();
                 // Open audio clip and load samples from the audio input stream.
                 clip.open(audioIn);
                 clip.start();
            } 
            catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } 
            catch (IOException e) {
                e.printStackTrace();
            } 
            catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }
        
        public void playClick()
        {
            try{
                // Open an audio input stream.
                 URL url = this.getClass().getClassLoader().getResource("misc003.wav");
                 AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                 // Get a sound clip resource.
                 Clip clip = AudioSystem.getClip();
                 // Open audio clip and load samples from the audio input stream.
                 clip.open(audioIn);
                 clip.start();
            } 
            catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } 
            catch (IOException e) {
                e.printStackTrace();
            } 
            catch (LineUnavailableException e) {
                e.printStackTrace();
            }            
        }
        
        public void playGong()
        {
            try{
                // Open an audio input stream.
                 URL url = this.getClass().getClassLoader().getResource("musical047.wav");
                 AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                 // Get a sound clip resource.
                 Clip clip = AudioSystem.getClip();
                 // Open audio clip and load samples from the audio input stream.
                 clip.open(audioIn);
                 clip.start();
            } 
            catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } 
            catch (IOException e) {
                e.printStackTrace();
            } 
            catch (LineUnavailableException e) {
                e.printStackTrace();
            }            
        }


}
