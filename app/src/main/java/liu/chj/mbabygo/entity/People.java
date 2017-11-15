package liu.chj.mbabygo.entity;

/**
 * 作者：柳成建
 * 日期：2016/12/5 - 10:55
 * 注释：关注的人 实体类
 */
public class People {
    private String name;//显示的数据
    private String headimg;//头像
    private String sortLetters;//显示数据拼音的首字母

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }
}
