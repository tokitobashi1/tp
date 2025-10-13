package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NoteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Note(null));
    }

    @Test
    public void constructor_invalidNote_throwsIllegalArgumentException() {
        String invalidNote = "";
        assertThrows(IllegalArgumentException.class, () -> new Note(invalidNote));
    }

    @Test
    public void isValidNote() {
        // null note
        assertThrows(NullPointerException.class, () -> Note.isValidNote(null));

        // invalid notes
        assertFalse(Note.isValidNote("")); // empty string
        assertFalse(Note.isValidNote(" ")); // spaces only

        // valid notes
        assertTrue(Note.isValidNote("Prefers email")); // alphabets only
        assertTrue(Note.isValidNote("Available on weekends")); // with spaces
        assertTrue(Note.isValidNote("Call between 9-5pm")); // alphanumeric with special chars
        assertTrue(Note.isValidNote("-")); // placeholder for empty note
        assertTrue(Note.isValidNote("VIP client! Handle with care.")); // with punctuation
        assertTrue(Note.isValidNote("12345")); // numbers only
        assertTrue(Note.isValidNote("Client requested callback on 15/03/2024 at 3:00 PM. "
                + "Discussed project requirements.")); // long note
    }

    @Test
    public void equals() {
        Note note = new Note("Valid Note");

        // same values -> returns true
        assertTrue(note.equals(new Note("Valid Note")));

        // same object -> returns true
        assertTrue(note.equals(note));

        // null -> returns false
        assertFalse(note.equals(null));

        // different types -> returns false
        assertFalse(note.equals(5.0f));

        // different values -> returns false
        assertFalse(note.equals(new Note("Other Valid Note")));
    }

    @Test
    public void hashCode_sameNote_sameHashCode() {
        Note note1 = new Note("Test note");
        Note note2 = new Note("Test note");
        assertEquals(note1.hashCode(), note2.hashCode());
    }

    @Test
    public void toString_validNote_returnsNoteValue() {
        String noteValue = "Important client";
        Note note = new Note(noteValue);
        assertEquals(noteValue, note.toString());
    }
}
