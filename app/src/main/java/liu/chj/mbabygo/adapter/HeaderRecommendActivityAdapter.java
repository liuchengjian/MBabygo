package liu.chj.mbabygo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.entity.Activity;

/**
 * 作者：柳成建
 * 日期：2016/11/15 - 13:45
 * 注释：首页推荐活动Adapter
 */
public class HeaderRecommendActivityAdapter extends BaseAdapter<Activity>{
    public HeaderRecommendActivityAdapter(Context context, List<Activity> data) {
        super(context, data);
        // TODO Auto-generated constructor stub
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Activity activity = getData().get(position);
        ViewHolder holder;
        if(convertView ==null){
            convertView  = getLayoutInflater().inflate(R.layout.list_header_recommend_activity, null);
            holder = new ViewHolder();
            holder.ivicon = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.tv_header_recommend_activity_title = (TextView)convertView.findViewById(R.id.tv_header_recommend_activity_title);
            holder.tv_header_recommend_activity_date = (TextView)convertView.findViewById(R.id.tv_header_recommend_activity_date);
            holder.tv_header_recommend_activity_person = (TextView)convertView.findViewById(R.id.tv_header_recommend_activity_person);
            holder.age = (TextView)convertView.findViewById(R.id.tv_age);
            holder.attributeone = (TextView)convertView.findViewById(R.id.tv_tributeone);
            holder.attributetwo = (TextView)convertView.findViewById(R.id.tv_tributetwo);
            holder.ivBackground = (ImageView) convertView.findViewById(R.id.iv_activity_background_image);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        }else{
            holder = (ViewHolder) convertView.getTag();

        }
        holder.ivicon.setImageResource(R.mipmap.xianshi2);
        holder.tv_header_recommend_activity_title.setText(activity.getTitle());
        holder.tv_header_recommend_activity_date.setText(activity.getPublishaddress()+" · "+activity.getDate());
        holder.tv_header_recommend_activity_person.setText(activity.getPerson());
        holder.age.setText(activity.getAge());
        holder.attributeone.setText(activity.getAttributeone());
        holder.attributetwo.setText(activity.getAttributetwo());
        holder.ivBackground.setImageResource(R.mipmap.yuanyuanma2);

        return convertView;
    }
    class ViewHolder{
        ImageView ivicon;//icon
        TextView tv_header_recommend_activity_title;//首页推荐活动标题
        TextView tv_header_recommend_activity_text;//首页推荐活动正文
        TextView tv_header_recommend_activity_date;//日期
        TextView tv_header_recommend_activity_person;//人数
        TextView age;//年龄
        TextView attributeone;//属性1
        TextView attributetwo;//属性2
        ImageView ivBackground;//背景图片
    }
}
