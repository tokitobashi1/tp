package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Integration tests for duplicate detection based on both name and phone number.
 * These tests verify that the enhanced duplicate detection correctly identifies duplicates
 * only when BOTH name AND phone number match.
 */
public class DuplicateDetectionIntegrationTest {

    private Model model;
    private Person basePerson;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        // Create a base person with AMY's details
        basePerson = new PersonBuilder()
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .build();
        model.addPerson(basePerson);
    }

    @Test
    public void addPerson_sameNameAndPhone_failure() {
        // Person with same name and phone but different email
        Person duplicatePerson = new PersonBuilder()
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY)
                .withEmail("different@example.com")
                .build();

        AddCommand addCommand = new AddCommand(duplicatePerson);
        assertCommandFailure(addCommand, model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void addPerson_sameNameDifferentPhone_success() {
        // Person with same name but different phone - should be allowed
        Person differentPerson = new PersonBuilder()
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB) // Different phone
                .withEmail(VALID_EMAIL_AMY)
                .build();

        Model expectedModel = new ModelManager();
        expectedModel.addPerson(basePerson);
        expectedModel.addPerson(differentPerson);

        AddCommand addCommand = new AddCommand(differentPerson);
        assertCommandSuccess(addCommand, model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(differentPerson)),
                expectedModel);
    }

    @Test
    public void addPerson_differentNameSamePhone_success() {
        // Person with different name but same phone - should be allowed
        Person differentPerson = new PersonBuilder()
                .withName(VALID_NAME_BOB) // Different name
                .withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_BOB)
                .build();

        Model expectedModel = new ModelManager();
        expectedModel.addPerson(basePerson);
        expectedModel.addPerson(differentPerson);

        AddCommand addCommand = new AddCommand(differentPerson);
        assertCommandSuccess(addCommand, model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(differentPerson)),
                expectedModel);
    }

    @Test
    public void addPerson_differentNameAndPhone_success() {
        // Person with both different name and phone - should be allowed
        Person differentPerson = new PersonBuilder()
                .withName(VALID_NAME_BOB) // Different name
                .withPhone(VALID_PHONE_BOB) // Different phone
                .withEmail(VALID_EMAIL_BOB)
                .build();

        Model expectedModel = new ModelManager();
        expectedModel.addPerson(basePerson);
        expectedModel.addPerson(differentPerson);

        AddCommand addCommand = new AddCommand(differentPerson);
        assertCommandSuccess(addCommand, model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(differentPerson)),
                expectedModel);
    }

    @Test
    public void isSamePerson_sameNameAndPhone_true() {
        Person person1 = new PersonBuilder()
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .build();

        Person person2 = new PersonBuilder()
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY)
                .withEmail("different@example.com") // Different email
                .build();

        assertTrue(person1.isSamePerson(person2));
    }

    @Test
    public void isSamePerson_sameNameDifferentPhone_false() {
        Person person1 = new PersonBuilder()
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY)
                .build();

        Person person2 = new PersonBuilder()
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB) // Different phone
                .build();

        assertFalse(person1.isSamePerson(person2));
    }

    @Test
    public void isSamePerson_differentNameSamePhone_false() {
        Person person1 = new PersonBuilder()
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY)
                .build();

        Person person2 = new PersonBuilder()
                .withName(VALID_NAME_BOB) // Different name
                .withPhone(VALID_PHONE_AMY)
                .build();

        assertFalse(person1.isSamePerson(person2));
    }

    @Test
    public void isSamePerson_differentNameAndPhone_false() {
        Person person1 = new PersonBuilder()
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY)
                .build();

        Person person2 = new PersonBuilder()
                .withName(VALID_NAME_BOB) // Different name
                .withPhone(VALID_PHONE_BOB) // Different phone
                .build();

        assertFalse(person1.isSamePerson(person2));
    }
}
