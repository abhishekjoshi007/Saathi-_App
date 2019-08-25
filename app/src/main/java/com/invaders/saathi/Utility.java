package com.invaders.saathi;

public class Utility {

    public static String dateFormate = "dd MMM yyyy";

    public static String getTimeString(String time) {
        StringBuilder timeString = new StringBuilder();
        String indicator = "";
        for (char c : time.toCharArray()) {
            if (c == 'm' || c == 'M') {
                timeString.append(indicator);
                indicator = "Min";
                continue;
            }
            timeString.append(c);

        }
        timeString.append(" ");
        timeString.append(indicator);
        return timeString.toString();
    }

    public static long getPhoneNumberFromString(String phoneNumber) {
        if (phoneNumber.length() < 10)
            return 0;
        try {
            return Long.parseLong(phoneNumber.substring(phoneNumber.length()-10));
        } catch (Exception e) {
            return 0;
        }
    }
}
