package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Lists all contacts in the address book to the user.
 * Shows all contacts with index numbers in the format:
 * "[index]. NAME | PHONE | [EMAIL] | [COMPANY] | [TAGS]".
 * If the list is empty, outputs "No contacts stored".
 */
public class ListCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Listed all contacts!";
    public static final String COMMAND_WORD = "list";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        List<Person> contacts = model.getFilteredPersonList();
        String feedbackToUser;

        if (contacts.isEmpty()) {
            feedbackToUser = "No contacts stored";
        } else {
            // Build the formatted string
            feedbackToUser = contacts.stream()
                    .map(person -> formatPerson(person, contacts.indexOf(person) + 1))
                    .collect(Collectors.joining("\n"));
        }

        return new CommandResult(feedbackToUser);
    }

    private String formatPerson(Person person, int index) {
        return String.format("%d. %s | %s | [%s] | [%s] | %s",
                index,
                person.getName(),
                person.getPhone(),
                person.getEmail(),
                person.getCompany(),
                person.getTags());
    }
}
