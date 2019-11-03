package synthesizer.GUI;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import synthesizer.Filters.MultiplicativeMixer;
import synthesizer.components.AudioComponent;
import synthesizer.components.Cable;

public class MultiplicativeMixerGUI extends WidgetBaseGUI {

    private MultiplicativeMixer multiplicativeMixer = new MultiplicativeMixer();

    public MultiplicativeMixerGUI() {
        super(" Multiplicative Mixer ");
    }

    @Override
    public WidgetBaseGUI getWidgetBaseCopy(String name) {
        return new MultiplicativeMixerGUI();
    }

    @Override
    public AudioComponent getPureNonGUI() {
        return multiplicativeMixer;
    }

    @Override
    public void updateBaseAudioComponent() {

    }

    @Override
    public void drawCable(WidgetBaseGUI widgetBaseGUI, Cable cable, BorderPane mainPane, Pane tempCablePane, Pane cablePane, Boolean deleteOldCables) {
        super.drawCable(widgetBaseGUI, cable, mainPane, tempCablePane, cablePane, false);
    }

}
