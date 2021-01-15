package com.xiaowen.quartz.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * @author xiaowen
 * @create 2021/1/15 22:24
 */
public class DFUtil {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String format(Date date) {
        return sdf.format(date);
    }

}