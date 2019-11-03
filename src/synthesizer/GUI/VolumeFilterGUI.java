package synthesizer.GUI;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import synthesizer.Filters.VolumeFilter;

public class VolumeFilterGUI extends WidgetBaseGUI {

    private Slider slider = new Slider();
    private VolumeFilter volumeFilter;

    public VolumeFilterGUI() {

        super(" Volume Filter ");
        widgetCellPane.setCenter(slider);
        volumeFilter = new VolumeFilter(slider.getValue());
        final Label sliderValue = new Label(
                Double.toString(slider.getValue()));
        slider.valueProperty().addListener((observableValue, oldVal, newVal) -> {
            //noinspection MalformedFormatString
            sliderValue.setText(String.format("%.2f", newVal));
        });
        widgetCellPane.setBottom(sliderValue);
        setUpSlider(slider, 0, 1, .25, .25, 5);

    }

    @Override
    public WidgetBaseGUI getWidgetBaseCopy(String name) {
        return new VolumeFilterGUI();
    }

    @Override
    public VolumeFilter getPureNonGUI() {
        return volumeFilter;
    }

    @Override
    public void updateBaseAudioComponent() {
        volumeFilter.updateAudioComponent(slider.getValue());
    }

}