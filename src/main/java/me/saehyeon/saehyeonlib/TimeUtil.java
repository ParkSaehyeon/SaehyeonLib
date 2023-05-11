package me.saehyeon.saehyeonlib;

public class TimeUtil {
    public static String secondsToTimeStr(long seconds,String splitStr) {
        long h = (seconds % 3600); // 분
        long m = (seconds % 3600) / 60; // 분
        long s = seconds % 60; // 초

        return String.format("%02d%s%02d%s%02d", h,splitStr, m, splitStr, s);
    }

    public static long[] secondsToTime(long seconds) {
        long h = (seconds % 3600); // 분
        long m = (seconds % 3600) / 60; // 분
        long s = seconds % 60; // 초

        return new long[] { h, m, s };
    }
}
