package liu.chj.mbabygo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.activity.LocationPublishActivity;

/**
 * 作者：柳成建
 * 日期：2017/1/9 - 9:31
 * 注释：高德地图fragment
 * */
public class GDMapFragment extends Fragment implements View.OnClickListener{
    @ViewInject(R.id.tabL_gdmap)
    private TabLayout tabL_gdmap;
    @ViewInject(R.id.viewpager_gdmap)
    private ViewPager mViewpager;
    //定位发布按钮
    @ViewInject(R.id.iv_gdmap_location)
    private ImageView iv_gdmap_location_publish;


    private List<Fragment> list;
    private GDMapAdapter adapter;
    private String[] titles = {"所有", "玩耍","美食", "学习"};
    private int images[] = {R.drawable.selector_gdmap_all, R.drawable.selector_gdmap_play, R.drawable.selector_doyen_food,R.drawable.selector_doyen_study};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate
                (R.layout.fragment_gdmap, null);
        x.view().inject(this, view);
        setViewpager();
        iv_gdmap_location_publish.setOnClickListener(this);
        return view;
    }
    public void onClick(View v){
        switch (v.getId()){
            /**
             * 定位发布按钮
             */
            case R.id.iv_gdmap_location:
                Intent intent = new Intent(getActivity(),LocationPublishActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    /**
     * 设置viewpager和TabLayout的联动
     */
    private void setViewpager(){
        //页面，数据源
        list = new ArrayList<>();
        list.add(new GdAllMapFragment());
        list.add(new MyFragment());
        list.add(new GDMapPlayFragment());
        list.add(new GdAllMapFragment());
        //ViewPager的适配器
        adapter = new GDMapAdapter(getChildFragmentManager(), getActivity());
        mViewpager.setAdapter(adapter);
        //绑定
        tabL_gdmap.setupWithViewPager(mViewpager);
        //MODE_SCROLLABLE可滑动的展示
        //MODE_FIXED固定展示
        tabL_gdmap.setTabMode(TabLayout.MODE_FIXED);
        //设置自定义视图
        for (int i = 0; i <tabL_gdmap.getTabCount(); i++) {
            TabLayout.Tab tab = tabL_gdmap.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
        }
    }




    /**
     * 自定义FragmentPagerAdapter
     */
    class GDMapAdapter extends FragmentPagerAdapter {

        private Context context;

        public GDMapAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        /**
         * 自定义方法，提供自定义Tab
         *
         * @param position 位置
         * @return 返回Tab的View
         */
        public View getTabView(int position) {
            View v = LayoutInflater.from(context).inflate(R.layout.doyen_navigation_bar, null);
            TextView textView = (TextView) v.findViewById(R.id.tv_doyen_navigation_bar);
            ImageView imageView = (ImageView) v.findViewById(R.id.iv_doyen_navigation_bar);
            textView.setText(titles[position]);
            imageView.setImageResource(images[position]);
            //添加一行，设置颜色
            return v;
        }
    }

}
