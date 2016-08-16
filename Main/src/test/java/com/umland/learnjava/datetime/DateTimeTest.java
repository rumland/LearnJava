package com.umland.learnjava.datetime;

import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Going through exercises here:
 *     https://javarevisited.blogspot.com/2015/03/20-examples-of-date-and-time-api-from-Java8.html
 */
public class DateTimeTest {
    @Test
    public void getTodaysDateTest() {
        LocalDate today = LocalDate.now();
        System.out.println("Today's local date: " + today);
    }

    @Test
    public void getCurrentDayMonthYearTest() {
        LocalDate today = LocalDate.now();
        int day = today.getDayOfMonth();
        int month = today.getMonthValue();
        int year = today.getYear();
        System.out.printf("Day: %s; Month: %s; Year: %s%n", day, month, year);
    }

    @Test
    public void getParticularDate() {
        LocalDate raceDay = LocalDate.of(2017, 4, 7);
        System.out.println("Arizona trail race day: " + raceDay);
    }

    @Test
    public void checkIfTwoDatesAreEqual() {
        LocalDate today = LocalDate.of(2016, 8, 9);
        LocalDate raceDay = LocalDate.of(2017, 4, 7);

        Assert.assertNotEquals(today, raceDay);
        Assert.assertNotEquals(raceDay, today);
        Assert.assertEquals(today, today);
        Assert.assertEquals(raceDay, raceDay);
    }

    @Test
    public void checkRecurringDatesTest() {
        LocalDate dateOfBirth = LocalDate.of(1983, 8, 1);
        LocalDate today = LocalDate.now();
        Month birthMonth = Month.of(dateOfBirth.getMonthValue());
        Month currentMonth = Month.from(today);
        MonthDay birthday = MonthDay.of(dateOfBirth.getMonthValue(), dateOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(today);

        if (birthMonth.equals(currentMonth) && currentMonthDay.equals(birthday)) {
            System.out.println("Your birthday is today :-)");
        } else {
            System.out.println("Sorry, your birthday is _not_ today :-(");
        }
    }

    @Test
    public void getCurrentTimeTest() {
        LocalTime now = LocalTime.now();
        System.out.println("Current time: " + now);
    }

    @Test
    public void addHoursInTimeTest() {
        LocalTime now = LocalTime.now();
        int hoursToAdd = 2;
        LocalTime plusHours = now.plusHours(hoursToAdd);
        System.out.println("Current time: " + now);
        System.out.printf(String.format("Time in %s hours: %s%n", hoursToAdd, plusHours));
    }

    @Test
    public void findDateAfterOneWeekTest() {
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.printf("Today is %s%n", today);
        System.out.printf("Next week is %s%n", nextWeek);
    }

    @Test
    public void yearBeforeAndAfterTest() {
        LocalDate today = LocalDate.now();
        LocalDate lastYear = today.minus(1, ChronoUnit.YEARS);
        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.printf("Today is %s%n", today);
        System.out.printf("Last year is %s%n", lastYear);
        System.out.printf("Next year is %s%n", nextYear);
    }

    @Test
    public void clockTest() {
        Clock clock = Clock.systemUTC();
        System.out.println("Clock: " + clock);

        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Default clock: " + defaultClock);
    }

    @Test
    public void checkDateBeforeOrAfterAnotherDateTest() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        LocalDate yesterday = today.plusDays(-1);

        Assert.assertTrue(tomorrow.isAfter(today));
        Assert.assertTrue(tomorrow.isAfter(yesterday));
        Assert.assertTrue(yesterday.isBefore(today));
        Assert.assertTrue(yesterday.isBefore(tomorrow));
    }

    @Test
    public void timeZonesJava8Test() {
        ZoneId newYorkTimeZone = ZoneId.of("America/New_York");
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localDateTime, newYorkTimeZone);

