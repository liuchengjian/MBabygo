package liu.chj.mbabygo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.activity.CommentArticleActivity;
import liu.chj.mbabygo.activity.RecommendArticleActivity;
import liu.chj.mbabygo.entity.Doyen;

/**
 * 作者：柳成建
 * 日期：2016/12/13 - 14:32
 * 注释：
 */
public class DoyenHotAdapter extends BaseAdapter<Doyen>{
    Context context;
    public DoyenHotAdapter(Context context, List<Doyen> data) {
        super(context, data);
        // TODO Auto-generated constructor stub
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Doyen doyen = getData().get(position);
        ViewHolder holder;
        if(convertView ==null){
            convertView  = getLayoutInflater().inflate(R.layout.list_doyen_hot, null);
            holder = new ViewHolder();
            holder.name = (TextView)convertView.findViewById(R.id.list_doyen_hot_name);
            holder.formdoyen = (TextView)convertView.findViewById(R.id.list_doyen_hot_from);
            holder.topic = (TextView)convertView.findViewById(R.id.tv_doyen_hot_topic);
            holder.title = (TextView)convertView.findViewById(R.id.list_doyen_hot_title);
            holder.text = (TextView)convertView.findViewById(R.id.tv_doyen_hot_text);
            holder.comment = (TextView)convertView.findViewById(R.id.tv_doyen_hot_comment);
            holder.transmit = (TextView)convertView.findViewById(R.id.tv_doyen_hot_transmit);
            holder.praise = (TextView)convertView.findViewById(R.id.tv_doyen_hot_praise);
            holder.ivHead = (ImageView) convertView.findViewById(R.id.iv_doyen_hot_Photo);
            holder.ivBackground = (ImageView) convertView.findViewById(R.id.iv_doyen_hot_background);
            LinearLayout ll_comment = (LinearLayout) convertView.findViewById(R.id.ll_doyen_hot_comment);
            LinearLayout ll_transmit = (LinearLayout) convertView.findViewById(R.id.ll_doyen_hot_transmit);
            LinearLayout ll_praise = (LinearLayout) convertView.findViewById(R.id.ll_doyen_hot_praise);
            ll_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), CommentArticleActivity.class);
                    getContext().startActivity(intent);
                }
            });
            ll_transmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),"转发",Toast.LENGTH_LONG).show();
                }
            });
            ll_praise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),"点赞",Toast.LENGTH_LONG).show();
                }
            });
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        }else{
            holder = (ViewHolder) convertView.getTag();

        }
        if (position==0){
            holder.name.setTextColor(convertView.getResources().getColor(R.color.doyen_one_text));
            holder.formdoyen.setTextColor(convertView.getResources().getColor(R.color.doyen_one_text));
        }
        holder.name.setText(doyen.getName());
        holder.formdoyen.setText("_"+doyen.getFormdoyen());
        holder.topic.setText(doyen.getTopic());
        holder.title.setText("【"+doyen.getTitle()+"】");
        holder.text.setText(doyen.getText());
        holder.comment .setText(doyen.getComment());
        holder.transmit .setText(doyen.getTransmit());
        holder.praise .setText(doyen.getPraise());
        holder.ivHead.setImageResource(R.mipmap.yuanyuanma1);
        holder.ivBackground.setImageResource(R.mipmap.yuanyuanma2);

        return convertView;
    }
    class ViewHolder{
        TextView name;//达人圈最热用户名
        TextView formdoyen;//来自什么达人
        TextView topic;//达人圈最热话题icon
        TextView title;//达人圈最热文章标题
        TextView text;//达人圈最热文章标题
        TextView comment;//评论数
        TextView transmit;//达人圈最热文章标题
        TextView praise;//点赞数
        ImageView ivHead;//头像
        ImageView ivBackground;//背景图片
    }
}
