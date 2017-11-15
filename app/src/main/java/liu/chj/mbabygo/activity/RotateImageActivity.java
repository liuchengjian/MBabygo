package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.app.StatusBarUtils;
import liu.chj.mbabygo.fragment.FrientFragment;
import liu.chj.mbabygo.fragment.HeaderActivitySynopsisFragment;

/**
 * 作者：轮播图跳转界面
 * 日期：2016/11/8 - 9:48
 * 注释：
 */
public class RotateImageActivity extends AppCompatActivity implements View.OnClickListener{
    private TabLayout tabLayout;
    private FragmentAdapter fmAapter;
    private Toolbar Mtoolbar;
    private AppBarLayout app_bar;
    private TextView tv_synopsis_title;
    @ViewInject(R.id.iv_synopsis_back)
    private ImageView iv_synopsis_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rotateimage);
        Intent intent = getIntent();
        x.view().inject(this);
        StatusBarUtils.from(this)
                //沉浸状态栏
                .setTransparentStatusbar(true)
                //白底黑字状态栏
//                .setLightStatusBar(true)
                .setTransparentStatusbar(true)
                .setTransparentNavigationbar(true)
                //设置toolbar,actionbar等view
//                .setActionbarView(mNavigationBar)
                .process();
        Mtoolbar = (Toolbar) findViewById(R.id.toolbar_more);
        app_bar =  (AppBarLayout) findViewById(R.id.app_synopsis_bar);
        tabLayout= (TabLayout) findViewById(R.id.tab_layout);
        tv_synopsis_title = (TextView)findViewById(R.id.tv_synopsis_title);
        setSupportActionBar(Mtoolbar);
        iv_synopsis_back.setOnClickListener(this);
        app_bar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if( state == State.EXPANDED ) {
                    tv_synopsis_title.setVisibility(View.INVISIBLE);
                    //展开状态
                    Log.e("lchj","展开状态");
                }else if(state == State.COLLAPSED){
                    tv_synopsis_title.setVisibility(View.VISIBLE);
                    //折叠状态
                    Log.e("lchj","折叠状态");

                }else {
                    tv_synopsis_title.setVisibility(View.INVISIBLE);
                    Log.e("lchj","中间状态");
                    //中间状态

                }
            }
        });

        List<Fragment> fmList = new ArrayList<Fragment>();
        if(fmList!=null){
            ArrayList<String> nameList=new ArrayList<>();
            for(int i=1;i<5;i++){
                nameList.add("第"+i+"天");
            }
            HeaderActivitySynopsisFragment ff=HeaderActivitySynopsisFragment.newInstance(nameList);
            fmList.add(ff);
            FrientFragment ff2=FrientFragment.newInstance(nameList);
            fmList.add(ff2);
//            RoutingActivityFragment tf1=RoutingActivityFragment.newInstance("this is tabfragment 1");
//            fmList.add(tf1);
        }

        fmAapter=new FragmentAdapter(getSupportFragmentManager(),fmList);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(fmAapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("简介");
        tabLayout.getTabAt(1).setText("行程");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_synopsis_back:
                finish();
                break;
            default:
                break;
        }
    }

    class FragmentAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> fmList=new ArrayList<Fragment>();

        public FragmentAdapter(FragmentManager fm, List<Fragment>fmList) {
            super(fm);
            this.fmList=fmList;
        }

        @Override
        public Fragment getItem(int position) {
            return fmList.get(position);
        }

        @Override
        public int getCount() {
            if(fmList==null){
                return 0;
            }else{
                return fmList.size();
            }
        }
    }

}
