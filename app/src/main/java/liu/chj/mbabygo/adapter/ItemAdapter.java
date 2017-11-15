package liu.chj.mbabygo.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.ui.BaseRecyclerAdapter;
import liu.chj.mbabygo.ui.BaseRecyclerHolder;

/**
 * @ explain:
 * @ author：xujun on 2016/10/18 16:42
 * @ email：gdutxiaoxu@163.com
 */
public class ItemAdapter extends BaseRecyclerAdapter<String> {

    public ItemAdapter(Context context, List<String> datas) {
        super(context, R.layout.item_string, datas);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, String item, int position) {
        TextView  tv=holder.getView(R.id.tv);
        tv.setText(item);
    }
}
