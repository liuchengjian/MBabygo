package liu.chj.mbabygo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.entity.Actor;

/**
 * 作者：柳成建
 * 日期：2016/12/9 - 14:01
 * 注释：联系人信息列表
 */
public class ContactsMessageAdapter extends BaseAdapter<Actor>{
    public ContactsMessageAdapter(Context context, List<Actor> data) {
        super(context, data);
        // TODO Auto-generated constructor stub
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Actor actor = getData().get(position);
       ViewHolder holder;
        if(convertView ==null){
            convertView  = getLayoutInflater().inflate(R.layout.list_contacts_message, null);
            holder = new ViewHolder();
            holder.position = (TextView) convertView.findViewById(R.id.tv_contact_message_position);
            holder.name = (TextView) convertView.findViewById(R.id.tv_contact_message_name);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        }else{
            holder = (ViewHolder) convertView.getTag();

        }
        holder.position.setText("参与人"+(position+1));
        Log.e("lchj","ssssssssssssss"+position);
        holder.name.setText(actor.getName());

        return convertView;
    }
    class ViewHolder{
        TextView position;
        TextView name;
    }
}
