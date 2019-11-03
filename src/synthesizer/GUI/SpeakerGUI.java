package synthesizer.GUI;

import synthesizer.components.Speaker;

public class SpeakerGUI extends WidgetBaseGUI {

    final private Speaker speaker = new Speaker();

    public SpeakerGUI() {

        super(" Speaker ");
        widgetCellPane.setRight(null);

    }

    @Override
    public Speaker getPureNonGUI() {
        return speaker;
    }

    @Override
    public void updateBaseAudioComponent() {

    }

    @Override
    public WidgetBaseGUI getWidgetBaseCopy(String name) {
        return this; //do not want more than one speaker
    }

}