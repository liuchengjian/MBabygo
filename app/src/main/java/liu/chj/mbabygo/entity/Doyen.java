package liu.chj.mbabygo.entity;

/**
 * 作者：柳成建
 * 日期：2016/12/13 - 14:33
 * 注释：达人圈实体类
 */
public class Doyen {
    private String name;//名字
    private String formdoyen;//来自什么达人
    private String topic;//话题
    private String title;//标题
    private String text;//正文
    private String transmit;//转发数
    private String comment;//评论数
    private String praise;//点赞数
    private String imghead;//头像
    private String imgbackground;//背景图
    public Doyen() {
    }

    public Doyen(String name,String formdoyen,String topic,String title,
                 String text,String transmit,String comment,String praise,
                 String imghead, String imgbackground ) {
        this.name =name;
        this.formdoyen =formdoyen;
        this.topic =topic;
        this.title =title;
        this.text =text;
        this.transmit =transmit;
        this.comment =comment;
        this.praise =praise;
        this.imghead =imghead;
        this.imgbackground =imgbackground;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormdoyen() {
        return formdoyen;
    }

    public void setFormdoyen(String formdoyen) {
        this.formdoyen = formdoyen;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTransmit() {
        return transmit;
    }

    public void setTransmit(String transmit) {
        this.transmit = transmit;
    }

    public String getPraise() {
        return praise;
    }

    public void setPraise(String praise) {
        this.praise = praise;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImghead() {
        return imghead;
    }

    public void setImghead(String imghead) {
        this.imghead = imghead;
    }

    public String getImgbackground() {
        return imgbackground;
    }

    public void setImgbackground(String imgbackground) {
        this.imgbackground = imgbackground;
    }
}
