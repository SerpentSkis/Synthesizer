package synthesizer.GUI;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import synthesizer.Waves.SineWave;

public class SineWaveGUI extends WidgetBaseGUI {

    private Slider slider = new Slider();
    private SineWave sineWave = new SineWave((int) slider.getValue());

    public SineWaveGUI() {

        super(" Sine Wave ");
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
        return new SineWaveGUI();
    }

    @Override
    public SineWave getPureNonGUI() {
        return sineWave;
    }

    @Override
    public void updateBaseAudioComponent() {
        sineWave.updateAudioComponent(getSlider().getValue());
    }

    private Slider getSlider() {
        return slider;
    }


}
