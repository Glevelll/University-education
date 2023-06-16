package org.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Test {

    public static void main(String args[]) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy");
        Calendar calendar = new GregorianCalendar(2023, Calendar.MARCH , 22);
        System.out.println(dateFormat.format(calendar.getTime()));
    }
}