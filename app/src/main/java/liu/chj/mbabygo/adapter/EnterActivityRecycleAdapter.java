package liu.chj.mbabygo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.cehua.OnItemClickListener;
import liu.chj.mbabygo.entity.Activity;
import liu.chj.mbabygo.entity.Follow;

/**
 * 作者：柳成建
 * 日期：2017/1/4 - 9:52
 * 注释：
 */
public class EnterActivityRecycleAdapter extends RecyclerView.Adapter<EnterActivityRecycleAdapter.EnterAvtivityHolder>{

    private List<Activity> activity;

    private OnItemClickListener mOnItemClickListener;

    public EnterActivityRecycleAdapter(List<Activity> activity) {
        this.activity = activity;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return activity == null ? 0 : activity.size();
    }

    @Override
    public EnterAvtivityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        EnterAvtivityHolder holder = new EnterAvtivityHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.list_enter_for_activity, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(EnterActivityRecycleAdapter.EnterAvtivityHolder holder, int position) {
        holder.setData(activity.get(position));
        holder.setOnItemClickListener(mOnItemClickListener);
    }

    static class EnterAvtivityHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /**
         * 列表是否被标记，默认是
         */
        private Boolean isIcon = true;
        TextView NameAndTheme;
        TextView tv_enter_address;
        TextView tv_enter_time;
        ImageView iv_enter_background;
        ImageView iv_enter_icon;
        TextView tv_enter_title;//
        TextView tv_enter_activity_enter;//
        OnItemClickListener mOnItemClickListener;

        public EnterAvtivityHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            NameAndTheme = (TextView) itemView.findViewById(R.id.tv_name_and_theme);
            tv_enter_address = (TextView) itemView.findViewById(R.id.tv_enter_address);
            tv_enter_time = (TextView) itemView.findViewById(R.id.tv_enter_time);
            iv_enter_background = (ImageView) itemView.findViewById(R.id.iv_enter_background);
            iv_enter_icon = (ImageView) itemView.findViewById(R.id.iv_enter_icon);
            tv_enter_title = (TextView) itemView.findViewById(R.id.tv_enter_title);
            tv_enter_activity_enter = (TextView) itemView.findViewById(R.id.tv_enter_activity_enter);
        }


        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        public void setData(Activity activity) {
            if (activity.getText().equals("")){
                this.NameAndTheme.setText(activity.getTitle());
            }else{
                this.NameAndTheme.setText("《"+activity.getTitle()+"》"+"—— "+activity.getText());
            }
            this.tv_enter_address.setText(activity.getPublishaddress());
            this.tv_enter_time.setText(activity.getDate());
            this.iv_enter_background.setImageResource(activity.getImgBackgound());
            this.iv_enter_icon.setImageResource(activity.getImgicon());
            this.tv_enter_title.setText(activity.getTitle());
            this.tv_enter_activity_enter.setText("火热报名中");
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
