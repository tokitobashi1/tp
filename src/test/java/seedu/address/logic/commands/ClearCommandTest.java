package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(true), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(true), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_withoutConfirmation_showsWarning() {
        Model model = new ModelManager();
        CommandResult result = new ClearCommand(false).execute(model);
        assertEquals(ClearCommand.MESSAGE_CONFIRMATION_REQUIRED, result.getFeedbackToUser());
    }

    @Test
    public void execute_withConfirmation_clearsSuccessfully() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        CommandResult result = new ClearCommand(true).execute(model);
        assertEquals(ClearCommand.MESSAGE_SUCCESS, result.getFeedbackToUser());
        assertTrue(model.getAddressBook().getPersonList().isEmpty());
    }
}
