package synthesizer.Waves;

import synthesizer.components.AudioClip;
import synthesizer.components.AudioComponent;

public class SineWave implements AudioComponent {

    private AudioClip audioClip = new AudioClip();
    private int frequency;

    public SineWave(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public AudioClip getClip() {
        for (int i = 0; i < AudioClip.DURATION * AudioClip.SAMPLE_RATE; i++) {
            audioClip.setSample(i, (int) (Short.MAX_VALUE * Math.sin(2 * Math.PI * frequency * i / AudioClip.SAMPLE_RATE)));
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