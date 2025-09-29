package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsAllContacts() {
        String expectedMessage = model.getFilteredPersonList().stream()
                .map(person -> formatPerson(person, model))
                .collect(Collectors.joining("\n"));

        assertCommandSuccess(new ListCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsAllContacts() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // Build expected message using the full list (like ListCommand will do)
        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        String expectedMessage = expectedModel.getFilteredPersonList().stream()
                .map(person -> formatPerson(person, expectedModel))
                .collect(Collectors.joining("\n"));

        assertCommandSuccess(new ListCommand(), model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_emptyAddressBook_showsNoContactsStored() {
        model = new ModelManager();
        expectedModel = new ModelManager();

        ListCommand listCommand = new ListCommand();
        String expectedMessage = "No contacts stored";

        assertCommandSuccess(listCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Formats a person using the filtered list from the model to ensure correct numbering.
     */
    private String formatPerson(Person person, Model model) {
        int index = model.getFilteredPersonList().indexOf(person) + 1;
        return String.format("%d. %s | %s | [%s] | [%s] | %s",
                index,
                person.getName(),
                person.getPhone(),
                person.getEmail(),
                person.getCompany(),
                person.getTags());
    }
}
