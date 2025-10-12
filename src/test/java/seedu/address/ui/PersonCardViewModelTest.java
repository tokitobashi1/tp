package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonCardViewModelTest {

    @Test
    public void viewModel_optionalFieldsHiddenWhenNull() {
        var person = new PersonBuilder()
                .withName("Z")
                .withPhone("0000")
                .withEmail(null)
                .withAddress(null)
                .withCompany(null)
                .withNote(null)
                .withTags()
                .build();

        PersonCardViewModel vm = new PersonCardViewModel(person, 1);

        assertEquals("1. ", vm.getIdText());
        assertEquals("Z", vm.getNameText());
        assertEquals("0000", vm.getPhoneText());

        assertEquals("", vm.getAddressText());
        assertFalse(vm.isAddressVisible());

        assertEquals("", vm.getEmailText());
        assertFalse(vm.isEmailVisible());

        assertEquals("", vm.getCompanyText());
        assertFalse(vm.isCompanyVisible());

        assertEquals("", vm.getNoteText());
        assertFalse(vm.isNoteVisible());

        assertEquals("", vm.getTagsText());
    }

    @Test
    public void viewModel_fieldsPresent_showsTextsAndVisible() {
        var person = new PersonBuilder()
                .withName("Alice")
                .withPhone("1234")
                .withEmail("alice@example.com")
                .withAddress("1 Main St")
                .withCompany("Acme Corp")
                .withNote("Important client")
                .withTags("friend", "colleague")
                .build();

        PersonCardViewModel vm = new PersonCardViewModel(person, 3);

        assertEquals("3. ", vm.getIdText());
        assertEquals("Alice", vm.getNameText());
        assertEquals("1234", vm.getPhoneText());

        assertEquals("1 Main St", vm.getAddressText());
        assertTrue(vm.isAddressVisible());

        assertEquals("alice@example.com", vm.getEmailText());
        assertTrue(vm.isEmailVisible());

        assertEquals("Acme Corp", vm.getCompanyText());
        assertTrue(vm.isCompanyVisible());

        assertEquals("Note: Important client", vm.getNoteText());
        assertTrue(vm.isNoteVisible());

        assertEquals("colleague, friend", vm.getTagsText());
    }

    @Test
    public void viewModel_emptyNote_notVisible() {
        var person = new PersonBuilder()
                .withName("Bob")
                .withPhone("5678")
                .withNote("-")
                .build();

        PersonCardViewModel vm = new PersonCardViewModel(person, 2);

        assertEquals("", vm.getNoteText());
        assertFalse(vm.isNoteVisible());
    }

    @Test
    public void viewModel_noteWithSpecialCharacters_showsCorrectly() {
        var person = new PersonBuilder()
                .withName("Charlie")
                .withPhone("9012")
                .withNote("Call before 5pm! Very important.")
                .build();

        PersonCardViewModel vm = new PersonCardViewModel(person, 1);

        assertEquals("Note: Call before 5pm! Very important.", vm.getNoteText());
        assertTrue(vm.isNoteVisible());
    }
}
