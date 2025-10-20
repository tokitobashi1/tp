package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PriorityCommand;
import seedu.address.model.person.Priority;

public class PriorityCommandParserTest {

    private PriorityCommandParser parser = new PriorityCommandParser();

    @Test
    public void parse_validArgs_returnsPriorityCommand() {
        // high priority
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_PRIORITY + "HIGH";
        PriorityCommand expectedCommand = new PriorityCommand(INDEX_FIRST_PERSON, new Priority("HIGH"));
        assertParseSuccess(parser, userInput, expectedCommand);

        // medium priority
        userInput = targetIndex.getOneBased() + " " + PREFIX_PRIORITY + "MEDIUM";
        expectedCommand = new PriorityCommand(INDEX_FIRST_PERSON, new Priority("MEDIUM"));
        assertParseSuccess(parser, userInput, expectedCommand);

        // low priority
        userInput = targetIndex.getOneBased() + " " + PREFIX_PRIORITY + "LOW";
        expectedCommand = new PriorityCommand(INDEX_FIRST_PERSON, new Priority("LOW"));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_numericPriority_returnsPriorityCommand() {
        // numeric priority 1 (HIGH)
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_PRIORITY + "1";
        PriorityCommand expectedCommand = new PriorityCommand(INDEX_FIRST_PERSON, new Priority("1"));
        assertParseSuccess(parser, userInput, expectedCommand);

        // numeric priority 3 (MEDIUM)
        userInput = targetIndex.getOneBased() + " " + PREFIX_PRIORITY + "3";
        expectedCommand = new PriorityCommand(INDEX_FIRST_PERSON, new Priority("3"));
        assertParseSuccess(parser, userInput, expectedCommand);

        // numeric priority 5 (LOW)
        userInput = targetIndex.getOneBased() + " " + PREFIX_PRIORITY + "5";
        expectedCommand = new PriorityCommand(INDEX_FIRST_PERSON, new Priority("5"));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_caseInsensitivePriority_returnsPriorityCommand() {
        Index targetIndex = INDEX_FIRST_PERSON;

        // lowercase
        String userInput = targetIndex.getOneBased() + " " + PREFIX_PRIORITY + "high";
        PriorityCommand expectedCommand = new PriorityCommand(INDEX_FIRST_PERSON, new Priority("high"));
        assertParseSuccess(parser, userInput, expectedCommand);

        // mixed case
        userInput = targetIndex.getOneBased() + " " + PREFIX_PRIORITY + "MeDiUm";
        expectedCommand = new PriorityCommand(INDEX_FIRST_PERSON, new Priority("MeDiUm"));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        // negative index
        String userInput = "-1 " + PREFIX_PRIORITY + "HIGH";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                PriorityCommand.MESSAGE_USAGE));

        // zero index
        userInput = "0 " + PREFIX_PRIORITY + "HIGH";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                PriorityCommand.MESSAGE_USAGE));

        // non-numeric index
        userInput = "a " + PREFIX_PRIORITY + "HIGH";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                PriorityCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingPriority_throwsParseException() {
        String userInput = "1";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                PriorityCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPriority_throwsParseException() {
        // invalid priority text
        String userInput = "1 " + PREFIX_PRIORITY + "URGENT";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                PriorityCommand.MESSAGE_USAGE));

        // invalid numeric priority (out of range)
        userInput = "1 " + PREFIX_PRIORITY + "6";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                PriorityCommand.MESSAGE_USAGE));

        userInput = "1 " + PREFIX_PRIORITY + "0";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                PriorityCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingIndex_throwsParseException() {
        String userInput = " " + PREFIX_PRIORITY + "HIGH";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                PriorityCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                PriorityCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_priorityWithWhitespace_returnsPriorityCommand() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_PRIORITY + "  HIGH  ";
        PriorityCommand expectedCommand = new PriorityCommand(INDEX_FIRST_PERSON, new Priority("HIGH"));
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
