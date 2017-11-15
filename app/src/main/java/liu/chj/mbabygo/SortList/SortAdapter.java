package liu.chj.mbabygo.SortList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.entity.People;

/**
 * 作者：柳成建
 * 日期：2016/12/5 - 11:24
 * 注释：数据的适配器类.用到的是SectionIndexer接口,它能够有效地帮助我们对分组进行控制
 */
public class SortAdapter  extends BaseAdapter implements SectionIndexer{
    private List<People> list = null;
    private Context mContext;

    public SortAdapter(Context mContext, List<People> list) {
        this.mContext = mContext;
        this.list = list;
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void updateListView(List<People> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final People mContent = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_contact, null);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.tv_sort_username);
            view.setTag(viewHolder);
            viewHolder.tvusername = (TextView) view.findViewById(R.id.tvTitle);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        int section = getSectionForPosition(position);

        if (position == getPositionForSection(section)) {
            viewHolder.tvusername.setVisibility(View.VISIBLE);
            viewHolder.tvusername.setText(mContent.getSortLetters());
        } else {
            viewHolder.tvusername.setVisibility(View.GONE);
        }

        viewHolder.tvTitle.setText(this.list.get(position).getName());

        return view;

    }


    final static class ViewHolder {
        TextView tvusername;
        TextView tvTitle;
        ImageView ivHeadimg;//头像
    }

    public int getSectionForPosition(int position) {
        return list.get(position).getSortLetters().charAt(0);
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Object[] getSections() {
        return null;
    }
}
