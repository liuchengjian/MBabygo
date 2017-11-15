package liu.chj.mbabygo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.activity.NoScrollListView;
import liu.chj.mbabygo.activity.RecommendArticleActivity;
import liu.chj.mbabygo.adapter.ActivityRoutingAdapter;
import liu.chj.mbabygo.entity.Routing;

/**
 * 作者：柳成建
 * 日期：2016/11/28 - 9:32
 * 注释：首页活动行程列表
 */
public class ActivityRoutingFragment extends Fragment{
    private ActivityRoutingAdapter adapter;
    private List<Routing>mRoutingList = new ArrayList<>();
    @ViewInject(R.id.lv_activity_routing)
    private NoScrollListView mlistview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_activity_routing,null);
        setData();
        x.view().inject(getActivity(),view);
        adapter = new ActivityRoutingAdapter(getActivity(),mRoutingList);
        if(mlistview.getAdapter()==null){
            mlistview.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }

//        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(),RecommendArticleActivity.class);
//                startActivity(intent);
//            }
//        });
        return view;
    }
    private void setData(){
        for (int i = 0; i < 3; i++) {
           mRoutingList.add(new Routing("第"+i+1+"天"));
        }
    }

}
