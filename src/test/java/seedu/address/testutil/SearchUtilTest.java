package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SearchUtilTest {

    @Test
    void containsSubstringIgnoreCase_emptySubstring_returnsFalse() {
        assertFalse(SearchUtil.containsSubstringIgnoreCase("anything", ""));
    }

    @Test
    void containsSubstringIgnoreCase_contains_returnsTrue() {
        assertTrue(SearchUtil.containsSubstringIgnoreCase("John Doe", "ohn"));
        assertTrue(SearchUtil.containsSubstringIgnoreCase("Acme Inc", "acme"));
    }

    @Test
    void containsSubstringIgnoreCase_nullSource_throwsNPE() {
        assertThrows(NullPointerException.class, () ->
            SearchUtil.containsSubstringIgnoreCase(null, "a"));
    }

    @Test
    void containsSubstringIgnoreCase_nullSubstring_throwsNPE() {
        assertThrows(NullPointerException.class, () ->
            SearchUtil.containsSubstringIgnoreCase("a", null));
    }
}