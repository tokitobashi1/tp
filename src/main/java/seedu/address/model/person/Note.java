package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a Person's note/remark in the address book.
 * Immutable: value and optional lastEdited timestamp.
 */
public class Note {

    public static final String MESSAGE_CONSTRAINTS = "Notes can take any values, and it should not be blank";
    public static final String EMPTY_NOTE_PLACEHOLDER = "-";

    /**
     * The first character of the note must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "([^\\s].*|-)"; // allow "-" too

    private static final DateTimeFormatter DISPLAY_FORMATTER =
            DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");

    public final String value;
    private final LocalDateTime lastEdited;

    /**
     * Constructs a {@code Note} with lastEdited = now().
     *
     * @param note A valid note.
     */
    public Note(String note) {
        requireNonNull(note);
        checkArgument(isValidNote(note), MESSAGE_CONSTRAINTS);

        this.value = note;

        if (note.equals(EMPTY_NOTE_PLACEHOLDER)) {
            this.lastEdited = null;
        } else {
            this.lastEdited = LocalDateTime.now();
        }
    }

    /**
     * Constructs a {@code Note} with explicit timestamp (for deserialization).
     *
     * @param note A valid note.
     * @param lastEdited timestamp when the note was last edited; may be null.
     */
    public Note(String note, LocalDateTime lastEdited) {
        requireNonNull(note);
        checkArgument(isValidNote(note), MESSAGE_CONSTRAINTS);

        this.value = note;

        if (note.equals(EMPTY_NOTE_PLACEHOLDER)) {
            this.lastEdited = null;
        } else {
            this.lastEdited = lastEdited;
        }
    }

    /**
     * Returns true if a given string is a valid note.
     */
    public static boolean isValidNote(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if this note represents an empty/cleared note.
     */
    public boolean isEmpty() {
        if (value.equals(EMPTY_NOTE_PLACEHOLDER)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the timestamp when this note was last edited, or null if unknown/cleared.
     */
    public LocalDateTime getLastEdited() {
        if (lastEdited == null) {
            return null;
        } else {
            return lastEdited;
        }
    }

    /**
     * Returns a human-readable timestamp suitable for display, or empty string if none.
     */
    public String getFormattedLastEdited() {
        if (lastEdited == null) {
            return "";
        } else {
            return lastEdited.format(DISPLAY_FORMATTER);
        }
    }

    /**
     * Returns a display-friendly string including last edited timestamp.
     */
    public String toDisplayString() {
        if (isEmpty()) {
            return EMPTY_NOTE_PLACEHOLDER;
        } else {
            return value + " (Last edited: " + getFormattedLastEdited() + ")";
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Note)) {
            return false;
        }
        Note otherNote = (Note) other;

        // Ignore lastEdited for equality; only compare the note value
        if (value.equals(otherNote.value)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
