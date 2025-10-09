package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import seedu.address.model.Model;
import java.util.Comparator;

/**
 * Sorts the contacts in the address book by name.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_SUCCESS = "Contacts sorted alphabetically by name.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredPersonList(Comparator.comparing(p -> p.getName().fullName.toLowerCase()));
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
