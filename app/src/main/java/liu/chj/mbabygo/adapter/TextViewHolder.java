package liu.chj.mbabygo.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import liu.chj.mbabygo.R;

/**
 * 作者：柳成建
 * 日期：2016/11/24 - 14:18
 * 注释：
 */
public class TextViewHolder extends TypeAbstractViewHolder<String>{
    public TextView textView;

    public TextViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.tv);
    }

    @Override
    public void bindHolder(String text){
        textView.setText("");
    }

}
