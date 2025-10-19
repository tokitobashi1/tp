package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriorityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Priority((String) null));
        assertThrows(NullPointerException.class, () -> new Priority((Priority.Level) null));
    }

    @Test
    public void constructor_invalidPriority_throwsIllegalArgumentException() {
        String invalidPriority = "";
        assertThrows(IllegalArgumentException.class, () -> new Priority(invalidPriority));
    }

    @Test
    public void isValidPriority() {
        // null priority
        assertThrows(NullPointerException.class, () -> Priority.isValidPriority(null));

        // invalid priorities
        assertFalse(Priority.isValidPriority("")); // empty string
        assertFalse(Priority.isValidPriority(" ")); // spaces only
        assertFalse(Priority.isValidPriority("URGENT")); // invalid text
        assertFalse(Priority.isValidPriority("0")); // invalid number (too low)
        assertFalse(Priority.isValidPriority("6")); // invalid number (too high)
        assertFalse(Priority.isValidPriority("abc")); // non-numeric/non-valid text

        // valid priorities (text)
        assertTrue(Priority.isValidPriority("HIGH"));
        assertTrue(Priority.isValidPriority("MEDIUM"));
        assertTrue(Priority.isValidPriority("LOW"));
        assertTrue(Priority.isValidPriority("high")); // case insensitive
        assertTrue(Priority.isValidPriority("MeDiUm")); // mixed case

        // valid priorities (numeric)
        assertTrue(Priority.isValidPriority("1"));
        assertTrue(Priority.isValidPriority("2"));
        assertTrue(Priority.isValidPriority("3"));
        assertTrue(Priority.isValidPriority("4"));
        assertTrue(Priority.isValidPriority("5"));
    }

    @Test
    public void constructor_validTextPriority_success() {
        Priority highPriority = new Priority("HIGH");
        assertEquals(Priority.Level.HIGH, highPriority.getLevel());

        Priority mediumPriority = new Priority("MEDIUM");
        assertEquals(Priority.Level.MEDIUM, mediumPriority.getLevel());

        Priority lowPriority = new Priority("LOW");
        assertEquals(Priority.Level.LOW, lowPriority.getLevel());
    }

    @Test
    public void constructor_validNumericPriority_success() {
        Priority priority1 = new Priority("1");
        assertEquals(Priority.Level.HIGH, priority1.getLevel());

        Priority priority2 = new Priority("2");
        assertEquals(Priority.Level.HIGH, priority2.getLevel());

        Priority priority3 = new Priority("3");
        assertEquals(Priority.Level.MEDIUM, priority3.getLevel());

        Priority priority4 = new Priority("4");
        assertEquals(Priority.Level.MEDIUM, priority4.getLevel());

        Priority priority5 = new Priority("5");
        assertEquals(Priority.Level.LOW, priority5.getLevel());
    }

    @Test
    public void constructor_levelEnum_success() {
        Priority highPriority = new Priority(Priority.Level.HIGH);
        assertEquals(Priority.Level.HIGH, highPriority.getLevel());

        Priority mediumPriority = new Priority(Priority.Level.MEDIUM);
        assertEquals(Priority.Level.MEDIUM, mediumPriority.getLevel());

        Priority lowPriority = new Priority(Priority.Level.LOW);
        assertEquals(Priority.Level.LOW, lowPriority.getLevel());
    }

    @Test
    public void getSymbol() {
        Priority highPriority = new Priority("HIGH");
        assertEquals("!!!", highPriority.getSymbol());

        Priority mediumPriority = new Priority("MEDIUM");
        assertEquals("!!", mediumPriority.getSymbol());

        Priority lowPriority = new Priority("LOW");
        assertEquals("!", lowPriority.getSymbol());
    }

    @Test
    public void getLevel() {
        Priority highPriority = new Priority("HIGH");
        assertEquals(Priority.Level.HIGH, highPriority.getLevel());
        assertEquals(1, highPriority.getLevel().getNumericValue());
        assertEquals("HIGH", highPriority.getLevel().getDisplayName());
        assertEquals("#ff4444", highPriority.getLevel().getColorHex());

        Priority mediumPriority = new Priority("MEDIUM");
        assertEquals(Priority.Level.MEDIUM, mediumPriority.getLevel());
        assertEquals(3, mediumPriority.getLevel().getNumericValue());
        assertEquals("MEDIUM", mediumPriority.getLevel().getDisplayName());
        assertEquals("#ffbb33", mediumPriority.getLevel().getColorHex());

        Priority lowPriority = new Priority("LOW");
        assertEquals(Priority.Level.LOW, lowPriority.getLevel());
        assertEquals(5, lowPriority.getLevel().getNumericValue());
        assertEquals("LOW", lowPriority.getLevel().getDisplayName());
        assertEquals("#00C851", lowPriority.getLevel().getColorHex());
    }

    @Test
    public void toString_method() {
        Priority highPriority = new Priority("HIGH");
        assertEquals("HIGH", highPriority.toString());

        Priority mediumPriority = new Priority("MEDIUM");
        assertEquals("MEDIUM", mediumPriority.toString());

        Priority lowPriority = new Priority("LOW");
        assertEquals("LOW", lowPriority.toString());
    }

    @Test
    public void equals() {
        Priority priority = new Priority("HIGH");

        // same values -> returns true
        assertTrue(priority.equals(new Priority("HIGH")));
        assertTrue(priority.equals(new Priority("1"))); // numeric equivalent

        // same object -> returns true
        assertTrue(priority.equals(priority));

        // null -> returns false
        assertFalse(priority.equals(null));

        // different types -> returns false
        assertFalse(priority.equals(5.0f));

        // different values -> returns false
        assertFalse(priority.equals(new Priority("LOW")));
    }

    @Test
    public void hashCode_method() {
        Priority priority1 = new Priority("HIGH");
        Priority priority2 = new Priority("HIGH");
        Priority priority3 = new Priority("1"); // numeric equivalent of HIGH
        Priority priority4 = new Priority("LOW");

        // same priority -> same hashcode
        assertEquals(priority1.hashCode(), priority2.hashCode());
        assertEquals(priority1.hashCode(), priority3.hashCode());

        // different priority -> different hashcode (not guaranteed, but likely)
        assertTrue(priority1.hashCode() != priority4.hashCode());
    }

    @Test
    public void caseInsensitive_parsing() {
        Priority priority1 = new Priority("high");
        Priority priority2 = new Priority("HIGH");
        Priority priority3 = new Priority("HiGh");

        assertEquals(priority1.getLevel(), priority2.getLevel());
        assertEquals(priority2.getLevel(), priority3.getLevel());
    }

    @Test
    public void whitespace_handling() {
        Priority priority1 = new Priority("  HIGH  ");
        Priority priority2 = new Priority("HIGH");

        assertEquals(priority1.getLevel(), priority2.getLevel());
    }
}
