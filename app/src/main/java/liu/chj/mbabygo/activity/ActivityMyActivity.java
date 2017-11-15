package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.adapter.FragmentAdapter;
import liu.chj.mbabygo.fragment.AddActivityRoutingFragment;
import liu.chj.mbabygo.fragment.AddActivitySynopsisFragment;
import liu.chj.mbabygo.fragment.EnterForActivityFragment;

/**
 * 作者：柳成建
 * 日期：2016/12/22 - 16:15
 * 注释：我的活动界面
 */
public class ActivityMyActivity extends AutoLayoutActivity{
    @ViewInject(R.id.vp_activity_my)
    private ViewPager vp_activity_my;
    @ViewInject(R.id.activity_my_tabLayout)
    private TabLayout activity_my_tabLayout;
    private FragmentAdapter adapter;
    private List<Fragment>listFragment = new ArrayList<>();
    private List<String>WriteTitle = new ArrayList<>() ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_my);
        Intent intent = getIntent();
        x.view().inject(this);
        Bundle bundleCode=new Bundle();
        bundleCode.putString("gid", "1");
//        Fragment aasf = new AddActivitySynopsisFragment();
//        aasf.setArguments(bundleCode);
        listFragment.add(new EnterForActivityFragment());
        listFragment.add(new AddActivityRoutingFragment());
        WriteTitle.add("已报名");
        WriteTitle.add("已完成");
        adapter = new FragmentAdapter(getSupportFragmentManager(),WriteTitle,listFragment);
        // 设置适配器
        vp_activity_my.setAdapter(adapter);
        // 直接绑定viewpager，消除了以前的需要设置监听器的繁杂工作
        activity_my_tabLayout.setupWithViewPager(vp_activity_my);
    }

}
