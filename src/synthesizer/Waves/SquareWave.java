package synthesizer.Waves;

import synthesizer.components.AudioClip;
import synthesizer.components.AudioComponent;

public class SquareWave implements AudioComponent {

    private AudioClip audioClip = new AudioClip();
    private int frequency;

    public SquareWave(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public AudioClip getClip() {
        for (int i = 0; i < AudioClip.DURATION * AudioClip.SAMPLE_RATE; i++) {
            if ((1.0 * frequency * i / AudioClip.SAMPLE_RATE) % 1 > 0.5) {
                audioClip.setSample(i, Short.MAX_VALUE);
            } else {
                audioClip.setSample(i, -Short.MAX_VALUE);
            }
        }
        return audioClip;
    }

    @Override
    public void connectInput(AudioComponent input) {

    }

    @Override
    public void updateAudioComponent(double parameter) {
        this.frequency = (int) parameter;
        audioClip = this.getClip();

    }
}