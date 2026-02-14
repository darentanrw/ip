package bloop.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeParserTest {

    @Test
    void parseDate_validDate_returnsLocalDate() {
        LocalDate result = TimeParser.parseDate("2025-12-25");
        assertEquals(2025, result.getYear());
        assertEquals(12, result.getMonthValue());
        assertEquals(25, result.getDayOfMonth());
    }

    @Test
    void parseDate_invalidFormat_throwsException() {
        assertThrows(DateTimeParseException.class, () -> TimeParser.parseDate("25-12-2025"));
    }

    @Test
    void parseDate_nonsenseString_throwsException() {
        assertThrows(DateTimeParseException.class, () -> TimeParser.parseDate("not-a-date"));
    }

    @Test
    void parseDateTime_validDateTime_returnsLocalDateTime() {
        LocalDateTime result = TimeParser.parseDateTime("1/1/2025 1800");
        assertEquals(2025, result.getYear());
        assertEquals(1, result.getMonthValue());
        assertEquals(1, result.getDayOfMonth());
        assertEquals(18, result.getHour());
        assertEquals(0, result.getMinute());
    }

    @Test
    void parseDateTime_doubleDigitMonthAndDay_parsesCorrectly() {
        LocalDateTime result = TimeParser.parseDateTime("12/25/2025 0930");
        assertEquals(12, result.getMonthValue());
        assertEquals(25, result.getDayOfMonth());
        assertEquals(9, result.getHour());
        assertEquals(30, result.getMinute());
    }

    @Test
    void parseDateTime_midnight_parsesCorrectly() {
        LocalDateTime result = TimeParser.parseDateTime("6/15/2025 0000");
        assertEquals(0, result.getHour());
        assertEquals(0, result.getMinute());
    }

    @Test
    void parseDateTime_invalidFormat_throwsException() {
        assertThrows(DateTimeParseException.class, () -> TimeParser.parseDateTime("2025-01-01 18:00"));
    }

    @Test
    void formatDate_typicalDate_formatsCorrectly() {
        LocalDate date = LocalDate.of(2025, 12, 25);
        assertEquals("Dec 25 2025", TimeParser.formatDate(date));
    }

    @Test
    void formatDate_singleDigitDay_padsWithZero() {
        LocalDate date = LocalDate.of(2025, 1, 5);
        assertEquals("Jan 05 2025", TimeParser.formatDate(date));
    }

    @Test
    void toString_morningTime_formatsWithAm() {
        LocalDateTime dt = LocalDateTime.of(2025, 3, 15, 9, 30);
        String result = TimeParser.toString(dt);
        assertTrue(result.equalsIgnoreCase("Mar 15 2025 09:30 AM"));
    }

    @Test
    void toString_afternoonTime_formatsWithPm() {
        LocalDateTime dt = LocalDateTime.of(2025, 3, 15, 14, 0);
        String result = TimeParser.toString(dt);
        assertTrue(result.equalsIgnoreCase("Mar 15 2025 02:00 PM"));
    }

    @Test
    void toString_midnight_formatsAs12Am() {
        LocalDateTime dt = LocalDateTime.of(2025, 6, 1, 0, 0);
        String result = TimeParser.toString(dt);
        assertTrue(result.equalsIgnoreCase("Jun 01 2025 12:00 AM"));
    }

    @Test
    void toString_noon_formatsAs12Pm() {
        LocalDateTime dt = LocalDateTime.of(2025, 6, 1, 12, 0);
        String result = TimeParser.toString(dt);
        assertTrue(result.equalsIgnoreCase("Jun 01 2025 12:00 PM"));
    }

    @Test
    void toStorage_typicalDateTime_formatsCorrectly() {
        LocalDateTime dt = LocalDateTime.of(2025, 3, 15, 14, 30);
        assertEquals("15/03/2025 1430", TimeParser.toStorage(dt));
    }

    @Test
    void toStorage_singleDigitDay_noLeadingZero() {
        LocalDateTime dt = LocalDateTime.of(2025, 1, 5, 9, 0);
        assertEquals("5/01/2025 0900", TimeParser.toStorage(dt));
    }

    @Test
    void toStorage_parseThenStore_producesExpectedFormat() {
        LocalDateTime parsed = TimeParser.parseDateTime("3/15/2025 1430");
        String stored = TimeParser.toStorage(parsed);
        assertEquals("15/03/2025 1430", stored);
    }
}
