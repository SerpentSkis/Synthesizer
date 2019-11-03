package synthesizer.components;

public interface AudioComponent {

    /**
     * @return return the current sound produced by this component.
     */
    AudioClip getClip();

    /**
     * connect another device to this input.
     * @param input the AudioComponent of a different device.
     */
    void connectInput(AudioComponent input);

    /**
     *
     * @param parameter the value to set this audioComponent to. Could be volume, frequency, etc..
     */
    void updateAudioComponent(double parameter);

}