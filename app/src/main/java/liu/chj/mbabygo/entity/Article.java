package liu.chj.mbabygo.entity;

/**
 * 作者：柳成建
 * 日期：2016/11/15 - 14:06
 * 注释：首页推荐文章实体类
 */
public class Article {
    private String img;//头像
    private String name;//用户名
    private String title;//标题
    private String date;//日期和星期
    private String imgBackgound;//背景图

    public Article(String img, String name, String title, String date, String imgBackgound) {
        this.img = img;
        this.name = name;
        this.title = title;
        this.date = date;
        this.imgBackgound = imgBackgound;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImgBackgound() {
        return imgBackgound;
    }
    public void setImgBackgound(String imgBackgound) {
        this.imgBackgound = imgBackgound;
    }
}
