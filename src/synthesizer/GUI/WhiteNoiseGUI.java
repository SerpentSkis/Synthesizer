package synthesizer.GUI;

import synthesizer.Waves.WhiteNoise;

public class WhiteNoiseGUI extends WidgetBaseGUI {

    private WhiteNoise WhiteNoise = new WhiteNoise();

    public WhiteNoiseGUI() {
        super(" White Noise ");
        widgetCellPane.setLeft(null);
    }

    @Override
    public WidgetBaseGUI getWidgetBaseCopy(String name) {
        return new WhiteNoiseGUI();
    }

    @Override
    public WhiteNoise getPureNonGUI() {
        return WhiteNoise;
    }

    @Override
    public void updateBaseAudioComponent() {

    }

}