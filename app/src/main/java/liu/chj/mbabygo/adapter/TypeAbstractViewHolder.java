package liu.chj.mbabygo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2016/11/9.
 */

public abstract class TypeAbstractViewHolder<T> extends RecyclerView.ViewHolder {
    public TypeAbstractViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindHolder(T dataModel);
}
