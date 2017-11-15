package liu.chj.mbabygo.entity;

/**
 * 作者：柳成建
 * 日期：2016/12/7 - 17:19
 * 注释：参与人的实体类（封装姓名和身份证号）
 */
public class Actor {
    private String name;
    private String idcard;//身份证号
    private int icon;//标记
    public Actor(String name, String idcard,int icon ) {
        this.name =name;
        this.idcard = idcard;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
