package liu.chj.mbabygo.entity;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 作者：柳成建
 * 日期：2017/1/13 - 10:40
 * 注释：
 */
public class Closetypewriting {

    /**
     * 关闭输入法
     */
    public void setclosetypewriting(Context context, EditText et){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            InputMethodManager im = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(et.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);

        }
    }
}
