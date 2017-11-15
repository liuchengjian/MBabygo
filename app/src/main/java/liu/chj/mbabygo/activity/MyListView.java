package liu.chj.mbabygo.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 作者：柳成建
 * 日期：2016/11/9 - 16:48
 * 注释：嵌套在ScrollView里面的listview的重写方法
 */
public class MyListView extends ListView{
    public MyListView(Context context) {
        super(context);
    }
    public MyListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
