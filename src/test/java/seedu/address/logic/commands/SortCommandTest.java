package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


public class SortCommandTest {

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        SortCommand sortCommand = new SortCommand();
        assertThrows(NullPointerException.class, () -> sortCommand.execute(null));
    }

    @Test
    public void execute_sortByName_andReturnsMessage() {
        // Arrange: typical address book + two extra persons intentionally added in reverse order
        AddressBook addressBook = getTypicalAddressBook();
        Model model = new ModelManager(addressBook, new UserPrefs());

        Person amy = new PersonBuilder().withName("Amy Bee").build();
        Person zoe = new PersonBuilder().withName("zoe zebra").build(); // lower-case to test case-insensitive compare

        // Add in reverse order to ensure sorting actually reorders them
        model.addPerson(zoe);
        model.addPerson(amy);

        // Capture the list before sorting (copy for later comparison)
        List<Person> before = new ArrayList<>(model.getFilteredPersonList());

        // Act
        SortCommand sortCommand = new SortCommand();
        CommandResult result = sortCommand.execute(model);

        // Assert: message
        assertEquals(SortCommand.MESSAGE_SUCCESS, result.getFeedbackToUser());

        // Assert: list is sorted alphabetically by name, case-insensitive
        List<Person> after = new ArrayList<>(model.getFilteredPersonList());
        List<Person> expected = new ArrayList<>(before);
        expected.sort(Comparator.comparing(p -> p.getName().fullName.toLowerCase()));

        assertEquals(expected, after);
    }
}
