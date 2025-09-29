package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompanyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Company(null));
    }

    @Test
    public void constructor_invalidCompany_throwsIllegalArgumentException() {
        String invalidCompany = "";
        assertThrows(IllegalArgumentException.class, () -> new Company(invalidCompany));
    }

    @Test
    public void isValidCompany() {
        // null company
        assertThrows(NullPointerException.class, () -> Company.isValidCompany(null));

        // invalid company names
        assertFalse(Company.isValidCompany("")); // empty string
        assertFalse(Company.isValidCompany(" ")); // spaces only
        assertFalse(Company.isValidCompany("@#$%")); // only special characters not allowed
        assertFalse(Company.isValidCompany("Company!")); // exclamation mark not allowed
        assertFalse(Company.isValidCompany("Company*")); // asterisk not allowed
        assertFalse(Company.isValidCompany(" Company")); // starting with space

        // valid company names
        assertTrue(Company.isValidCompany("Google"));
        assertTrue(Company.isValidCompany("Microsoft Corporation"));
        assertTrue(Company.isValidCompany("Tech Corp."));
        assertTrue(Company.isValidCompany("ABC & Co"));
        assertTrue(Company.isValidCompany("Smith, Jones & Associates"));
        assertTrue(Company.isValidCompany("3M")); // starting with number
        assertTrue(Company.isValidCompany("Tech-Solutions"));
        assertTrue(Company.isValidCompany("A")); // single character
        assertTrue(Company.isValidCompany("Very Long Company Name With Many Words Inc."));
    }

    @Test
    public void equals() {
        Company company = new Company("Tech Corp");

        // same values -> returns true
        assertTrue(company.equals(new Company("Tech Corp")));

        // same object -> returns true
        assertTrue(company.equals(company));

        // null -> returns false
        assertFalse(company.equals(null));

        // different types -> returns false
        assertFalse(company.equals(5.0f));

        // different values -> returns false
        assertFalse(company.equals(new Company("Other Company")));
    }

    @Test
    public void toString_validCompany_returnsCompanyName() {
        String companyName = "Tech Solutions Inc";
        Company company = new Company(companyName);
        assertEquals(companyName, company.toString());
    }

    @Test
    public void hashCode_sameCompany_sameHashCode() {
        Company company1 = new Company("Tech Corp");
        Company company2 = new Company("Tech Corp");
        assertEquals(company1.hashCode(), company2.hashCode());
    }

    @Test
    public void hashCode_differentCompany_differentHashCode() {
        Company company1 = new Company("Tech Corp");
        Company company2 = new Company("Other Corp");
        assertFalse(company1.hashCode() == company2.hashCode());
    }
}
