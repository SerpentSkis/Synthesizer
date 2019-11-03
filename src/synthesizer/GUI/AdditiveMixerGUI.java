package synthesizer.GUI;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import synthesizer.Filters.AdditiveMixer;
import synthesizer.components.AudioComponent;
import synthesizer.components.Cable;

public class AdditiveMixerGUI extends WidgetBaseGUI {

    private AdditiveMixer additiveMixer = new AdditiveMixer();

    public AdditiveMixerGUI() {
        super(" Additive Mixer ");
    }

    @Override
    public WidgetBaseGUI getWidgetBaseCopy(String name) {
        return new AdditiveMixerGUI();
    }

    @Override
    public AudioComponent getPureNonGUI() {
        return additiveMixer;
    }

    @Override
    public void updateBaseAudioComponent() {

    }

    @Override
    public void drawCable(WidgetBaseGUI widgetBaseGUI, Cable cable, BorderPane mainPane, Pane tempCablePane, Pane cablePane, Boolean deleteOldCables) {
        super.drawCable(widgetBaseGUI, cable, mainPane, tempCablePane, cablePane, false);
    }

}
