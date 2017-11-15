package liu.chj.mbabygo.entity;

/**
 * 作者：柳成建
 * 日期：2016/12/12 - 10:55
 * 注释：咨询实体类
 */
public class Consult {
    private String ask;
    private String answer;
    private String date;
    public Consult() {
        super();
    }

    public Consult(String  ask, String answer,String date) {
        this. ask = ask;
        this.answer= answer;
        this.date= date;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
