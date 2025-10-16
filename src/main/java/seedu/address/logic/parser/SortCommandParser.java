package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_SORT_COMMAND_FORMAT;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the given {@code String} of arguments in the context of the SortCommand
 * and returns a SortCommand object for execution.
 */
public class SortCommandParser implements Parser<SortCommand> {
    @Override
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        SortKeys key = trimmedArgs.isEmpty()
                ? SortKeys.NAME
                : parseField(trimmedArgs.toLowerCase(java.util.Locale.ROOT));
        return new SortCommand(key);
    }

    private SortKeys parseField(String raw) throws ParseException {
        switch (raw) {
        case "name": return SortKeys.NAME;
        case "phone": return SortKeys.PHONE;
        case "email": return SortKeys.EMAIL;
        case "address": return SortKeys.ADDRESS;
        case "tag": return SortKeys.TAG;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_SORT_COMMAND_FORMAT, raw));
        }
    }
}
