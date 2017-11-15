package liu.chj.mbabygo.entity;

/**
 * 作者：柳成建
 * 日期：2016/11/17 - 13:45
 * 注释：首页推荐活动的实体类
 */
public class Activity {
    private String title;//标题
    private String text;//短正文
    private String age;//年龄
    private String attributeone;//活动属性1
    private String attributetwo;//活动属性2

    private String date;//日期和星期
    private String publishaddress;//日期和星期
    private String person;//参与人数

    private int imgicon;//限时和热门icon图
    private int imgBackgound;//背景图
    public Activity(String title, String text, String age, String attributeone,
                    String attributetwo,String publishaddress,String date,
                    String person,int imgicon,int imgBackgound) {
        this.title =title;
        this.text = text;
        this.age = age;
        this.attributeone = attributeone;
        this.attributetwo = attributetwo;
        this.publishaddress = publishaddress;
        this.date = date;
        this.person = person;
        this.imgicon = imgicon;
        this.imgBackgound = imgBackgound;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAttributeone() {
        return attributeone;
    }

    public void setAttributeone(String attributeone) {
        this.attributeone = attributeone;
    }

    public String getAttributetwo() {
        return attributetwo;
    }

    public void setAttributetwo(String attributetwo) {
        this.attributetwo = attributetwo;
    }

    public String getDate() {
        return date;
    }

    public String getPublishaddress() {
        return publishaddress;
    }

    public void setPublishaddress(String publishaddress) {
        this.publishaddress = publishaddress;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public int getImgicon() {
        return imgicon;
    }

    public void setImgicon(int imgicon) {
        this.imgicon = imgicon;
    }

    public int getImgBackgound() {
        return imgBackgound;
    }

    public void setImgBackgound(int imgBackgound) {
        this.imgBackgound = imgBackgound;
    }
}
