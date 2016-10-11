package me.solidev.library.utils;

/**
 * Created by _SOLID
 * Date:2016/10/11
 * Time:10:31
 * Desc:
 */

public class TimeUtil {
    public String getCountShowTime(int countTime) {
        String result = "";
        if (countTime < 10)
            result = "00:0" + countTime;
        else if (countTime < 60)
            result = "00:" + countTime;
        else {
            int minute = countTime / 60;
            int mod = countTime % 60;
            if (minute < 10) result += "0" + minute + ":";
            else {
                result += minute + ":";
            }
            if (mod < 10) result += "0" + mod;
            else {
                result += mod;
            }

        }
        return result;
    }
}
