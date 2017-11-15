package liu.chj.mbabygo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.rd.PageIndicatorView;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.adapter.HomeAdapter;
import liu.chj.mbabygo.app.StatusBarUtils;

/**
 * 作者：柳成建
 * 日期：2017/1/17 - 10:49
 * 注释：引导页
 */
public class GuideActivity extends AutoLayoutActivity {
    List<View> pageList = new ArrayList<>();
    PageIndicatorView pageIndicatorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        pageIndicatorView =(PageIndicatorView) findViewById(R.id.pageIndicatorView);
//        pageIndicatorView.setSelection(3);
//        pageIndicatorView.setCount(2);
        pageIndicatorView.setDynamicCount(true);
        pageIndicatorView.setUnselectedColor(Color.parseColor("#f5f5f5"));
        pageIndicatorView.setSelectedColor(Color.parseColor("#ffbf41"));
        initViews();
        StatusBarUtils.from(this)
                //沉浸状态栏
                .setTransparentStatusbar(true)
                //白底黑字状态栏
                .setLightStatusBar(true)
                .setTransparentStatusbar(true)
                .setTransparentNavigationbar(true)
                //设置toolbar,actionbar等view
//                .setActionbarView(mNavigationBar)
                .process();

    }
    public void initViews() {
        final HomeAdapter adapter = new HomeAdapter(pageList,this);
        adapter.setData(createPageList());

        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("lchj","11111111111111");
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("lchj","222222222222");
                if (position ==pageList.size()-1){
                    pageIndicatorView.setVisibility(View.INVISIBLE);
                }else {
                    pageIndicatorView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e("lchj","33333333333");

            }
        });
        pager.setAdapter(adapter);
    }

    private List<View> createPageList() {
        LayoutInflater inflater = LayoutInflater.from(this);
        // 初始化引导图片列表
        pageList.add(inflater.inflate(R.layout.what_new_one, null));
        pageList.add(inflater.inflate(R.layout.what_new_two, null));
        pageList.add(inflater.inflate(R.layout.what_new_three, null));

//        pageList.add(createPageView(R.color.google_red));
//        pageList.add(createPageView(R.color.google_blue));
//        pageList.add(createPageView(R.color.google_yellow));
//        pageList.add(createPageView(R.color.google_green));

        return pageList;
    }

    @NonNull
    private View createPageView(int color) {
        View view = new View(this);
        view.setBackgroundColor(getResources().getColor(color));

        return view;
    }
}