        System.out.printf("Current date and time in New York: %s.%n", dateAndTimeInNewYork);
    }

    @Test
    public void timeZonesJava7Test() {
        //Next line is deprecated. Use GregorianCalendar instead. This is documented here:
        //    http://docs.oracle.com/javase/6/docs/api/java/util/Date.html
        // Date localDate = new Date(2016, 7, 16, 5, 52, 43);
        Date localDate = new GregorianCalendar(2016, 7, 16, 5, 52, 43).getTime();
        DateFormat converter = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
        converter.setTimeZone(TimeZone.getTimeZone("America/New_York"));

        System.out.printf("Current date and time here: %s.%n", localDate);
        Assert.assertEquals("Tue Aug 16 05:52:43 MDT 2016", localDate.toString());
        System.out.printf("Current date and time in New York: %s.%n", converter.format(localDate));
        Assert.assertEquals("16/08/2016:07:52:43", converter.format(localDate));
    }

    @Test
    public void representFixedDateTest() {
        YearMonth currentYearMonth = YearMonth.of(2016, 8);
        Assert.assertEquals(31, currentYearMonth.lengthOfMonth());

        YearMonth creditCardExpiry = YearMonth.of(2018, Month.APRIL);
        Assert.assertEquals("2018-04", creditCardExpiry.toString());
    }

    @Test
    public void checkLeapYearTest() {
        LocalDate leapDate = LocalDate.of(2020, 2, 29);
        LocalDate standardDate = LocalDate.of(2017, 8, 16);

        Assert.assertTrue(leapDate.isLeapYear());
        boolean leapYear = standardDate.isLeapYear();
        Assert.assertFalse(String.format("%s not expected to be a leap year.", standardDate), leapYear);
    }

    @Test
    public void daysMonthsBeforeArizonaTrailRaceTest() {
        LocalDate today = LocalDate.now();
        LocalDate raceDay = LocalDate.of(2017, 4, 7);
        Period period = Period.between(today, raceDay);
        System.out.println("Arizona trail race day: " + raceDay);
        System.out.printf("Years before race: %s%n", period.getYears());
        System.out.printf("Months before race: %s%n", period.getMonths());
        System.out.printf("Days before race: %s%n", period.getDays());
    }

    /**
     * OffsetDateTime is meant for machines. For human dates prefer ZoneDateTime.
     */
    @Test
    public void dateAndTimeWithTimezoneOffsetTest() {
        LocalDateTime dateTime = LocalDateTime.of(2014, Month.JANUARY, 14, 19, 30);
        ZoneOffset offset = ZoneOffset.of("+05:30"); //India offset GMT or UTC +05:30
        OffsetDateTime offsetDateTime = OffsetDateTime.of(dateTime, offset);
        System.out.printf("Date and Time with timezone in India: %s.%n", offsetDateTime);
        Assert.assertEquals("2014-01-14T19:30+05:30", offsetDateTime.toString());
    }

    @Test
    public void currentTimestampTest() {
        Instant timestamp = Instant.now();
        System.out.printf("Value of instant: %s.%n", timestamp);
    }

    @Test
    public void parseAndFormatDateWithPredefinedFormattingTest() {
        String dayAfterTomorrow = "20160818";
        LocalDate formatted = LocalDate.parse(dayAfterTomorrow, DateTimeFormatter.BASIC_ISO_DATE);
        Assert.assertEquals("2016-08-18", formatted.toString());
    }

    @Test
    public void parseAndFormatDateWithCustomFormattingTest() {
        String goodFriday = "Apr 18 2014";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            LocalDate holiday = LocalDate.parse(goodFriday, formatter);
            Assert.assertEquals("2014-04-18", holiday.toString());
        } catch (DateTimeParseException ex) {
            System.out.printf("Could not parse '%s'.%n", goodFriday);
            throw ex;
        }
    }

    @Test
    public void convertDateToStringTest() {
        LocalDateTime arrivalDate = LocalDateTime.of(2016, 8, 18, 15, 30, 0);
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            String landing = arrivalDate.format(format);
            Assert.assertEquals("Aug 18 2016 03:30 PM", landing);
        } catch (DateTimeException ex) {
            System.out.printf("%s cannot be formated.%n", arrivalDate);
        }
    }
}
