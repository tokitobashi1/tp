package seedu.address.testutil;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.SearchUtil;

public class SearchUtilTest {

    @Test
    void containsSubstringIgnoreCase_emptySubstring_returnsFalse() {
        assertFalse(SearchUtil.containsSubstringIgnoreCase("anything", ""));
    }

    @Test
    void containsSubstringIgnoreCase_contains_returnsTrue() {
        assertTrue(SearchUtil.containsSubstringIgnoreCase("John Doe", "ohn"));
    }

    @Test
    void containsSubstringIgnoreCase_nullSource_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                SearchUtil.containsSubstringIgnoreCase(null, "a"));
    }

    @Test
    void containsSubstringIgnoreCase_nullSubstring_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                SearchUtil.containsSubstringIgnoreCase("a", null));
    }
}
