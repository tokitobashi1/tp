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
        // Test invalid sort keys
        assertParseFailure(parser, " number", String.format(MESSAGE_INVALID_SORT_COMMAND_FORMAT, "number"));
        assertParseFailure(parser, " desc", String.format(MESSAGE_INVALID_SORT_COMMAND_FORMAT, "desc"));
        assertParseFailure(parser, " n/ e/", String.format(MESSAGE_INVALID_SORT_COMMAND_FORMAT, "n/ e/"));
        assertParseFailure(parser, " foo bar", String.format(MESSAGE_INVALID_SORT_COMMAND_FORMAT, "foo bar"));
    }

    @Test
    public void parse_validSortKeys_success() {
        // Test all valid sort keys
        assertParseSuccess(parser, "name", new SortCommand(SortKeys.NAME));
        assertParseSuccess(parser, "phone", new SortCommand(SortKeys.PHONE));
        assertParseSuccess(parser, "email", new SortCommand(SortKeys.EMAIL));
        assertParseSuccess(parser, "address", new SortCommand(SortKeys.ADDRESS));
        assertParseSuccess(parser, "tag", new SortCommand(SortKeys.TAG));
        assertParseSuccess(parser, "priority", new SortCommand(SortKeys.PRIORITY));
    }
}
