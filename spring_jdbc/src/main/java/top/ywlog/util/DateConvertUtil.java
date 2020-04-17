package top.ywlog.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @Author: Durian
 * @Date: 2020/4/14 13:03
 * @Description: LocalDateTime转换
 */
public class DateConvertUtil
{
    public static LocalDateTime timestampToLocalDateTime(Timestamp timestamp)
    {
        Instant instant = timestamp.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static LocalDateTime dateToLocalDateTime(Date date)
    {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static Timestamp localDateTimeToTimeStamp(LocalDateTime localDateTime)
    {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        return Timestamp.from(zonedDateTime.toInstant());
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime)
    {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }
}
