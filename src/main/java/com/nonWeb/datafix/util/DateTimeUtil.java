package com.nonWeb.datafix.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    public static String getLocalDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMM, dd yyyy HH:mm:ss");

        return LocalDateTime.now().format(formatter);
    }
}
