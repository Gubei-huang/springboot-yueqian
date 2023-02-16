package com.crazy.finance.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtils {

     //24小时毫秒数
    public static final long DAY = 86400000;

    public static String DataToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String s = dateFormat.format(date);
        return s;
    }

    /**
     * 日期差值
     * @param startTime 开始日期
     * @param date 结束日期
     * @return 结果天数
     */
    public static BigDecimal subtract(Date startTime, Date date) {
        long time = startTime.getTime();
        long time1 = date.getTime();
        long sub = time1 - time;
        long div = sub / DAY;
        BigDecimal bigDecimal = BigDecimal.valueOf(div);
        return bigDecimal;
    }
}
