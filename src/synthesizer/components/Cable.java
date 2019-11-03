package synthesizer.components;

import javafx.geometry.Point2D;
import synthesizer.GUI.WidgetBaseGUI;

/**
 * basic class that stores some info about inputs and outputs
 * there is currently only one cable object that is used to connect all widgets.
 */
public class Cable {

    public WidgetBaseGUI inputWidget;
    public WidgetBaseGUI outputWidget;
    public double cableX;
    public double cableY;
    public Point2D cableEnd;

    /**
     * connects the currently stored input and output widgets.
     */
    public void connect() {

        inputWidget.getPureNonGUI().connectInput(outputWidget.getPureNonGUI());
        System.out.println(inputWidget + "(input) is connected to (output)" + outputWidget);

    }

}
