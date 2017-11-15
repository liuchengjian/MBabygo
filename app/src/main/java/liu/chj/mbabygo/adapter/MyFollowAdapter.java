package liu.chj.mbabygo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.entity.Follow;

/**
 * 作者：柳成建
 * 日期：2017/1/3 - 14:55
 * 注释：
 */
public class MyFollowAdapter extends BaseAdapter<Follow> {
    LinearLayout ll_my_follow_yes_or_no;
    /**
     * 是否关注
     */
    boolean isfollow = false;
    ViewHolder holder;
    public MyFollowAdapter(Context context, List<Follow> data) {
        super(context, data);
        // TODO Auto-generated constructor stub
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        Follow follow = getData().get(position);

        if(convertView ==null){
            convertView  = getLayoutInflater().inflate(R.layout.list_my_follow, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.tv_my_follow_name);
            holder.text = (TextView) convertView.findViewById(R.id.tv_my_follow_text);
            ll_my_follow_yes_or_no  = ( LinearLayout) convertView.findViewById(R.id.ll_my_follow_yes_or_no);
            holder. headimg= (ImageView) convertView.findViewById(R.id.iv_my_follow_headimg);
            holder.follow_yes_or_no = (ImageView) convertView.findViewById(R.id.iv_my_follow_yes_or_no);
            holder.tv_follow_yes_or_no = (TextView) convertView.findViewById(R.id.tv_my_follow_yes_or_no);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        }else{
            holder = (ViewHolder) convertView.getTag();

        }
            ll_my_follow_yes_or_no.setTag(position);
            ll_my_follow_yes_or_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("image", " u clicked on icon Position" + position);
                    holder.follow_yes_or_no.setImageResource(R.mipmap.follow_no);
                    holder.tv_follow_yes_or_no.setText("互相关注");
                    
//                    if(isfollow){
//                        holder.follow_yes_or_no.setImageResource(R.mipmap.follow_yes);
//                        isfollow=false;
//                    }else {
//                        holder.follow_yes_or_no.setImageResource(R.mipmap.follow_no);
//                        isfollow=true;
//                    }
                }
            });
            holder.name.setText(follow.getName());
            holder.text.setText(follow.getText());
            holder.headimg.setImageResource(R.mipmap.yuanyuanma2);

        return convertView;
    }
    class ViewHolder{
        TextView name;//
        TextView text;//
        ImageView headimg;
        ImageView follow_yes_or_no;
        TextView tv_follow_yes_or_no;//
        LinearLayout ll_my_follow_yes_or_no;
    }
}
