package liu.chj.mbabygo.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.adapter.ItemAdapter;

/**
 * @ author：lchj
 */
public class ListFragment extends BaseFragment {

    RecyclerView mRecyclerView;
    private static final String KEY="key";
    private String title="测试";

    List<String> mDatas=new ArrayList<>();
    private ItemAdapter mAdapter;

    public static ListFragment newInstance(String title){
        ListFragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY,title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        Bundle arguments = getArguments();
        if(arguments!=null){
            title=arguments.getString(KEY);
        }
        mRecyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
//        DividerItemDecoration itemDecoration = new DividerItemDecoration(mContext,
//                LinearLayoutManager.VERTICAL);
//        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setLayoutManager(layoutManager);

        for(int i=0;i<3;i++){
//            String s = String.format("我是第%d个" + title, i);
            String s = String.format("广东省的撒谎都会的飒飒哈哈电话安徽的" +
                    "回答广东省的撒谎都会的飒飒哈哈电话安徽的回答" +
                    "广东省的撒谎都会的飒飒哈哈电话安徽的回答广东省的撒谎都会" +
                    "的飒飒哈哈电话安徽的回答广东省的撒谎都会的飒飒哈哈电话安徽的回答" +
                    "的撒谎都会的飒飒哈哈电话安徽的回答广东省的撒谎都会的" +
                    "的撒谎都会的飒飒哈哈电话安徽的回答广东省的撒谎都会的的撒谎都会的" +
                    "飒飒哈哈电话安徽的回答广东省的撒谎都会的" +
                    "飒飒哈哈电话安徽的回答广东省的撒谎都会" +
                    "的话安徽的回答广东省的撒谎都会的话安徽的" +
                    "回答广东省的撒谎都会的话安徽的回答广东省的撒谎" +
                    "都会的话安徽的回答广东省的撒谎都会的话安徽的回答广东省的撒谎都会的话安徽的回答广东省的撒谎都会的话安徽的回答广东省的撒谎都会的话安徽的回答广东省的撒谎都会的的撒谎都会的飒飒飒飒哈哈电话安徽的回答广东省的撒谎都会的的撒谎都会的飒飒" +
                    "飒飒哈哈电话安徽的回答广东省的撒谎都会的的撒谎" +
                    "都会的飒飒飒飒哈哈电话安徽的回答广东省的撒谎都会的的撒谎都会的飒飒飒飒哈哈电" +
                    "话安徽的回答广东省的撒谎都会的的撒谎都会的飒飒");

            mDatas.add(s);
        }

        mAdapter = new ItemAdapter(mContext, mDatas);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    public void fetchData() {

    }
}
