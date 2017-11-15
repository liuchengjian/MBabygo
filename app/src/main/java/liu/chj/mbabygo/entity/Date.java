package liu.chj.mbabygo.entity;

import java.sql.Time;

/**
 * 作者：柳成建
 * 日期：2016/12/12 - 15:18
 * 注释：
 */
public class Date {
    private String time;
    public Date() {
    }
    public Date(String time ) {
        this.time =time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
