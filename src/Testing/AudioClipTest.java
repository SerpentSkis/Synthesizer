package Testing;

import synthesizer.components.AudioClip;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AudioClipTest {

    private AudioClip audioClip = new AudioClip();

    @org.junit.jupiter.api.Test
    void getAndSetTesting() {
        audioClip.data = new byte[AudioClip.DURATION * AudioClip.SAMPLE_RATE * 2 * 2]; //makes the byte[] bigger so I can store all possible values

        //test all possible values
        int j = Short.MIN_VALUE;
        for (int i = 0; i < Short.MAX_VALUE - Short.MIN_VALUE; i++) {
            audioClip.setSample(i, j);
            j++;
            //System.out.println(audioClip.getSample(i));
        }
        System.out.println(Arrays.toString(audioClip.getData()));

        j = Short.MIN_VALUE;
        for (int i = 0; i < Short.MAX_VALUE - Short.MIN_VALUE; i++) {
            assertEquals(j, audioClip.getSample(i));
            j++;
        }

        audioClip.setSample(0, 255);
        audioClip.setSample(1, 512);
        audioClip.setSample(2, 256);
        audioClip.setSample(3, 32767);
        audioClip.setSample(4, 12345);
        audioClip.setSample(5, 15);

        assertEquals(255, audioClip.getSample(0));
        assertEquals(512, audioClip.getSample(1));
        assertEquals(256, audioClip.getSample(2));
        assertEquals(32767, audioClip.getSample(3));
        assertEquals(12345, audioClip.getSample(4));
        assertEquals(15, audioClip.getSample(5));
        System.out.println(Arrays.toString(audioClip.getData()));

    }
//TODO think of more testing.
}