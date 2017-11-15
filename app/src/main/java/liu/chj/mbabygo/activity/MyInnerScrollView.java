package liu.chj.mbabygo.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * 作者：柳成建
 * 日期：2016/11/25 - 14:08
 * 注释：
 */
public class MyInnerScrollView extends ScrollView{

    public MyInnerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    private int mLastY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int y = (int) ev.getY();
        View childView = getChildAt(0);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mLastY-y> 0&&childView != null && childView.getMeasuredHeight() <= getScrollY() + getHeight()) {
                    return false;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
}
