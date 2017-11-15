package liu.chj.mbabygo.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.entity.ItemActivityBean;
import liu.chj.mbabygo.entity.TopicBaseItem;

/**
 * 作者：柳成建
 * 日期：2017/1/5 - 14:46
 * 注释：社区列表adapter
 */
public class TopicListAdapter extends android.widget.BaseAdapter {
    private Context mContext ;//上下文
    private LayoutInflater mInflater = null;
    ViewActivityHolder viewActivityHolder = null;
    ViewArticleHolder viewArticleHolder = null;
    ViewChatHolder viewChatHolder = null;

    private List<TopicBaseItem> mData = null;//要显示的数据

    public TopicListAdapter(Context context, List<TopicBaseItem> data) {
        this.mContext =context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    //添加一个新的Item，并通知listview进行显示刷新
    public void addItem(TopicBaseItem newItem) {
        this.mData.add(newItem);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        return mData.get(position).getItem_type();
    }

    @Override
    public int getViewTypeCount() {
        return ItemType.ITEM_TYPE_MAX_COUNT;
    }

    @Override
    public int getCount() {
        if (mData == null) {
            return 0;
        }
        return this.mData.size();
    }

    @Override
    public Object getItem(int i) {

        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        int itemType = this.getItemViewType(position);
        if (itemType == ViewActivityHolder.VIEW_ACTIVITY_TYPE) {
            //第一种item

            if (convertView == null) {
                //没有缓存过
                viewActivityHolder = new ViewActivityHolder();
                convertView = this.mInflater.inflate(R.layout.list_topic_activity_item, null, false);
                viewActivityHolder.iv_topic_activity_Photo = (ImageView) convertView.findViewById(R.id.
                        iv_topic_activity_Photo);
                viewActivityHolder.iv_topic_activity_img = (ImageView) convertView.findViewById(R.id.
                        iv_topic_activity_img);
                viewActivityHolder.list_topic_activity_name = (TextView) convertView.findViewById(R.id.
                        list_topic_activity_name);
                viewActivityHolder.list_topic_activity_from = (TextView) convertView.findViewById(R.id.
                        list_topic_activity_from);
                viewActivityHolder.list_topic_activity_mian_title = (TextView) convertView.findViewById(R.id.
                        list_topic_activity_mian_title);
                viewActivityHolder.list_topic_activity_title = (TextView) convertView.findViewById(R.id.
                        list_topic_activity_title);
                viewActivityHolder.tv_topic_activity_transmit = (TextView) convertView.findViewById(R.id.
                        tv_topic_activity_transmit);
                viewActivityHolder.tv_topic_activity_comment = (TextView) convertView.findViewById(R.id.
                        tv_topic_activity_comment);
                viewActivityHolder.tv_topic_activity_praise = (TextView) convertView.findViewById(R.id.
                        tv_topic_activity_praise);
                convertView.setTag(viewActivityHolder);
            } else {
                viewActivityHolder = (ViewActivityHolder) convertView.getTag();
            }
            viewActivityHolder.iv_topic_activity_Photo.setImageResource(R.mipmap.yuanyuanma2);
            viewActivityHolder.iv_topic_activity_img.setImageResource(R.mipmap.yuanyuanma1);
            viewActivityHolder.list_topic_activity_name.setText(((ItemActivityBean) mData.get(position)).getName());
            viewActivityHolder.list_topic_activity_from.setText(" _ " + ((ItemActivityBean) mData.get(position)).getFrom());
            viewActivityHolder.list_topic_activity_mian_title.setText("我发表的活动:《" + ((ItemActivityBean) mData.get(position)).getTitle() + "》");
            viewActivityHolder.list_topic_activity_title.setText("【" + ((ItemActivityBean) mData.get(position)).getTitle() + "】");
            viewActivityHolder.tv_topic_activity_transmit.setText(((ItemActivityBean) mData.get(position)).getTransmit());
            viewActivityHolder.tv_topic_activity_comment.setText(((ItemActivityBean) mData.get(position)).getComment());
            viewActivityHolder.tv_topic_activity_praise.setText(((ItemActivityBean) mData.get(position)).getPraise());
        } else if (itemType == ViewArticleHolder.VIEW_ARTICLE_TYPE) {
            if (convertView == null) {

                viewArticleHolder = new ViewArticleHolder();
                convertView = this.mInflater.inflate(R.layout.list_topic_activity_item, null, false);
                viewArticleHolder.iv_topic_activity_Photo = (ImageView) convertView.findViewById(R.id.
                        iv_topic_activity_Photo);
                viewArticleHolder.iv_topic_activity_img = (ImageView) convertView.findViewById(R.id.
                        iv_topic_activity_img);
                viewArticleHolder.list_topic_activity_name = (TextView) convertView.findViewById(R.id.
                        list_topic_activity_name);
                viewArticleHolder.list_topic_activity_from = (TextView) convertView.findViewById(R.id.
                        list_topic_activity_from);
                viewArticleHolder.list_topic_activity_mian_title = (TextView) convertView.findViewById(R.id.
                        list_topic_activity_mian_title);
                viewArticleHolder.list_topic_activity_title = (TextView) convertView.findViewById(R.id.
                        list_topic_activity_title);
                viewArticleHolder.tv_topic_activity_transmit = (TextView) convertView.findViewById(R.id.
                        tv_topic_activity_transmit);
                viewArticleHolder.tv_topic_activity_comment = (TextView) convertView.findViewById(R.id.
                        tv_topic_activity_comment);
                viewArticleHolder.tv_topic_activity_praise = (TextView) convertView.findViewById(R.id.
                        tv_topic_activity_praise);
                convertView.setTag(viewArticleHolder);
            } else {
                viewArticleHolder = (ViewArticleHolder) convertView.getTag();
            }
            viewArticleHolder.iv_topic_activity_Photo.setImageResource(R.mipmap.yuanyuanma1);
            viewArticleHolder.iv_topic_activity_img.setImageResource(R.mipmap.yuanyuanma2);
            viewArticleHolder.list_topic_activity_name.setText(((ItemActivityBean) mData.get(position)).getName());
            viewArticleHolder.list_topic_activity_from.setText(" _ " + ((ItemActivityBean) mData.get(position)).getFrom());
            viewArticleHolder.list_topic_activity_mian_title.setText("我发表的文章:《" + ((ItemActivityBean) mData.get(position)).getTitle() + "》");
            viewArticleHolder.list_topic_activity_title.setText("【" + ((ItemActivityBean) mData.get(position)).getTitle() + "】");
            viewArticleHolder.tv_topic_activity_transmit.setText(((ItemActivityBean) mData.get(position)).getTransmit());
            viewArticleHolder.tv_topic_activity_comment.setText(((ItemActivityBean) mData.get(position)).getComment());
            viewArticleHolder.tv_topic_activity_praise.setText(((ItemActivityBean) mData.get(position)).getPraise());
        }else if (itemType == ViewChatHolder.VIEW_CHAT_TYPE){
            if (convertView == null) {
                viewChatHolder = new ViewChatHolder();
                convertView = this.mInflater.inflate(R.layout.list_topic_chat_item, null, false);
                viewChatHolder.iv_topic_chat_Photo = (ImageView) convertView.findViewById(R.id.
                        iv_topic_chat_Photo);
                viewChatHolder.list_topic_chat_name = (TextView) convertView.findViewById(R.id.
                        list_topic_chat_name);
                viewChatHolder.list_topic_chat_from = (TextView) convertView.findViewById(R.id.
                        list_topic_chat_from);
                viewChatHolder.list_topic_chat_title = (TextView) convertView.findViewById(R.id.
                        list_topic_chat_title);
                viewChatHolder.tv_topic_chat_transmit = (TextView) convertView.findViewById(R.id.
                        tv_topic_chat_transmit);
                viewChatHolder.tv_topic_chat_comment = (TextView) convertView.findViewById(R.id.
                        tv_topic_chat_comment);
                viewChatHolder.tv_topic_chat_praise = (TextView) convertView.findViewById(R.id.
                        tv_topic_chat_praise);
//                viewChatHolder.nineGridView = (NineGridView) convertView.findViewById(R.id.
//                        nineGrid);
                convertView.setTag( viewChatHolder);
            }else{
                viewChatHolder = (ViewChatHolder) convertView.getTag();
            }
//            setImage(mContext, viewChatHolder.iv_topic_chat_Photo,
//                    "http://g.hiphotos.baidu.com/image/h%3D300/sign=0a9ac84f89b1cb1321693a13ed5556da/1ad5ad6eddc451dabff9af4bb2fd5266d0163206.jpg");
//
//            ArrayList<ImageInfo> imageInfo = new ArrayList<>();
////            List<ChatPic> imageDetails = ((ItemchatBean) mData.get(position)).getChatimg();
//            List<String> imageDetails = {};
//            if (imageDetails != null) {
//                for (ChatPic chatPic : imageDetails) {
//                    ImageInfo info = new ImageInfo();
//                    info.setThumbnailUrl(chatPic.smallImageUrl);
//                    info.setBigImageUrl(chatPic.imageUrl);
//                    imageInfo.add(info);
//                }
//            }
//            viewChatHolder.nineGridView.setAdapter(new NineGridViewClickAdapter(mContext, imageInfo));
        }
        return convertView;
    }

    public class ViewActivityHolder {
        public static final int VIEW_ACTIVITY_TYPE = 0;
        ImageView iv_topic_activity_Photo;
        ImageView iv_topic_activity_img;
        TextView list_topic_activity_name;
        TextView list_topic_activity_from;
        TextView list_topic_activity_mian_title;
        TextView list_topic_activity_title;
        TextView tv_topic_activity_transmit;
        TextView tv_topic_activity_comment;
        TextView tv_topic_activity_praise;
    }

    public class ViewArticleHolder {
        public static final int VIEW_ARTICLE_TYPE = 1;
        ImageView iv_topic_activity_Photo;
        ImageView iv_topic_activity_img;
        TextView list_topic_activity_name;
        TextView list_topic_activity_from;
        TextView list_topic_activity_mian_title;
        TextView list_topic_activity_title;
        TextView tv_topic_activity_transmit;
        TextView tv_topic_activity_comment;
        TextView tv_topic_activity_praise;
    }
    public class ViewChatHolder {
        public static final int VIEW_CHAT_TYPE = 3;
        ImageView iv_topic_chat_Photo;
        TextView list_topic_chat_name;
        TextView list_topic_chat_from;
        TextView list_topic_chat_title;
        TextView tv_topic_chat_transmit;
        TextView tv_topic_chat_comment;
        TextView tv_topic_chat_praise;
//        NineGridView nineGridView;
    }

    /**
     * 说说的九宫图
     */
//    private void setImage(Context context, ImageView imageView, String url) {
//        Glide.with(context).load(url)//
//                .placeholder(R.drawable.ic_default_image)//
//                .error(R.drawable.ic_default_image)//
//                .diskCacheStrategy(DiskCacheStrategy.ALL)//
//                .into(imageView);
//    }
}
