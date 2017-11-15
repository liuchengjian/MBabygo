package liu.chj.mbabygo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.adapter.EnterActivityRecycleAdapter;
import liu.chj.mbabygo.cehua.ListViewDecoration;
import liu.chj.mbabygo.cehua.OnItemClickListener;
import liu.chj.mbabygo.cehua.followerDecoration;
import liu.chj.mbabygo.cutpicture.SelectDialog;
import liu.chj.mbabygo.entity.Activity;
import liu.chj.mbabygo.entity.Follow;

/**
 * 作者：柳成建
 * 日期：2017/1/5 - 9:26
 * 注释：已报名的活动Fragment
 */
public class EnterForActivityFragment extends Fragment{
    @ViewInject(R.id.rv_enter_for_activity)
    private RecyclerView rv_enter_for_activity;
    private List<Activity> listenter = new ArrayList<>();
    private EnterActivityRecycleAdapter madpter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_enter_for_activity,null);
        x.view().inject(this,view);
        rv_enter_for_activity.setLayoutManager(new LinearLayoutManager(getActivity()));
        setEnterData();
        madpter = new EnterActivityRecycleAdapter(listenter);
        madpter.setOnItemClickListener(onItemClickListener);
        rv_enter_for_activity.setHasFixedSize(true);
        rv_enter_for_activity.setAdapter(madpter);
        rv_enter_for_activity.addItemDecoration(new ListViewDecoration());// 添加分割线。
        return view;
    }
    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(final int position) {
            /**
             * 点击列表实现业务逻辑，目前没有业务
             */
//            Toast.makeText(MyFollowerActivity.this, "我是第" + position + "条。", Toast.LENGTH_SHORT).show();
        }
    };
    private void setEnterData(){
        for(int i=0;i<5;i++){
            listenter.add(new Activity("孙悟空大闹天空","和妈妈一起去成都玩",
                    "6 +","手工课","趣味活动","文化传媒kv工作室","2020.10.10",
                    "500人以参加",R.mipmap.xianshi2,R.mipmap.yuanyuanma1));
        }
        for(int i=0;i<5;i++){
            listenter.add(new Activity("孙悟空大闹天空","",
                    "6 +","手工课","趣味活动","文化传媒kv工作室","2020.10.10",
                    "500人以参加",R.mipmap.remen2,R.mipmap.yuanyuanma2));
        }
    }
}
