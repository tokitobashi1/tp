package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label company;
    @FXML
    private Label note;
    @FXML
    private Label noteTimestamp;
    @FXML
    private Button showNoteTimeButton;
    @FXML
    private FlowPane tags;

    /**
    * Creates a {@code PersonCard} with the given {@code Person} to display and
    * the displayed index in the list.
    *
    * @param person The person whose information is to be displayed.
    * @param displayedIndex The index to display alongside the person in the UI list.
    */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;

        PersonCardViewModel viewModel = new PersonCardViewModel(person, displayedIndex);

        id.setText(viewModel.getIdText());
        name.setText(viewModel.getNameText());
        phone.setText(viewModel.getPhoneText());

        // Address
        address.setText(viewModel.getAddressText());
        address.setVisible(viewModel.isAddressVisible());
        address.setManaged(viewModel.isAddressVisible());

        // Email
        email.setText(viewModel.getEmailText());
        email.setVisible(viewModel.isEmailVisible());
        email.setManaged(viewModel.isEmailVisible());

        // Company
        company.setText(viewModel.getCompanyText());
        company.setVisible(viewModel.isCompanyVisible());
        company.setManaged(viewModel.isCompanyVisible());

        // Note
        note.setText(viewModel.getNoteText());
        boolean hasNote = viewModel.isNoteVisible();
        note.setVisible(hasNote);
        note.setManaged(hasNote);

        // Note timestamp (hidden by default)
        boolean hasTimestamp = person.getNote() != null
                && !person.getNote().isEmpty()
                && person.getNote().getLastEdited() != null;
        if (hasTimestamp) {
            noteTimestamp.setText("Last edited: " + person.getNote().getFormattedLastEdited());
        } else {
            noteTimestamp.setText(""); // no note timestamp
        }
        noteTimestamp.setVisible(false);
        noteTimestamp.setManaged(false);

        // Show Note Button: always present but semi-hidden if no note
        showNoteTimeButton.setOpacity(0); // hidden by default
        showNoteTimeButton.setMouseTransparent(true); // cannot be clicked if opacity 0
        if (hasNote) {
            showNoteTimeButton.setOpacity(1); // fully visible if there’s a note
            showNoteTimeButton.setMouseTransparent(false);
        }

        // Show button on hover
        cardPane.setOnMouseEntered(e -> showNoteTimeButton.setOpacity(1));
        cardPane.setOnMouseExited(e -> {
            if (!hasNote) {
                showNoteTimeButton.setOpacity(0);
            }
        });

        // Tags
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Handles showing or hiding the note timestamp when the user clicks the button.
     */
    @FXML
    void handleShowNoteTime() {
        if (note.getText().isEmpty()) {
            return; // do nothing if no note
        }
        boolean visible = !noteTimestamp.isVisible();
        noteTimestamp.setVisible(visible);
        noteTimestamp.setManaged(visible);
    }

    // Helpers (unchanged)
    static String getAddressText(Person person) {
        return person.getAddress() != null ? person.getAddress().value : "";
    }

    static boolean isAddressVisible(Person person) {
        return person.getAddress() != null;
    }

    static String getEmailText(Person person) {
        return person.getEmail() != null ? person.getEmail().value : "";
    }

    static boolean isEmailVisible(Person person) {
        return person.getEmail() != null;
    }

    static String getCompanyText(Person person) {
        return person.getCompany() != null ? person.getCompany().value : "";
    }

    static boolean isCompanyVisible(Person person) {
        return person.getCompany() != null;
    }

    static String getNoteText(Person person) {
        return person.getNote() != null && !person.getNote().isEmpty()
                ? "Note: " + person.getNote().value
                : "";
    }

    static boolean isNoteVisible(Person person) {
        return person.getNote() != null && !person.getNote().isEmpty();
    }

    // For testing only
    Label getNoteTimestampForTest() {
        return noteTimestamp;
    }

    void callHandleShowNoteTimeForTest() {
        handleShowNoteTime();
    }
}
