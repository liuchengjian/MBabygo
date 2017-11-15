package liu.chj.mbabygo.entity;

/**
 * 作者：柳成建
 * 日期：2016/12/7 - 17:19
 * 注释：
 */
public class Follow {
    private String name;
    private int headimg;//头像
    private String text;
    public Follow(String name, int headimg,  String text ) {
        this.name =name;
        this.headimg= headimg;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeadimg() {
        return headimg;
    }

    public void setHeadimg(int headimg) {
        this.headimg = headimg;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
