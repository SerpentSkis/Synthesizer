package synthesizer.GUI;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import synthesizer.Waves.SquareWave;

public class SquareWaveGUI extends WidgetBaseGUI {

    private Slider slider = new Slider();
    private SquareWave squareWave = new SquareWave((int) slider.getValue());

    public SquareWaveGUI() {

        super(" Square Wave ");
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
        return new SquareWaveGUI();
    }

    @Override
    public SquareWave getPureNonGUI() {
        return squareWave;
    }

    @Override
    public void updateBaseAudioComponent() {
        squareWave.updateAudioComponent(slider.getValue());
    }

}