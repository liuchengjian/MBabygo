package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.adapter.ResumeAdapter;

/**
 * 作者：柳成建
 * 日期：2016/11/18 - 10:43
 * 注释：推荐活动更多详情界面Activity
 */
public class MoreInformationActivity extends AutoLayoutActivity {
    ViewPager mPager;
    @ViewInject(R.id.app_bar_layout)
    private AppBarLayout appBarLayout;
    @ViewInject(R.id.collapsingToolbarLayout)
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    @ViewInject(R.id.fl_header_more)
    private FrameLayout fl_header_more;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_information);
        Intent intent = getIntent();
        x.view().inject(this);
//      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        //addOnOffsetChangedListener监听折叠收缩的位移
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -fl_header_more.getHeight() / 2) {
                    mCollapsingToolbarLayout.setTitle("更多详情");
//                    mCollapsingToolbarLayout.setCollapsedTitleGravity(Gravity.CENTER);
                    mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色
                } else {
                    mCollapsingToolbarLayout.setTitle(" ");
                    mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
                }
            }
        });

        // 设置返回主页的按钮
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        // ViewPager
        mPager = (ViewPager) findViewById(R.id.viewPager);
        ResumeAdapter mPagerAdapter = new ResumeAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(mPager);
        // ViewPager切换时NestedScrollView滑动到顶部
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPager.setCurrentItem(tab.getPosition());//联动
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                ((NestedScrollView) findViewById(R.id.nestedScrollView)).scrollTo(0, 0);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }
}
