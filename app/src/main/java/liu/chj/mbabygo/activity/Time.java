package liu.chj.mbabygo.activity;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 作者：柳成建
 * 日期：2016/12/12 - 15:20
 * 注释：封装时间
 */
public class Time {

    /**
     * 相差天数
     */
    public static int daysOfTwo(Date originalDate, Date compareDateDate){
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(originalDate);
        int originalDay = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(compareDateDate);
        int compareDay = aCalendar.get(Calendar.DAY_OF_YEAR);

        return originalDay - compareDay;
    }

    public static String FriendlyDate(Date compareDate) {
        SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间形式
        Date nowDate = new Date();
        Long nowtime = nowDate.getTime();
        long date;

        int dayDiff = daysOfTwo(nowDate, compareDate);

        if (dayDiff <= 0)
            return new SimpleDateFormat("H"+"时"+"m"+"分").format(compareDate);
        else if (dayDiff == 1)
            return "1天前";
        else if (dayDiff == 2)
            return "2天前";
        else
            return new SimpleDateFormat("y-M-d").format(compareDate);
    }
}
