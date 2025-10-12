package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
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
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);

        // Address (optional)
        address.setText(getAddressText(person));
        address.setVisible(isAddressVisible(person));
        address.setManaged(isAddressVisible(person));

        // Email (optional)
        email.setText(getEmailText(person));
        email.setVisible(isEmailVisible(person));
        email.setManaged(isEmailVisible(person));

        // Company (optional)
        company.setText(getCompanyText(person));
        company.setVisible(isCompanyVisible(person));
        company.setManaged(isCompanyVisible(person));

        // Note (optional)
        note.setText(getNoteText(person));
        note.setVisible(isNoteVisible(person));
        note.setManaged(isNoteVisible(person));

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

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
        return person.getNote() != null ? "Note: " + person.getNote().value : "";
    }

    static boolean isNoteVisible(Person person) {
        return person.getNote() != null && !person.getNote().value.equals("-");
    }
}
