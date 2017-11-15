package liu.chj.mbabygo.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 作者：柳成建
 * 日期：2017/1/19 - 13:59
 * 注释：
 */
public class InputMethodUtil {
    public static Context mContext;
    public static Handler inputHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            View view = (View) msg.obj;
            showInput(mContext, view);
        };
    };

    /**
     *            ： onCreate()  中不能马上弹出，需要稍微延时
     */
    public static void showInputMethod(Context context, View view,
                                       long delayMillis) {
        mContext = context;
        Message msg = inputHandler.obtainMessage();
        msg.obj = view;
        inputHandler.sendMessageDelayed(msg, delayMillis);
    }

    private static void showInput(Context context, View view) {
        InputMethodManager im = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        im.showSoftInput(view, 0);

    }

    /**
     *            关闭输入法，需要一个activity
     */
    public static void closeInputMethod(Activity context) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(context
                            .getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);

        } catch (Exception e) {
            // TODO: handle exception
            Log.d("", "关闭输入法异常");
        }
    }
}
