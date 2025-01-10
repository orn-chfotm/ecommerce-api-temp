package com.build.core.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocaDateTimeUtil {

    public final static String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String toString(LocalDateTime localDateObject) {
        return localDateObject.format(DateTimeFormatter.ofPattern(TIMESTAMP_PATTERN));
    }

    public static LocalDateTime toLocalDate(String localDateString) {
        return LocalDateTime.parse(localDateString, DateTimeFormatter.ofPattern(TIMESTAMP_PATTERN));
    }

    public static String nowToString() {
        return LocaDateTimeUtil.toString(LocalDateTime.now());
    }

}
