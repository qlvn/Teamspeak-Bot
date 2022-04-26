package de.joel.teamspeakbot.file;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Data {

    public static String getprefix() {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("[HH:mm:ss] ");
        return simpleDateFormat.format(Calendar.getInstance().getTime());
    }
}