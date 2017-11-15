package liu.chj.mbabygo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.entity.Article;
import liu.chj.mbabygo.entity.Routing;

/**
 * 作者：柳成建
 * 日期：2016/11/15 - 13:45
 * 注释：首页活动行程Adapter
 */
public class ActivityRoutingAdapter extends BaseAdapter<Routing>{
    public ActivityRoutingAdapter(Context context, List<Routing> data) {
        super(context, data);
        // TODO Auto-generated constructor stub
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Routing routing = getData().get(position);
        ViewHolder holder;
        if(convertView ==null){
            convertView  = getLayoutInflater().inflate(R.layout.list_activity_routing, null);
            holder = new ViewHolder();
            holder.bt_day = (Button) convertView.findViewById(R.id.bt_day);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();

        }
        holder.bt_day.setText(routing.getDay());

        return convertView;
    }
    class ViewHolder{
        Button bt_day;//天数
    }
}
