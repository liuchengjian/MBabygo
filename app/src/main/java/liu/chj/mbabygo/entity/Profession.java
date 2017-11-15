package liu.chj.mbabygo.entity;

/**
 * 作者：柳成建
 * 日期：2016/12/27 - 9:58
 * 注释：行业
 */
public class Profession {
    private String profession;//显示的数据

    public Profession(String profession) {
        this.profession = profession;
    }
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
