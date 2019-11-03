package synthesizer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import synthesizer.GUI.*;
import synthesizer.components.Cable;

import java.util.ArrayList;

public class Main extends Application {

    //widget declaration
    final private SpeakerGUI speakerGUI = new SpeakerGUI();


    //TODO add more widgets/features
    final private SineWaveGUI sineWaveGUI = new SineWaveGUI();
    final private SquareWaveGUI squareWaveGUI = new SquareWaveGUI();
    final private TriangleWaveGUI triangleWaveGUI = new TriangleWaveGUI();
    final private SawToothWaveGUI sawToothWaveGUI = new SawToothWaveGUI();
    final private WhiteNoiseGUI whiteNoiseGUI = new WhiteNoiseGUI();
    final private VolumeFilterGUI volumeFilterGUI = new VolumeFilterGUI();
    final private AdditiveMixerGUI additiveMixerGUI = new AdditiveMixerGUI();
    final private MultiplicativeMixerGUI multiplicativeMixerGUI = new MultiplicativeMixerGUI();

    final private Cable cable = new Cable();
    final private ArrayList<WidgetBaseGUI> widgetBaseGUIS = new ArrayList<>(15);
    final private Pane tempCablePane = new Pane();
    final private BorderPane mainPane = new BorderPane();
    final private Pane widgetPane = new Pane();

    //main method

    public void start(@NotNull Stage primaryStage) {

        setUpStage(primaryStage, mainPane);

        setUpPlayButton(mainPane);

        setUpWidgetButtons(mainPane, widgetPane);

    }

    /**
     * @param primaryStage primaryStage
     * @param mainPane     mainPane
     */
    private void setUpStage(@NotNull Stage primaryStage, BorderPane mainPane) {
        mainPane.setCenter(widgetPane);
        mainPane.getChildren().add(tempCablePane);
        final Scene scene = new Scene(mainPane, 1920, 600);
        primaryStage.setTitle("Synthesizer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * sets up play button, updates all values based on sliders.
     *
     * @param mainPane mainPane
     */
    private void setUpPlayButton(BorderPane mainPane) {
        final Button playButton = new Button();
        playButton.setText("Play");
        mainPane.setLeft(playButton);

        playButton.setOnMousePressed(mouseEvent -> {
            for (WidgetBaseGUI w : widgetBaseGUIS) {
                w.updateBaseAudioComponent();
            }
            speakerGUI.getPureNonGUI().playSound();
        });
    }

    /**
     * @param mainPane   mainPane that everything lives in
     * @param widgetPane Pane that each individual widgetGUI lives
     */
    private void setUpWidgetButtons(BorderPane mainPane, Pane widgetPane) {

        final VBox buttonBox = new VBox();
        mainPane.setRight(buttonBox);
        generatingButtons(buttonBox, widgetPane, "Speaker", speakerGUI, mainPane, true);
        generatingButtons(buttonBox, widgetPane, "Sine Wave", sineWaveGUI, mainPane, true);
        generatingButtons(buttonBox, widgetPane, "Square Wave", squareWaveGUI, mainPane, true);
        generatingButtons(buttonBox, widgetPane, "Triangle Wave", triangleWaveGUI, mainPane, true);
        generatingButtons(buttonBox, widgetPane, "Saw Tooth Wave", sawToothWaveGUI, mainPane, true);
        generatingButtons(buttonBox, widgetPane, "White Noise", whiteNoiseGUI, mainPane, true);
        generatingButtons(buttonBox, widgetPane, "Volume Filter", volumeFilterGUI, mainPane, true);
        generatingButtons(buttonBox, widgetPane, "Additive Mixer", additiveMixerGUI, mainPane, false);
        generatingButtons(buttonBox, widgetPane, "Multiplicative Mixer", multiplicativeMixerGUI, mainPane, false);

    }

    /**
     * @param buttonBox       VBox that the generating buttons live
     * @param widgetPane      Pane that each individual widgetGUI lives
     * @param widgetName      widgetName
     * @param widgetBaseGUI   individual widgetBaseGUI
     * @param mainPane        mainPane
     * @param deleteOldCables removes lines for cables that are disconnected.
     *                        This distinction is so that mixers can have more than one input shown.
     *                        //TODO make disconnected lines disappear correctly
     */
    private void generatingButtons(
            VBox buttonBox, Pane widgetPane, String widgetName, WidgetBaseGUI widgetBaseGUI, BorderPane mainPane, Boolean deleteOldCables) {

        //TODO make them not spawn on top of each other.
        final Button widgetGeneratingButton = new Button();
        widgetGeneratingButton.setText(widgetName);
        buttonBox.getChildren().add(widgetGeneratingButton);

        widgetGeneratingButton.setOnAction(actionEvent -> {

            final Pane cablePane = new Pane();
            final WidgetBaseGUI widgetBaseGUI1 = widgetBaseGUI.getWidgetBaseCopy(widgetName);
            widgetBaseGUIS.add(widgetBaseGUI1);
            mainPane.getChildren().addAll(cablePane);
            widgetPane.getChildren().add(widgetBaseGUI1.widgetCellPane);
            widgetBaseGUI1.drawCable(widgetBaseGUI1, cable, mainPane, tempCablePane, cablePane, deleteOldCables);

        });

    }

}