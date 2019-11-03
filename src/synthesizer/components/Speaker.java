package synthesizer.components;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Speaker implements AudioComponent {
    public AudioComponent input;

    /**
     * Plays the sound of the speakers input.
     */
    public void playSound() {

        //get properties from the system about samples rates, etc
        Clip c = null;
        try {
            c = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            System.out.println("no speaker input");
            System.out.println("error 1, AudioSystem.getClip");
            e.printStackTrace();
        }

        //This is the format that we're following, 44.1KHz mono audio, 16 bits per sample
        AudioFormat format16 = new AudioFormat(44100, 16, 1, true, false);

        AudioComponent gen = input;
        AudioClip clip = gen.getClip();
        try {
            if (c != null) {
                c.open(format16, clip.getData(), 0, clip.getData().length); //reads data from my byte array to play it
            }
        } catch (LineUnavailableException e) {
            System.out.println("no speaker input");
            System.out.println("error 2, c.open");
            e.printStackTrace();
        }


        if (c != null) {
            c.start(); //plays it
        }
        if (c != null) {
            c.loop(3); //additional loops
        }
        System.out.println("sound played");
    }

    @Override
    public AudioClip getClip() {
        System.out.println("speaker get clip was called, this shouldn't happen. ");
        return null;
    }

    @Override
    public void connectInput(AudioComponent input) {
        this.input = input;
    }

    @Override
    public void updateAudioComponent(double parameter) {
    }

}