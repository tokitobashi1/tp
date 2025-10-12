package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.NoteCommand;
import seedu.address.model.person.Note;

public class NoteCommandParserTest {

    private NoteCommandParser parser = new NoteCommandParser();
    private final String nonEmptyNote = "Some note.";

    @Test
    public void parse_indexSpecified_success() {
        // have note
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK + nonEmptyNote;
        NoteCommand expectedCommand = new NoteCommand(INDEX_FIRST_PERSON, new Note(nonEmptyNote));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no note (clear note)
        userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK;
        expectedCommand = new NoteCommand(INDEX_FIRST_PERSON, new Note("-"));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, "", expectedMessage);

        // no index
        assertParseFailure(parser, " " + PREFIX_REMARK + nonEmptyNote, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid index
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "a " + PREFIX_REMARK + nonEmptyNote, expectedMessage);

        // negative index
        assertParseFailure(parser, "-1 " + PREFIX_REMARK + nonEmptyNote, expectedMessage);

        // zero index
        assertParseFailure(parser, "0 " + PREFIX_REMARK + nonEmptyNote, expectedMessage);
    }

    @Test
    public void parse_emptyNote_success() {
        // Empty note should be treated as clearing the note
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK;
        NoteCommand expectedCommand = new NoteCommand(INDEX_FIRST_PERSON, new Note("-"));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_noteWithSpecialCharacters_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String noteWithSpecialChars = "Client! Very important. Call @ 5pm.";
        String userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK + noteWithSpecialChars;
        NoteCommand expectedCommand = new NoteCommand(INDEX_FIRST_PERSON, new Note(noteWithSpecialChars));
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
