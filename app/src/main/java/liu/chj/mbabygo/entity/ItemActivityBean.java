package liu.chj.mbabygo.entity;

/**
 * 作者：柳成建
 * 日期：2017/1/5 - 16:18
 * 注释：
 */
public class ItemActivityBean extends TopicBaseItem{
    private int photo ;
    private int backgroundimg;
    private String name = null;
    private String from = null;
    private String title = null;
    private String transmit = null;
    private String comment = null;
    private String praise = null;


    public ItemActivityBean(int item_type, int photo,int backgroundimg,
                            String name, String from,String title,String transmit,String comment,String praise) {
        super(item_type);
        this.photo = photo;
        this.backgroundimg= backgroundimg;
        this.name = name;
        this.from = from;
        this.title  = title ;
        this.transmit  = transmit ;
        this.comment  = comment ;
        this.praise  = praise ;
    }
    public int getPhoto() {
        return photo;
    }
    public void setPhoto(int photo) {
        this.photo = photo;
    }
    public int getBackgroundimg() {
        return backgroundimg;
    }
    public void setBackgroundimg(int backgroundimg) {
        this.backgroundimg = backgroundimg;
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
}
