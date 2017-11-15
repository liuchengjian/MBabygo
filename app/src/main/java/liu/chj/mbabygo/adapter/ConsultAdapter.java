package liu.chj.mbabygo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.Date;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.activity.Time;
import liu.chj.mbabygo.entity.Consult;

/**
 * 作者：柳成建
 * 日期：2016/12/12 - 10:53
 * 注释：咨询聊天adapter
 */
public class ConsultAdapter extends BaseAdapter<Consult>{
    Long timecurrentTimeMillis = System.currentTimeMillis();
    Date date = new Date(System.currentTimeMillis());
    public ConsultAdapter(Context context, List<Consult> data) {
        super(context, data);
        // TODO Auto-generated constructor stub
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Consult consult = getData().get(position);
        ViewHolder holder;
        if(convertView ==null){
            convertView  = getLayoutInflater().inflate(R.layout.list_consult, null);
            holder = new ViewHolder();
            holder.ask = (TextView) convertView.findViewById(R.id.tv_ask);
            holder.answer = (TextView) convertView.findViewById(R.id.tv_answer);
            holder.iv_ask = (ImageView) convertView.findViewById(R.id.iv_ask);
            holder.iv_answer = (ImageView) convertView.findViewById(R.id.iv_answer);
            convertView.setTag(holder);
            holder.ConsultDate = (TextView) convertView.findViewById(R.id.tv_consult_date);
            AutoUtils.autoSize(convertView);
        }else{
            holder = (ViewHolder) convertView.getTag();

        }
        if(position%2==1){
            holder.ask.setText(consult.getAsk());
            holder.iv_ask.setImageResource(R.mipmap.icon_ask);
            holder.answer.setVisibility(View.GONE);
            holder.iv_answer.setVisibility(View.GONE);

            holder.ConsultDate.setText(consult.getDate());
        }else{
            holder.ask.setText(consult.getAsk());
            holder.iv_ask.setImageResource(R.mipmap.icon_ask);
            holder.answer.setText(consult.getAnswer());
            holder.iv_answer.setImageResource(R.mipmap.icon_answer);
            holder.ConsultDate.setText(consult.getDate());
        }

        return convertView;
    }
    class ViewHolder{
        TextView ask;//问
        ImageView iv_ask;
        TextView answer;//答
        ImageView iv_answer;
        TextView ConsultDate;//时间
    }
}
