package liu.chj.mbabygo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.activity.RecommendArticleActivity;
import liu.chj.mbabygo.adapter.DoyenHotAdapter;
import liu.chj.mbabygo.entity.Doyen;
import liu.chj.mbabygo.polltorefresh.MyListener;
import liu.chj.mbabygo.polltorefresh.PullToRefreshLayout;

/**
 * 作者：柳成建
 * 日期：2016/12/13 - 9:31
 * 注释：达人圈热门界面fragment
 */
public class HotFragment extends Fragment  {
    private PullToRefreshLayout pullToRefreshLayout;
    @ViewInject(R.id.lv_doyen_hot)
    private ListView lv_doyen_hot;
    private List<Doyen>listdoyen = new ArrayList<>();
    private DoyenHotAdapter hotAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, null);
        x.view().inject(this, view);
        ((PullToRefreshLayout) view.findViewById(R.id.refresh_view))
                .setOnRefreshListener(new MyListener());
        setdata();
        hotAdapter = new DoyenHotAdapter(getActivity(),listdoyen);
        lv_doyen_hot.setAdapter(hotAdapter);
        initListView();

        return view;
    }
    public void setdata(){
        for(int i=0;i<3;i++){

            listdoyen.add(new Doyen("123","123321","1231","12123","444","21213","142","142","",""));
        }
    }
    /**
     * ListView初始化方法
     */
    private void initListView()
    {
//        listdoyen.setOnItemLongClickListener(new OnItemLongClickListener()
//        {
//
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view,
//                                           int position, long id)
//            {
//                Toast.makeText(
//                        PullableListViewActivity.this,
//                        "LongClick on "
//                                + parent.getAdapter().getItemId(position),
//                        Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
        lv_doyen_hot.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                Intent intent = new Intent(getActivity(),RecommendArticleActivity.class);
                startActivity(intent);
//                Toast.makeText(PullableListViewActivity.this,
//                        " Click on " + parent.getAdapter().getItemId(position),
//                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
