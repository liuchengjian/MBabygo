package liu.chj.mbabygo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.activity.AlxRefreshLoadMoreRecyclerView;
import liu.chj.mbabygo.adapter.DoyenChildRecyclerViewAdapter;
import liu.chj.mbabygo.entity.Doyen;

/**
 * 作者：柳成建
 * 日期：2016/12/14 - 11:20
 * 注释：达人圈育儿界面
 */
public class ChildDoyenFragemt extends Fragment{
    private List<Doyen>listChild = new ArrayList<>();
    @ViewInject(R.id.alx_recyclerView)
    private AlxRefreshLoadMoreRecyclerView alxRecyclerView;
    DoyenChildRecyclerViewAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doyen_child, null);
        x.view().inject(this, view);
//        final AlxRefreshLoadMoreRecyclerView alxRecyclerView = (AlxRefreshLoadMoreRecyclerView) getActivity().findViewById(R.id.alx_recyclerView);
//        String[] names = {"张三","李四","王五","哈哈","格格","咳咳","发发","宝宝","嘎嘎","数控刀具","孙菲菲","郭芳芳","王欣欣","郭晓小","刘莎莎","郑哈哈","新飞飞","林丹丹","宋彤彤","李花花"};
//        final List<String> nameList = new ArrayList<>();
//        for(String s:names)nameList.add(s);
//
//        final AlxRecyclerViewAdapter adapter = new AlxRecyclerViewAdapter(nameList,R.layout.recyclerview_item,true);
        for(int i=0;i<3;i++){
            listChild.add(new Doyen("悟空","八戒","沙僧","白龙马","白骨精","90","10","10","",""));
        }
        adapter = new DoyenChildRecyclerViewAdapter(listChild,R.layout.list_doyen_hot,true);
        adapter.setPullLoadMoreEnable(true);
        alxRecyclerView.setPullLoadEnable(true);
        alxRecyclerView.setAdapter(adapter);
        alxRecyclerView.setLoadMoreListener(new AlxRefreshLoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                Log.i("Alex","现在开始加载下一页");
                new Handler().postDelayed(new Runnable() {//模拟两秒网络延迟
                    @Override
                    public void run() {
//                        String[] moreNames = {"新加的名字1","新加的名字2","新加的名字3","新加的名字4","新加的名字5","新加的名字6","新加的名字7","新加的名字8"};
//                        List<String> dataList = adapter.getDataList();
//                        for(String s:moreNames)dataList.add(s);
//                        adapter.notifyItemInserted(nameList.size() - moreNames.length + 1);
                        for(int i=0;i<2;i++){
                            listChild.add(new Doyen("七天","一周","一个人要","吃饭","睡觉","打豆豆","50","60","",""));
                        }
                        adapter.notifyItemInserted(listChild.size());
                        alxRecyclerView.stopLoadMore();
                    }
                },2000);

            }
        });
        alxRecyclerView.setOnRefreshListener(new AlxRefreshLoadMoreRecyclerView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i("Alex","现在开始刷新");
                new Handler().postDelayed(new Runnable() {//模拟两秒网络延迟
                    @Override
                    public void run() {
//                        String[] moreNames = {"刷新的名字1","刷新的名字2","刷新的名字3","刷新的名字4","刷新的名字5","刷新的名字6","刷新的名字7","刷新的名字8","刷新的名字9","刷新的名字10","刷新的名字11","刷新的名字12"};
//                        final List<String> nameList2 = new ArrayList<String>();
//                        for(String s:moreNames)nameList2.add(s);
//                        adapter.setDataList(nameList2);//重设数据
                        for(int i=0;i<1;i++){
                            listChild.add(new Doyen("电话撒上","啊等会那","大生","敖德萨","敖德萨","定位","100","1","",""));
                        }
                        adapter.notifyDataSetChanged();
                        alxRecyclerView.stopRefresh();
                    }
                },2000);
            }
        });

        return view;
    }
    class AlxRecyclerViewAdapter extends AlxRefreshLoadMoreRecyclerView.AlxDragRecyclerViewAdapter<String>{

        public AlxRecyclerViewAdapter(List<String> dataList, int itemLayout, boolean pullEnable) {
            super(dataList, itemLayout, pullEnable);
        }

        @Override
        public RecyclerView.ViewHolder setItemViewHolder(View itemView) {
            return new AlxRecyclerViewHolder(itemView);
        }

        @Override
        public void initItemView(RecyclerView.ViewHolder itemHolder, int posion, String entity) {
            AlxRecyclerViewHolder holder = (AlxRecyclerViewHolder)itemHolder;
            holder.tv1.setText(getDataList().get(posion-1));
        }
    }

    class AlxRecyclerViewHolder extends RecyclerView.ViewHolder{
        ImageView iv1;
        TextView tv1;
        public AlxRecyclerViewHolder(View itemView) {
            super(itemView);
            iv1 = (ImageView) itemView.findViewById(R.id.iv1);
            tv1 = (TextView) itemView.findViewById(R.id.tv1);
        }
    }
}
