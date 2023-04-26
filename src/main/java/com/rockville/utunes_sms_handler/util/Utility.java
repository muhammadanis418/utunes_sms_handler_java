package com.rockville.utunes_sms_handler.util;

public class Utility {
    public static String normalizeMsisdn(String msisdn) {
        String normalizedMsisdn = msisdn;
        if (msisdn.startsWith("0092")) {
            normalizedMsisdn = msisdn.substring(4);
        } else if (msisdn.startsWith("92")) {
            normalizedMsisdn = msisdn.substring(2);
        } else if (msisdn.startsWith("0")) {
            normalizedMsisdn = msisdn.substring(1);
        }
        return normalizedMsisdn;
    }
}
