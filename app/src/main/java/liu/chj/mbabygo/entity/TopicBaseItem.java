package liu.chj.mbabygo.entity;

/**
 * 作者：柳成建
 * 日期：2017/1/5 - 15:14
 * 注释：社区数据基类
 */
public class TopicBaseItem {
    private int item_type = 0;
    public TopicBaseItem(int item_type) {
        this.item_type = item_type;
    }
    public int getItem_type() {
        return item_type;
    }

    public void setItem_type(int item_type) {
        this.item_type = item_type;
    }
}
