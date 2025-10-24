package seedu.address.ui;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A UI for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    private static final String FXML = "StatusBarFooter.fxml";

    @FXML
    private Label saveLocationStatus;

    @FXML
    private Label copyrightLabel;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public StatusBarFooter(Path saveLocation) {
        super(FXML);
        setSaveLocation(saveLocation);
    }

    /**
     * Sets or updates the save file location text.
     */
    public void setSaveLocation(Path newSaveLocation) {
        saveLocationStatus.setText("Save file location: " + Paths.get(".").resolve(newSaveLocation).toString());
    }

    /**
     * Optionally set a custom message on the footer.
     */
    public void setCustomMessage(String message) {
        saveLocationStatus.setText(message);
    }
}
