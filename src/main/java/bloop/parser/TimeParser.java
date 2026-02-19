package bloop.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TimeParser class for parsing time strings into LocalDate objects.
 */
public class TimeParser {
    private static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter INPUT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter OUTPUT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
    private static final DateTimeFormatter STORAGE_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    /**
     * Parses a time string into a LocalDate object.
     *
     * @param timeString the time string to parse (iin YYYY-MM-DD format)
     * @return the LocalDate object
     */
    public static LocalDate parseDate(String timeString) {
        return LocalDate.parse(timeString, INPUT_DATE_FORMATTER);
    }

    /**
     * Parses a time string into a LocalDateTime object.
     *
     * @param timeString the time string to parse (in YYYY-MM-DD HHmm format)
     * @return the LocalDateTime object
     */
    public static LocalDateTime parseDateTime(String timeString) {
        return LocalDateTime.parse(timeString, INPUT_DATETIME_FORMATTER);
    }

    /**
     * Formats a LocalDate object into a string.
     *
     * @param date the LocalDate object to format
     * @return the formatted string
     */
    public static String formatDate(LocalDate date) {
        return date.format(OUTPUT_DATE_FORMATTER);
    }

    /**
     * Formats a LocalDateTime object into a string.
     *
     * @param dateTime the LocalDateTime object to format
     * @return the formatted string in MMM dd yyyy hh:mm a format
     */
    public static String toString(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_DATETIME_FORMATTER);
    }

    /**
     * Formats a LocalDateTime object into a string.
     *
     * @param dateTime the LocalDateTime object to format
     * @return the formatted string in d/MM/YYYY HHmm format
     */
    public static String toStorage(LocalDateTime dateTime) {
        return dateTime.format(STORAGE_DATETIME_FORMATTER);
    }
}
