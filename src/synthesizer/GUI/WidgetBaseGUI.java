package synthesizer.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import synthesizer.components.AudioComponent;
import synthesizer.components.Cable;

public abstract class WidgetBaseGUI {

    final private Circle outputJack;
    final private Circle inputJack;
    public BorderPane widgetCellPane = new BorderPane();

    /**
     * Sets up the visual window for any widget.
     * Includes dragging
     *
     * @param title title to be shown at top of pane.
     */
    WidgetBaseGUI(String title) {

        final Text text = new Text();
        text.setText(title);
        widgetCellPane.setTop(text);
        widgetCellPane.setStyle("-fx-border-style: solid; -fx-border-width: 3px; -fx-border-color: #000000");
        final BackgroundFill backgroundFill = new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, Insets.EMPTY);
        final Background background = new Background(backgroundFill);
        widgetCellPane.setBackground(background);

        inputJack = new Circle(10, Color.BLUE);
        outputJack = new Circle(10, Color.LIMEGREEN);
        widgetCellPane.setRight(outputJack);
        widgetCellPane.setLeft(inputJack);


        //dragging widgetCellPane
        //TODO make cables follow dragged items.
        var ref = new Object() {
            double mouseX;
            double mouseY;
        };

        widgetCellPane.setOnMousePressed(mouseEvent -> {
                    ref.mouseX = mouseEvent.getX();
                    ref.mouseY = mouseEvent.getY();
                }
        );

        widgetCellPane.setOnMouseDragged(mouseEvent -> {
            double xMove = mouseEvent.getX() - ref.mouseX;
            double yMove = mouseEvent.getY() - ref.mouseY;
            if (!outputJack.isPressed()) {
                widgetCellPane.relocate(widgetCellPane.getLayoutX() + xMove, widgetCellPane.getLayoutY() + yMove);

            }
        });

    }

    /**
     * @param widgetBaseGUI each widget base currently has one input cable visual, except for mixers that have more.
     * @param cable         the single cable that connects all inputs
     * @param mainPane      mainPane
     * @param tempCablePane the cablePane that the temporary draggable cable is displayed in.
     *                      no functionality, just display.
     * @param cablePane     Where the persistent cables are drawn
     */
    public void drawCable(WidgetBaseGUI widgetBaseGUI, Cable cable, BorderPane mainPane, Pane tempCablePane, Pane cablePane, Boolean deleteOldCables) {

        //TODO make it so you dont have to click again to connect the jacks after its dragged.
        widgetBaseGUI.getOutputJack().setOnMousePressed(mouseEvent -> {
            tempCablePane.getChildren().clear();
            cable.outputWidget = widgetBaseGUI;
        });

        //dragging part
        widgetBaseGUI.getOutputJack().setOnMouseDragged(mouseEvent -> {

            tempCablePane.getChildren().clear();
            Point2D outputJack = widgetBaseGUI.getOutputJack().localToScene(widgetBaseGUI.getOutputJack().getCenterX(), widgetBaseGUI.getOutputJack().getCenterY());
            Line line = new Line(outputJack.getX(), outputJack.getY(), mouseEvent.getSceneX(), mouseEvent.getSceneY());
            tempCablePane.getChildren().add(line);
            tempCablePane.setPickOnBounds(false);

        });

        widgetBaseGUI.getOutputJack().setOnMouseReleased(mouseDragEvent -> {
            System.out.println("output jack mouse released");
            cable.cableX = mouseDragEvent.getSceneX();
            cable.cableY = mouseDragEvent.getSceneY();
            cable.cableEnd = new Point2D(mouseDragEvent.getSceneX(), mouseDragEvent.getSceneY());


        });


        //persistent part
        widgetBaseGUI.getInputJack().setOnMousePressed(mouseEvent -> {
            System.out.println("input jack pressed");
            System.out.println(cable.cableX);
            System.out.println(cable.cableY);

            if (widgetBaseGUI.getInputJack().localToScene(getInputJack().getLayoutBounds()).contains(cable.cableEnd)) {
                tempCablePane.getChildren().clear();
                System.out.println("input jack has cable in it");
                cable.inputWidget = widgetBaseGUI;
                cable.connect();
                Point2D cableStart = cable.inputWidget.inputJack.localToScene(inputJack.getCenterX(), inputJack.getCenterY());
                Point2D cableEnd = cable.outputWidget.outputJack.localToScene(outputJack.getCenterX(), outputJack.getCenterY());

                Line line = new Line(
                        cableStart.getX(),
                        cableStart.getY(),
                        cableEnd.getX(),
                        cableEnd.getY()
                );

                if (deleteOldCables) {
                    cablePane.getChildren().clear();
                }
                cablePane.setPickOnBounds(false);
                System.out.println(line);
                cablePane.getChildren().add(line);
                mainPane.getChildren().remove(cablePane);
                mainPane.getChildren().add(cablePane);

            }
        });
    }

    /**
     * @param name name
     * @return a copy of the widgetBaseGUI with the given name
     */
    abstract public WidgetBaseGUI getWidgetBaseCopy(String name);

    private Circle getOutputJack() {
        return outputJack;
    }

    private Circle getInputJack() {
        return inputJack;
    }

    /**
     * Applied to a widgetGUI
     *
     * @return the corresponding nonGUI widget.
     */
    abstract public AudioComponent getPureNonGUI();

    /**
     * updates the value of the base audio component. Used to make the sliders work properly.
     */
    abstract public void updateBaseAudioComponent();


    void setUpSlider(Slider slider, int min, int max, double startingValue, double majorTickUnits, int minorTickCount) {
        slider.setMin(min);
        slider.setMax(max);
        slider.setValue(startingValue);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(majorTickUnits);
        slider.setMinorTickCount(minorTickCount);
        slider.setBlockIncrement(1);
        slider.setMinSize(300, 20);
    }

}
