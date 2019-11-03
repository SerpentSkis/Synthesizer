package synthesizer.Waves;

import synthesizer.components.AudioClip;
import synthesizer.components.AudioComponent;

public class SawToothWave implements AudioComponent {

    private AudioClip audioClip = new AudioClip();
    private int frequency;

    public SawToothWave(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public AudioClip getClip() {
        for (int i = 0; i < AudioClip.DURATION * AudioClip.SAMPLE_RATE; i++) {
            double x = 1.0 * i * frequency / AudioClip.SAMPLE_RATE;
            audioClip.setSample(i, (int) (Short.MAX_VALUE * (x - Math.floor(x))));
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