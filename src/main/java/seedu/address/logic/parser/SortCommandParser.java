package seedu.address.logic.parser;

import seedu.address.logic.commands.SortCommand;

/**
 * Parses the given {@code String} of arguments in the context of the SortCommand
 * and returns a SortCommand object for execution.
 */
public class SortCommandParser implements Parser<SortCommand> {
    @Override
    public SortCommand parse(String args) {
        return new SortCommand();
    }
}
