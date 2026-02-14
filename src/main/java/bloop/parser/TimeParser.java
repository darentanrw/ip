package bloop.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TimeParser class for parsing time strings into LocalDate objects.
 */
public class TimeParser {
    /**
     * Parses a time string into a LocalDate object.
     * 
     * @param timeString the time string to parse (iin YYYY-MM-DD format)
     * @return the LocalDate object
     */
    public static LocalDate parseDate(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(timeString, formatter);
    }

    /**
     * Parses a time string into a LocalDateTime object.
     * 
     * @param timeString the time string to parse (in YYYY-MM-DD HHmm format)
     * @return the LocalDateTime object
     */
    public static LocalDateTime parseDateTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
        return LocalDateTime.parse(timeString, formatter);
    }

    /**
     * Formats a LocalDate object into a string.
     * 
     * @param date the LocalDate object to format
     * @return the formatted string
     */
    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(formatter);
    }

    /**
     * Formats a LocalDateTime object into a string.
     * 
     * @param dateTime the LocalDateTime object to format
     * @return the formatted string in MMM dd yyyy hh:mm a format
     */
    public static String toString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return dateTime.format(formatter);
    }

    /**
     * Formats a LocalDateTime object into a string.
     * 
     * @param dateTime the LocalDateTime object to format
     * @return the formatted string in d/MM/YYYY HHmm format
     */
    public static String toStorage(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return dateTime.format(formatter);
    }
}
