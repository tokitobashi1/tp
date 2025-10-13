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
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests and unit tests for NoteCommand.
 */
public class NoteCommandTest {

    private static final String NOTE_STUB = "Some note";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addNoteUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        // Single Note instance
        Note note = new Note(NOTE_STUB);
        Person editedPerson = new PersonBuilder(firstPerson).withNoteObject(note).build();

        NoteCommand noteCommand = new NoteCommand(INDEX_FIRST_PERSON, note);

        // Only test the person name in the success message
        String expectedMessage = String.format(NoteCommand.MESSAGE_ADD_NOTE_SUCCESS,
            Messages.format(editedPerson) + " [" + editedPerson.getNote().toDisplayString() + "]");
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteNoteUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        // Clearing note
        Note note = new Note("-");
        Person editedPerson = new PersonBuilder(firstPerson).withNoteObject(note).build();
        NoteCommand noteCommand = new NoteCommand(INDEX_FIRST_PERSON, note);

        String expectedMessage = String.format(NoteCommand.MESSAGE_DELETE_NOTE_SUCCESS,
                Messages.format(editedPerson) + " [" + editedPerson.getNote().toDisplayString() + "]");


        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Note note = new Note(NOTE_STUB);
        Person editedPerson = new PersonBuilder(firstPerson).withNoteObject(note).build();
        NoteCommand noteCommand = new NoteCommand(INDEX_FIRST_PERSON, note);

        String expectedMessage = String.format(NoteCommand.MESSAGE_ADD_NOTE_SUCCESS,
                Messages.format(editedPerson) + " [" + editedPerson.getNote().toDisplayString() + "]");


        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Person personInExpectedModel = expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        expectedModel.setPerson(personInExpectedModel, editedPerson);

        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        NoteCommand noteCommand = new NoteCommand(outOfBoundIndex, new Note(NOTE_STUB));

        assertCommandFailure(noteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        NoteCommand noteCommand = new NoteCommand(outOfBoundIndex, new Note(NOTE_STUB));

        assertCommandFailure(noteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final NoteCommand standardCommand = new NoteCommand(INDEX_FIRST_PERSON, new Note(NOTE_STUB));

        // same values -> returns true
        NoteCommand commandWithSameValues = new NoteCommand(INDEX_FIRST_PERSON, new Note(NOTE_STUB));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new NoteCommand(INDEX_SECOND_PERSON, new Note(NOTE_STUB))));

        // different note -> returns false
        assertFalse(standardCommand.equals(new NoteCommand(INDEX_FIRST_PERSON, new Note("Different note"))));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        Note note = new Note(NOTE_STUB);
        NoteCommand noteCommand = new NoteCommand(index, note);
        String expected = NoteCommand.class.getCanonicalName() + "{index=" + index + ", note=" + note + "}";
        assertEquals(expected, noteCommand.toString());
    }
}
