package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


public class UndoCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();


    @Test
    public void execute_afterAdd_success() throws Exception {
        Person p = new PersonBuilder().build();
        model.addPerson(p);
        model.commitAddressBook();

        model.deletePerson(p);
        model.commitAddressBook();


        expectedModel.addPerson(p);
        expectedModel.commitAddressBook();
        expectedModel.deletePerson(p);
        expectedModel.commitAddressBook();

        expectedModel.undoAddressBook();

        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
