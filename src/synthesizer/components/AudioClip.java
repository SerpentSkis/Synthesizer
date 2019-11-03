package synthesizer.components;

public class AudioClip {

    public static final int DURATION = 1;
    public static final int SAMPLE_RATE = 44100;
    public static final int TOTAL_SAMPLES = DURATION * SAMPLE_RATE;
    public byte[] data = new byte[DURATION * SAMPLE_RATE * 2];

    /**
     * @param index index of the first byte in the data that is two bytes long.
     * @return returns the short sample
     */
    final public int getSample(int index) {
        int msb = (data[2 * index + 1]) << 8;
        int lsb = (data[2 * index] & 0x00ff);
        return (msb | lsb);  // little endian
    }

    /**
     *
     * @param index index of the Short sample in the data
     * @param value value of the Short sample
     */
    final public void setSample(int index, int value) {
        data[2 * index] = (byte) value;  //LSB
        data[2 * index + 1] = (byte) (value >>> 8);  //MSB
    }

    /**
     * @return a copy of the byte[] that makes up this sound clip.
     */
    final public byte[] getData() {
        AudioClip original = this;
        AudioClip result;
        result = original;
        return result.data;
    }

}