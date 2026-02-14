package utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class DateFormatterUtil {
    public static String formatTimestamp(Timestamp timestamp) {
        Instant instant = timestamp.toInstant();

        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                .withLocale(new Locale("ru"));

        String formattedDate = zonedDateTime.format(formatter);

        return formattedDate.replace("г.", " год");
    }

}
