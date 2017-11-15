package liu.chj.mbabygo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

import liu.chj.mbabygo.R;

/**
 * 作者：柳成建
 * 日期：2016/12/2 - 14:37
 * 注释：
 */
public class PopupAdapter extends android.widget.BaseAdapter{
    private Context context;

    private List<String> list;

    public PopupAdapter(Context context, List<String> list) {

        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {


        ViewHolder holder;
        if (convertView==null) {
            convertView= LayoutInflater.from(context).inflate(R.layout.popup_list, null);
            holder=new ViewHolder();

            convertView.setTag(holder);

            holder.groupItem=(TextView) convertView.findViewById(R.id.tv_hot_or_time);

        }
        else{
            holder=(ViewHolder) convertView.getTag();
        }
        holder.groupItem.setText(list.get(position));

        return convertView;
    }

    static class ViewHolder {
        TextView groupItem;
    }
}
