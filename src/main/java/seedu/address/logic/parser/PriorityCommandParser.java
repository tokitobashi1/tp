package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.PriorityCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Priority;

/**
 * Parses input arguments and creates a new PriorityCommand object
 */
public class PriorityCommandParser implements Parser<PriorityCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PriorityCommand
     * and returns a PriorityCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PriorityCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PRIORITY);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    PriorityCommand.MESSAGE_USAGE), ive);
        }

        if (!argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    PriorityCommand.MESSAGE_USAGE));
        }

        Priority priority;
        try {
            priority = ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    PriorityCommand.MESSAGE_USAGE), pe);
        }

        return new PriorityCommand(index, priority);
    }
}
