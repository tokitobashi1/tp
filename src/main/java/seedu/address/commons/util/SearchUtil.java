package seedu.address.commons.util;
import java.util.Objects;

/**
 * Utility methods used for searching and matching strings.
 *
 * This is a helper class implemented to
 * / make "find" feature more easily implementable
 */
public final class SearchUtil {

    private SearchUtil() {}

    /**
     * @param source The full string to search within. Must be non-null.
     * @param substring The substring to search for. Must be non-null and non-empty.
     * @return {@code true} if the source contains the substring, ignoring case;
     *         {@code false} otherwise.
     * @throws NullPointerException If either {@code source} or {@code substring} is null.
     */
    public static boolean containsSubstringIgnoreCase(String source, String substring) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(substring);

        if (substring.isEmpty()) {
            return false;
        }

        return source.toLowerCase().contains(substring.toLowerCase());
    }
}
