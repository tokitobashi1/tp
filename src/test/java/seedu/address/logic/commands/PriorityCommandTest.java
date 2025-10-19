package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Priority;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests and unit tests for PriorityCommand.
 */
public class PriorityCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_setPriorityUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withPriority("HIGH").build();

        PriorityCommand priorityCommand = new PriorityCommand(INDEX_FIRST_PERSON, new Priority("HIGH"));

        String expectedMessage = String.format(PriorityCommand.MESSAGE_SET_PRIORITY_SUCCESS,
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(priorityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_setPriorityFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withPriority("MEDIUM").build();

        PriorityCommand priorityCommand = new PriorityCommand(INDEX_FIRST_PERSON, new Priority("MEDIUM"));

        String expectedMessage = String.format(PriorityCommand.MESSAGE_SET_PRIORITY_SUCCESS,
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(priorityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        PriorityCommand priorityCommand = new PriorityCommand(outOfBoundIndex, new Priority("HIGH"));

        assertCommandFailure(priorityCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        PriorityCommand priorityCommand = new PriorityCommand(outOfBoundIndex, new Priority("HIGH"));

        assertCommandFailure(priorityCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final PriorityCommand standardCommand = new PriorityCommand(INDEX_FIRST_PERSON, new Priority("HIGH"));

        // same values -> returns true
        PriorityCommand commandWithSameValues = new PriorityCommand(INDEX_FIRST_PERSON, new Priority("HIGH"));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand(false)));

        // different index -> returns false
        assertFalse(standardCommand.equals(new PriorityCommand(INDEX_SECOND_PERSON, new Priority("HIGH"))));

        // different priority -> returns false
        assertFalse(standardCommand.equals(new PriorityCommand(INDEX_FIRST_PERSON, new Priority("LOW"))));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        Priority priority = new Priority("HIGH");
        PriorityCommand priorityCommand = new PriorityCommand(index, priority);
        String expected = PriorityCommand.class.getCanonicalName()
                + "{index=" + index + ", priority=" + priority + "}";
        assertEquals(expected, priorityCommand.toString());
    }

    @Test
    public void execute_setPriorityWithNumericValue_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withPriority("1").build();

        PriorityCommand priorityCommand = new PriorityCommand(INDEX_FIRST_PERSON, new Priority("1"));

        String expectedMessage = String.format(PriorityCommand.MESSAGE_SET_PRIORITY_SUCCESS,
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(priorityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_changePriorityOfPersonWithExistingPriority_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person personWithPriority = new PersonBuilder(firstPerson).withPriority("HIGH").build();
        model.setPerson(firstPerson, personWithPriority);

        Person editedPerson = new PersonBuilder(personWithPriority).withPriority("LOW").build();

        PriorityCommand priorityCommand = new PriorityCommand(INDEX_FIRST_PERSON, new Priority("LOW"));

        String expectedMessage = String.format(PriorityCommand.MESSAGE_SET_PRIORITY_SUCCESS,
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personWithPriority, editedPerson);

        assertCommandSuccess(priorityCommand, model, expectedMessage, expectedModel);
    }
}
