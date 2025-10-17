package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String CONFIRMATION_KEYWORD = "confirm";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Clears all contacts.\n"
                    + "To proceed, type: clear " + CONFIRMATION_KEYWORD;

    public static final String MESSAGE_CONFIRMATION_REQUIRED =
            "⚠This will delete ALL contacts!\n"
                    + "Please confirm by typing: clear " + CONFIRMATION_KEYWORD;

    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    private final boolean isConfirmed;

    public ClearCommand(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (!isConfirmed) {
            return new CommandResult(MESSAGE_CONFIRMATION_REQUIRED);
        }
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ClearCommand
                && isConfirmed == ((ClearCommand) other).isConfirmed);
    }
}
