package liu.chj.mbabygo.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：柳成建
 * 日期：2017/1/9 - 14:19
 * 注释：自定义Viewpager，解决viewpager嵌套高德地图手势问题
 */
public class GDMapViewPager extends ViewPager{
    public GDMapViewPager(Context context) {
        super(context);
    }

    public GDMapViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if(v.getClass().getName().equals("com.amap.api.maps.MapView")) {
            return true;
        }
        //if(v instanceof MapView){
        //    return true;
        //}
        return super.canScroll(v, checkV, dx, x, y);
    }
}
