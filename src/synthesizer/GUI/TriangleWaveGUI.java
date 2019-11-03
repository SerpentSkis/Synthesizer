package synthesizer.GUI;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import synthesizer.Waves.SquareWave;
import synthesizer.Waves.TriangleWave;

public class TriangleWaveGUI extends WidgetBaseGUI {

    private Slider slider = new Slider();
    private TriangleWave triangleWave = new TriangleWave((int) slider.getValue());

    public TriangleWaveGUI() {

        super(" Triangle Wave ");
        widgetCellPane.setCenter(slider);
        widgetCellPane.setLeft(null);
        final Label sliderValue = new Label(
                Double.toString(slider.getValue()));

        slider.valueProperty().addListener((observableValue, oldVal, newVal) -> {
            //noinspection MalformedFormatString
            sliderValue.setText(String.format("%.2f", newVal));
        });
        widgetCellPane.setBottom(sliderValue);
        setUpSlider(slider, 20, 440 * 5 + 20, 440, 440, 10);

    }

    @Override
    public WidgetBaseGUI getWidgetBaseCopy(String name) {
        return new TriangleWaveGUI();
    }

    @Override
    public TriangleWave getPureNonGUI() {
        return triangleWave;
    }

    @Override
    public void updateBaseAudioComponent() {
        triangleWave.updateAudioComponent(slider.getValue());
    }

}