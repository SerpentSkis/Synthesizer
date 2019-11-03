package synthesizer.Filters;

import synthesizer.components.AudioClip;
import synthesizer.components.AudioComponent;

/**
 * Scales the input to produce a quieter output.
 */
public class VolumeFilter implements AudioComponent {
    private AudioClip original;
    public AudioComponent input;

    private double scalarVolume;

    public VolumeFilter(double scalarVolume) {
        this.scalarVolume = scalarVolume;
    }

    @Override
    public AudioClip getClip() {

        original = input.getClip();

        AudioClip result = new AudioClip();
        for (int i = 0; i < AudioClip.TOTAL_SAMPLES; i++) {
            result.setSample(i, (short) (scalarVolume * original.getSample(i)));

            //clamping
            if (result.getSample(i) > Short.MAX_VALUE) {
                result.setSample(i, Short.MAX_VALUE);
            } else if (result.getSample(i) < Short.MIN_VALUE) {
                result.setSample(i, Short.MIN_VALUE);
            }

        }
        return result;
    }

    @Override
    public void connectInput(AudioComponent input) {
        this.input = input;
    }

    @Override
    public void updateAudioComponent(double parameter) {
        this.scalarVolume = parameter;
        original = this.getClip();

    }

}