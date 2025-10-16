package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_SORT_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;

public class SortCommandParserTest {

    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArgs_success() {
        // No args → default behavior (e.g., sort by name ascending)
        assertParseSuccess(parser, "", new SortCommand(SortKeys.NAME));
    }

    @Test
    public void parse_whitespace_success() {
        // Pure whitespace should be treated like empty args
        assertParseSuccess(parser, "   \t\n  ", new SortCommand(SortKeys.NAME));
    }

    @Test
    public void parse_trailingWhitespace_success() {
        assertParseSuccess(parser, "   ", new SortCommand(SortKeys.NAME));
    }

    @Test
    public void parse_withArgs_failure() {
        String expected = String.format(MESSAGE_INVALID_SORT_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " number", expected);
        assertParseFailure(parser, " desc", expected);
        assertParseFailure(parser, " n/ e/", expected);
        assertParseFailure(parser, " foo bar", expected);
    }
}
