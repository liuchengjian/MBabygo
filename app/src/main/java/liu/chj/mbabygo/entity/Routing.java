package liu.chj.mbabygo.entity;

/**
 * 作者：柳成建
 * 日期：2016/11/28 - 10:01
 * 注释：行程实体类
 */
public class Routing {
    private String day;
    public Routing(String day) {
        this.day= day;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
