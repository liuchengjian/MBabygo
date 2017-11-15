package liu.chj.mbabygo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.activity.AlxRefreshLoadMoreRecyclerView;
import liu.chj.mbabygo.entity.Doyen;

/**
 * 作者：柳成建
 * 日期：2016/12/14 - 11:33
 * 注释：
 */
public class DoyenChildRecyclerViewAdapter extends
        AlxRefreshLoadMoreRecyclerView.AlxDragRecyclerViewAdapter<Doyen>{
    public DoyenChildRecyclerViewAdapter(List<Doyen> dataList, int itemLayout, boolean pullEnable) {
        super(dataList, itemLayout, pullEnable);
    }

    @Override
    public RecyclerView.ViewHolder setItemViewHolder(View itemView) {
        return new DoyenChildRecyclerViewHolder(itemView);
    }

    @Override
    public void initItemView(RecyclerView.ViewHolder itemHolder, int posion, Doyen doyen) {
        DoyenChildRecyclerViewHolder holder = (DoyenChildRecyclerViewHolder)itemHolder;
//        holder. name.setText(getDataList().get(posion-1));
        holder. name.setText(doyen.getName());
        holder.formdoyen.setText("_"+doyen.getFormdoyen());
        holder.topic.setText(doyen.getTopic());
        holder.title.setText("【"+doyen.getTitle()+"】");
        holder.text.setText(doyen.getText());
        holder.comment .setText(doyen.getComment());
        holder.transmit .setText(doyen.getTransmit());
        holder.praise .setText(doyen.getPraise());
        holder.ivHead.setImageResource(R.mipmap.yuanyuanma1);
        holder.ivBackground.setImageResource(R.mipmap.yuanyuanma2);
    }
}

class DoyenChildRecyclerViewHolder extends RecyclerView.ViewHolder{
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
    public DoyenChildRecyclerViewHolder(View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.list_doyen_hot_name);
        formdoyen = (TextView)itemView.findViewById(R.id.list_doyen_hot_from);
        topic = (TextView)itemView.findViewById(R.id.tv_doyen_hot_topic);
        title = (TextView)itemView.findViewById(R.id.list_doyen_hot_title);
        text = (TextView)itemView.findViewById(R.id.tv_doyen_hot_text);
        comment = (TextView)itemView.findViewById(R.id.tv_doyen_hot_comment);
        transmit = (TextView)itemView.findViewById(R.id.tv_doyen_hot_transmit);
        praise = (TextView)itemView.findViewById(R.id.tv_doyen_hot_praise);
        ivHead = (ImageView) itemView.findViewById(R.id.iv_doyen_hot_Photo);
        ivBackground = (ImageView) itemView.findViewById(R.id.iv_doyen_hot_background);
    }
}
