package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.SortKeys;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


public class SortCommandTest {

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        SortCommand sortCommand = new SortCommand(SortKeys.NAME);
        assertThrows(NullPointerException.class, () -> sortCommand.execute(null));
    }

    @Test
    public void execute_sortByName_andReturnsMessage() {
        AddressBook addressBook = getTypicalAddressBook();
        Model model = new ModelManager(addressBook, new UserPrefs());

        Person amy = new PersonBuilder().withName("Amy Bee").build();
        Person zoe = new PersonBuilder().withName("zoe zebra").build();

        model.addPerson(zoe);
        model.addPerson(amy);

        List<Person> before = new ArrayList<>(model.getFilteredPersonList());

        SortCommand sortCommand = new SortCommand(SortKeys.NAME);
        CommandResult result = sortCommand.execute(model);

        String successMessage = String.format(SortCommand.MESSAGE_SUCCESS, SortKeys.NAME.getDisplayName());
        assertEquals(successMessage, result.getFeedbackToUser());

        List<Person> after = new ArrayList<>(model.getFilteredPersonList());
        List<Person> expected = new ArrayList<>(before);
        expected.sort(Comparator.comparing(p -> p.getName().fullName.toLowerCase()));

        assertEquals(expected, after);
    }

    @Test
    public void execute_sortByPhone_sortsAscending() {
        AddressBook ab = getTypicalAddressBook();
        Model model = new ModelManager(ab, new UserPrefs());

        List<Person> before = new ArrayList<>(model.getFilteredPersonList());

        SortCommand sortCommand = new SortCommand(SortKeys.PHONE);
        CommandResult result = sortCommand.execute(model);

        String successMessage = String.format(SortCommand.MESSAGE_SUCCESS, SortKeys.PHONE.getDisplayName());
        assertEquals(successMessage, result.getFeedbackToUser());

        List<Person> after = new ArrayList<>(model.getFilteredPersonList());
        List<Person> expected = new ArrayList<>(before);
        expected.sort(Comparator.comparing(p -> p.getPhone().value));

        assertEquals(expected, after);
    }

    @Test
    public void execute_sortByEmail_sortsAscendingCaseInsensitive() {
        AddressBook ab = getTypicalAddressBook();
        Model model = new ModelManager(ab, new UserPrefs());

        List<Person> before = new ArrayList<>(model.getFilteredPersonList());

        SortCommand sortCommand = new SortCommand(SortKeys.EMAIL);
        CommandResult result = sortCommand.execute(model);

        String successMessage = String.format(SortCommand.MESSAGE_SUCCESS, SortKeys.EMAIL.getDisplayName());
        assertEquals(successMessage, result.getFeedbackToUser());

        List<Person> after = new ArrayList<>(model.getFilteredPersonList());
        List<Person> expected = new ArrayList<>(before);
        expected.sort(Comparator.comparing(p -> p.getEmail().value.toLowerCase()));

        assertEquals(expected, after);
    }

    @Test
    public void execute_sortByAddress_sortsAscendingCaseInsensitive() {
        AddressBook ab = getTypicalAddressBook();
        Model model = new ModelManager(ab, new UserPrefs());

        List<Person> before = new ArrayList<>(model.getFilteredPersonList());

        SortCommand sortCommand = new SortCommand(SortKeys.ADDRESS);
        CommandResult result = sortCommand.execute(model);

        String successMessage = String.format(SortCommand.MESSAGE_SUCCESS, SortKeys.ADDRESS.getDisplayName());
        assertEquals(successMessage, result.getFeedbackToUser());

        List<Person> after = new ArrayList<>(model.getFilteredPersonList());
        List<Person> expected = new ArrayList<>(before);
        expected.sort(Comparator.comparing(p -> p.getAddress().value.toLowerCase()));

        assertEquals(expected, after);
    }

    @Test
    public void execute_sortByTag_sortsByFirstTagCaseInsensitive() {
        AddressBook ab = getTypicalAddressBook();
        Model model = new ModelManager(ab, new UserPrefs());

        List<Person> before = new ArrayList<>(model.getFilteredPersonList());

        SortCommand sortCommand = new SortCommand(SortKeys.TAG);
        CommandResult result = sortCommand.execute(model);

        String successMessage = String.format(SortCommand.MESSAGE_SUCCESS, SortKeys.TAG.getDisplayName());
        assertEquals(successMessage, result.getFeedbackToUser());

        List<Person> after = new ArrayList<>(model.getFilteredPersonList());
        List<Person> expected = new ArrayList<>(before);

        // Expected: sort by lexicographically smallest tag (case-insensitive). If no tags, treat as "".
        expected.sort(Comparator.comparing(p ->
                p.getTags().stream()
                        .map(tag -> tag.tagName.toLowerCase())
                        .sorted()
                        .findFirst()
                        .orElse("")
        ));

        assertEquals(expected, after);
    }
}
