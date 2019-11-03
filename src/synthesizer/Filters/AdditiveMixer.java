package synthesizer.Filters;

import synthesizer.components.AudioClip;
import synthesizer.components.AudioComponent;

/**
 * Outputs the sum of the frequencies of the connected inputs.
 * Currently supports two inputs.
 */
public class AdditiveMixer implements AudioComponent {
    private int numInputs;
    private AudioComponent input0;
    private AudioComponent input1;

    @Override
    public AudioClip getClip() {

        AudioClip original0;
        AudioClip original1 = new AudioClip();
        AudioClip result = new AudioClip();
        double NUM_INPUTS = numInputs;

        if (numInputs > 0) {
            original0 = input0.getClip();
            if (numInputs > 1) {
                original1 = input1.getClip();
            }
            for (int i = 0; i < AudioClip.TOTAL_SAMPLES; i++) {
                result.setSample(i, (int) (1.0 * (original0.getSample(i) + original1.getSample(i)) / NUM_INPUTS));

            }
        }
        System.out.println(input0 + " was added to " + input1);
        return result;
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