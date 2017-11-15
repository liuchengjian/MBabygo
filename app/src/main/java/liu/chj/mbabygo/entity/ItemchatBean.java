package liu.chj.mbabygo.entity;

import java.util.List;

/**
 * 作者：柳成建
 * 日期：2017/1/5 - 16:18
 * 注释：
 */
public class ItemchatBean extends TopicBaseItem{
    public Avatar avatar;
    private String name = null;
    private String from = null;
    private String title = null;
    private String transmit = null;
    private String comment = null;
    private String praise = null;
    public List<ChatPic> chatimg; // 说说图片列表


    public ItemchatBean(int item_type,
                        String name, String from, String title, String transmit, String comment, String praise,List<ChatPic> chatimg) {
        super(item_type);
        this.name = name;
        this.from = from;
        this.title  = title ;
        this.transmit  = transmit ;
        this.comment  = comment ;
        this.praise  = praise ;
        this.chatimg  = chatimg ;
    }
    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTransmit() {
        return transmit;
    }
    public void setTransmit(String transmit) {
        this.transmit = transmit;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getPraise() {
        return praise;
    }
    public void setPraise(String praise) {
        this.praise = praise;
    }

    public List<ChatPic> getChatimg() {
        return chatimg;
    }

    public void setChatimg(List<ChatPic> chatimg) {
        this.chatimg = chatimg;
    }
}
