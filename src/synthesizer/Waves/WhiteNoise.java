package synthesizer.Waves;

import synthesizer.components.AudioClip;
import synthesizer.components.AudioComponent;


public class WhiteNoise implements AudioComponent {

    private AudioClip audioClip = new AudioClip();

    public WhiteNoise() {
    }

    @Override
    public AudioClip getClip() {
        for (int i = 0; i < AudioClip.DURATION * AudioClip.SAMPLE_RATE; i++) {
            audioClip.setSample(i, (int) (Math.random() * (Short.MAX_VALUE - Short.MIN_VALUE) + Short.MIN_VALUE));
        }
        return audioClip;
    }

    @Override
    public void connectInput(AudioComponent input) {
    }

    @Override
    public void updateAudioComponent(double parameter) {
        audioClip = this.getClip();
    }
}