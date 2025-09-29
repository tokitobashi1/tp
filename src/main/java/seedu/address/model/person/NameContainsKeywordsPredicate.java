package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.commons.util.SearchUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Person> {
/**
 * Constructs the predicate with the provided list of keywords. The list itself
 * may not be {@code null}; individual keywords may be {@code null} or empty and will
 * be ignored by {@link #test(Person)}.
 *
 * @param keywords the keywords to search for (OR semantics).
 */
public NameContainsKeywordsPredicate(List<String> keywords) {
    this.keywords = keywords;
}

@Override
public boolean test(Person person) {
    if (keywords == null || keywords.isEmpty()) {
        return false;
    }

    return keywords.stream()
            .filter(k -> k != null && !k.trim().isEmpty())
            .map(String::trim)
            .anyMatch(keyword -> matchesAnyField(person, keyword));
}

/**
 * Returns true if any searchable field contains {@code keyword} (case-insensitive).
 */
private boolean matchesAnyField(Person person, String keyword) {
    // name
    if (person.getName() != null && SearchUtil.containsSubstringIgnoreCase(
            person.getName().fullName, keyword)) {
        return true;
    }

    // phone
    if (person.getPhone() != null && SearchUtil.containsSubstringIgnoreCase(
            person.getPhone().value, keyword)) {
        return true;
    }

    // email
    if (person.getEmail() != null && SearchUtil.containsSubstringIgnoreCase(
            person.getEmail().value, keyword)) {
        return true;
    }

    // address
    if (person.getAddress() != null && SearchUtil.containsSubstringIgnoreCase(
            person.getAddress().value, keyword)) {
        return true;
    }

    // company
    if (person.getCompany() != null && SearchUtil.containsSubstringIgnoreCase(
            person.getCompany().value, keyword)) {
        return true;
    }

    // tags
    return person.getTags().stream()
            .anyMatch(tag -> SearchUtil.containsSubstringIgnoreCase(tag.tagName, keyword));
}


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsKeywordsPredicate)) {
            return false;
        }

        NameContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (NameContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
