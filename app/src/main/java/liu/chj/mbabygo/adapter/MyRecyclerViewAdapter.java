package liu.chj.mbabygo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import liu.chj.mbabygo.R;

/**
 * 作者：lchj
 * 日期：2016/11/24 - 14:07
 * 注释：
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    public static final int TYPE_THREE = 3;

    private LayoutInflater layoutInflater;
    private List<String> datatext;
    private List<Object> allList = new ArrayList<>();
    private List<Integer> types = new ArrayList<>();
    //    private Map<Integer,Integer> mPosition = new HashMap<Integer,Integer>();
    private Map<Integer,Integer> mPosition = new HashMap<>();
    private Context context;

    public MyRecyclerViewAdapter(Context context , List<String> datatext) {
        addListByType(TYPE_ONE, datatext);
        allList.addAll(datatext);
        this.datatext = datatext;
        this.context = context;
        layoutInflater = layoutInflater.from(context);

    }

    private void addListByType(int type,List list){
        mPosition.put(type,list.size());
        for (int i = 0;i<list.size();i++){
            types.add(type);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 1:
                return new TextViewHolder(layoutInflater.inflate(R.layout.item_string,parent,false));//false 代表不加入根视图（根布局）
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return types.get(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        int viewType = getItemViewType(position);
//        ((TypeAbstractViewHolder)holder).bindHolder(dataList.get(position));
        int viewType = getItemViewType(position);
        int realPosition = 0;
        Object object = allList.get(position);
        if(object instanceof String){
            for (int i=0;i<datatext.size();i++){
                if(object == datatext.get(i)){
                    realPosition = i;
                }
            }
        }

        switch (viewType){
            case 1:
                ((TextViewHolder)holder).bindHolder(datatext.get(realPosition));
                break;
        }
    }

    @Override
    public int getItemCount(){
        return types.size();
    }
}
