package seedu.address.logic.parser;

/**
 * Enumeration of keys to sort contacts by.
 * Used by SortCommand and SortCommandParser.
 */
public enum SortKeys {
    NAME("name"),
    PHONE("number"),
    EMAIL("email"),
    ADDRESS("address"),
    TAG("tag");

    private final String displayName;

    SortKeys(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
