package liu.chj.mbabygo.polltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class PullableScrollView extends ScrollView implements Pullable
{
//	 private boolean isCanPullDown = true;//�Ƿ�������

	public PullableScrollView(Context context)
	{
		super(context);
	}

	public PullableScrollView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public PullableScrollView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	public boolean canPullDown()
	{
		if (getScrollY() == 0)
			return true;
		else
			return false;
	}

	public boolean canPullUp()
	{
		if (getScrollY() >= (getChildAt(0).getHeight() - getMeasuredHeight()))
			return true;
		else
			return false;
	}
	
//	  public void setCanPullDown(boolean canPullDown) {
//	        isCanPullDown = canPullDown;
//	    }

}
