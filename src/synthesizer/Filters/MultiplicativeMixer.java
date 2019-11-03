package synthesizer.Filters;

import synthesizer.components.AudioClip;
import synthesizer.components.AudioComponent;

/**
 * Outputs the geometric average of the frequencies of the connected inputs.
 * Currently supports two inputs.
 */
public class MultiplicativeMixer implements AudioComponent {
    private int numInputs;
    private AudioComponent input0;
    private AudioComponent input1;

    @Override
    public AudioClip getClip() {

        AudioClip result = new AudioClip();

        if (numInputs > 0) {
            AudioClip original0 = input0.getClip();
            if (numInputs > 1) {
                AudioClip original1 = input1.getClip();

                //geometric average
                for (int i = 0; i < AudioClip.TOTAL_SAMPLES; i++) {
                    result.setSample(i, (int) Math.sqrt((1.0 * original0.getSample(i) * original1.getSample(i))));

                }
            } else {
                return original0;
            }
            System.out.println("there are " + numInputs + " inputs");
            return result;
        }
        return null;
    }

    @Override
    public void connectInput(AudioComponent input) {
        //TODO have this work with any number of inputs
        if (numInputs % 2 == 0) {
            this.input0 = input;
        } else if (numInputs % 2 == 1) {
            this.input1 = input;
        }
        numInputs++;

    }

    @Override
    public void updateAudioComponent(double parameter) {
    }

}