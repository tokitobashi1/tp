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
     * Creates a {@code PersonCard} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;

        // Create view model to handle presentation logic
        PersonCardViewModel viewModel = new PersonCardViewModel(person, displayedIndex);

        // Set required fields
        id.setText(viewModel.getIdText());
        name.setText(viewModel.getNameText());
        phone.setText(viewModel.getPhoneText());

        // Set optional address field
        address.setText(viewModel.getAddressText());
        address.setVisible(viewModel.isAddressVisible());
        address.setManaged(viewModel.isAddressVisible());

        // Set optional email field
        email.setText(viewModel.getEmailText());
        email.setVisible(viewModel.isEmailVisible());
        email.setManaged(viewModel.isEmailVisible());

        // Set optional company field
        company.setText(viewModel.getCompanyText());
        company.setVisible(viewModel.isCompanyVisible());
        company.setManaged(viewModel.isCompanyVisible());

        // Set optional note field
        note.setText(viewModel.getNoteText());
        note.setVisible(viewModel.isNoteVisible());
        note.setManaged(viewModel.isNoteVisible());

        // Set tags
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
        return person.getNote() != null && !person.getNote().isEmpty()
                ? "Note: " + person.getNote().value
                : "";
    }

    static boolean isNoteVisible(Person person) {
        return person.getNote() != null && !person.getNote().isEmpty();
    }
}
