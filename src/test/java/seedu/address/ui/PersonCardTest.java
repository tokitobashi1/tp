package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class PersonCardTest {

    @Test
    public void helpers_nullOptionalFields() {
        Person p = new PersonBuilder()
                .withName("Z")
                .withPhone("0000")
                .withEmail(null)
                .withAddress(null)
                .withCompany(null)
                .withNote(null)
                .withTags()
                .build();

        assertEquals("", PersonCard.getAddressText(p));
        assertFalse(PersonCard.isAddressVisible(p));

        assertEquals("", PersonCard.getEmailText(p));
        assertFalse(PersonCard.isEmailVisible(p));

        assertEquals("", PersonCard.getCompanyText(p));
        assertFalse(PersonCard.isCompanyVisible(p));

        assertEquals("", PersonCard.getNoteText(p));
        assertFalse(PersonCard.isNoteVisible(p));
    }

    @Test
    public void helpers_presentOptionalFields() {
        Person p = new PersonBuilder()
                .withName("Alice")
                .withPhone("1111")
                .withEmail("alice@example.com")
                .withAddress("123, Main Street")
                .withCompany("Acme Inc")
                .withNote("VIP client")
                .withTags("friends")
                .build();

        assertEquals("123, Main Street", PersonCard.getAddressText(p));
        assertTrue(PersonCard.isAddressVisible(p));

        assertEquals("alice@example.com", PersonCard.getEmailText(p));
        assertTrue(PersonCard.isEmailVisible(p));

        assertEquals("Acme Inc", PersonCard.getCompanyText(p));
        assertTrue(PersonCard.isCompanyVisible(p));

        assertEquals("Note: VIP client", PersonCard.getNoteText(p));
        assertTrue(PersonCard.isNoteVisible(p));
    }

    @Test
    public void helpers_emptyNotePlaceholder_notVisible() {
        Person p = new PersonBuilder()
                .withName("Bob")
                .withPhone("2222")
                .withNote("-")
                .build();

        assertEquals("", PersonCard.getNoteText(p));
        assertFalse(PersonCard.isNoteVisible(p));
    }

    @Test
    public void getNoteText_withNote_returnsFormattedNote() {
        Person p = new PersonBuilder()
                .withName("Charlie")
                .withPhone("3333")
                .withNote("Important contact - call before 5pm")
                .build();

        assertEquals("Note: Important contact - call before 5pm", PersonCard.getNoteText(p));
    }

    @Test
    public void isNoteVisible_withEmptyPlaceholder_returnsFalse() {
        Person p = new PersonBuilder()
                .withName("David")
                .withPhone("4444")
                .withNote("-")
                .build();

        assertFalse(PersonCard.isNoteVisible(p));
    }

    @Test
    public void isNoteVisible_withRealNote_returnsTrue() {
        Person p = new PersonBuilder()
                .withName("Eve")
                .withPhone("5555")
                .withNote("Regular customer")
                .build();

        assertTrue(PersonCard.isNoteVisible(p));
    }
}
