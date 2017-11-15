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
import liu.chj.mbabygo.entity.Follow;

/**
 * 作者：柳成建
 * 日期：2017/1/4 - 9:52
 * 注释：
 */
public class MyFollowerRecycleAdapter extends RecyclerView.Adapter<MyFollowerRecycleAdapter.MyFollwerHolder>{

    private List<Follow> follows;

    private OnItemClickListener mOnItemClickListener;

    public MyFollowerRecycleAdapter(List<Follow> follows) {
        this.follows = follows;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return follows == null ? 0 : follows.size();
    }

    @Override
    public MyFollwerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyFollwerHolder holder = new MyFollwerHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.list_my_follow, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyFollowerRecycleAdapter.MyFollwerHolder holder, int position) {
        holder.setData(follows.get(position));
        holder.setOnItemClickListener(mOnItemClickListener);
    }

    static class MyFollwerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /**
         * 列表是否被标记，默认是
         */
        private Boolean isIcon = true;
        TextView tvname;
        TextView tv_my_follow_text;
        ImageView headimg;
        ImageView follow_yes_or_no;
        TextView tv_follow_yes_or_no;//
        LinearLayout ll_my_follow_yes_or_no;
        OnItemClickListener mOnItemClickListener;

        public MyFollwerHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            tvname = (TextView) itemView.findViewById(R.id.tv_my_follow_name);
            tv_my_follow_text = (TextView) itemView.findViewById(R.id.tv_my_follow_text);
            ll_my_follow_yes_or_no  = (LinearLayout)itemView.findViewById(R.id.ll_my_follow_yes_or_no);
            headimg = (ImageView) itemView.findViewById(R.id.iv_my_follow_headimg);
            follow_yes_or_no = (ImageView) itemView.findViewById(R.id.iv_my_follow_yes_or_no);
            tv_follow_yes_or_no = (TextView) itemView.findViewById(R.id.tv_my_follow_yes_or_no);
            ll_my_follow_yes_or_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**
                     * 判断是否被标记，然后点击完成关注
                     */
                    if(isIcon ==true){
                        tv_follow_yes_or_no.setText("已关注");
                        follow_yes_or_no.setImageResource(R.mipmap.follow_yes);
                        isIcon = false;
                    }else {
                        tv_follow_yes_or_no.setText("加关注");
                        follow_yes_or_no.setImageResource(R.mipmap.follow_no);
                        isIcon = true;
                    }
                }
            });
        }


        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        public void setData(Follow follow) {
            this.tvname.setText(follow.getName());
            this.tv_my_follow_text.setText(follow.getText());
            this.headimg.setImageResource(follow.getHeadimg());
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
