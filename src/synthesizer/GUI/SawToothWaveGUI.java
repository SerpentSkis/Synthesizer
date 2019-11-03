package synthesizer.GUI;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import synthesizer.Waves.SawToothWave;

public class SawToothWaveGUI extends WidgetBaseGUI {

    private Slider slider = new Slider();
    private SawToothWave sawToothWave = new SawToothWave((int) slider.getValue());

    public SawToothWaveGUI() {

        super(" SawTooth Wave ");
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
        return new SawToothWaveGUI();
    }

    @Override
    public SawToothWave getPureNonGUI() {
        return sawToothWave;
    }

    @Override
    public void updateBaseAudioComponent() {
        sawToothWave.updateAudioComponent(slider.getValue());
    }

}