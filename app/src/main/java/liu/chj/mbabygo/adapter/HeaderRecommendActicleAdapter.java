package liu.chj.mbabygo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.entity.Article;

/**
 * 作者：柳成建
 * 日期：2016/11/15 - 13:45
 * 注释：首页推荐文章Adapter
 */
public class HeaderRecommendActicleAdapter extends BaseAdapter<Article>{
    public HeaderRecommendActicleAdapter(Context context, List<Article> data) {
        super(context, data);
        // TODO Auto-generated constructor stub
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Article article = getData().get(position);
        ViewHolder holder;
        if(convertView ==null){
            convertView  = getLayoutInflater().inflate(R.layout.list_header_recommend_article, null);
            holder = new ViewHolder();
            holder.ivHead = (ImageView) convertView.findViewById(R.id.iv_head_Photo);
            holder.mRecomendAritcleTitle = (TextView)convertView.findViewById(R.id.tv_header_recpmmend_acticler_title);
            holder.mRecomendAritcleUsername = (TextView)convertView.findViewById(R.id.tv_header_recpmmend_acticler_name);
            holder.date = (TextView)convertView.findViewById(R.id.tv_header_recpmmend_acticler_date);
            holder.ivBackground = (ImageView) convertView.findViewById(R.id.iv_background_image);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        }else{
            holder = (ViewHolder) convertView.getTag();

        }
        holder.ivHead.setImageResource(R.mipmap.yuanyuanma1);
        holder.mRecomendAritcleTitle.setText(article.getTitle());
        holder.mRecomendAritcleUsername.setText(article.getName());
        holder.date .setText(article.getDate());
        holder.ivBackground.setImageResource(R.mipmap.yuanyuanma2);

        return convertView;
    }
    class ViewHolder{
        ImageView ivHead;//头像
        TextView mRecomendAritcleTitle;//首页推荐文章标题
        TextView mRecomendAritcleUsername;//首页推荐文章用户名
        TextView date;//日期
        ImageView ivBackground;//背景图片
    }
}
