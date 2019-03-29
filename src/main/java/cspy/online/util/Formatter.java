package cspy.online.util;

/**
 * @author CSpy
 * @date 2019/3/23 20:11
 */
public class Formatter {
    public static String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() != 11) {
            return "*** **** ****";
        } else {
            return phoneNumber.substring(0, 3) + "****" + phoneNumber.substring(7);
        }
    }
}
