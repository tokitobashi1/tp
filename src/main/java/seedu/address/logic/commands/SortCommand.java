package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.model.Model;

/**
 * Sorts the contacts in the address book by name.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Contacts sorted alphabetically by name.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPersonList(Comparator.comparing(p -> p.getName().fullName.toLowerCase()));
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this || other instanceof SortCommand;
    }
}
