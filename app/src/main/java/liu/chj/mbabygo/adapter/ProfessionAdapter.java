package liu.chj.mbabygo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.entity.Profession;

/**
 * 作者：柳成建
 * 日期：2016/12/26 - 17:27
 * 注释：
 */
public class ProfessionAdapter extends BaseAdapter<Profession>{
    ViewHolder holder;
    private int selectedPosition = -1;// 选中的位置
public ProfessionAdapter(Context context, List<Profession> data) {
        super(context, data);
        }
    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
    Profession profession = getData().get(position);

        if(convertView ==null){
        convertView  = getLayoutInflater().inflate(R.layout.list_profession, null);
        holder = new ViewHolder();

        holder.rl_profession = (RelativeLayout) convertView.findViewById(R.id.rl_list_profession);
        holder.choose = (ImageView) convertView.findViewById(R.id.iv_choose);
        holder.tv_list_profession = (TextView)convertView.findViewById(R.id.tv_list_profession);
        convertView.setTag(holder);
        AutoUtils.autoSize(convertView);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
//    holder.rl_profession.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            holder.choose.setVisibility(View.VISIBLE);
//            holder.choose.setImageResource(R.mipmap.textaa_choose);
//        }
//    });
        if (selectedPosition == position) {
            holder.choose.setVisibility(View.VISIBLE);
        } else {
            holder.choose.setVisibility(View.GONE);
        }
        holder.choose.setImageResource(R.mipmap.textaa_choose);
      holder.tv_list_profession.setText(profession.getProfession());


        return convertView;
        }
class ViewHolder{
    RelativeLayout rl_profession;
    ImageView choose;//选择
    TextView tv_list_profession;//行业
}
}
