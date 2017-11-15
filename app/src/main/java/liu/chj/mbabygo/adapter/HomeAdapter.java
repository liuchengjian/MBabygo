package liu.chj.mbabygo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.activity.LoginActivity;
import liu.chj.mbabygo.activity.MainActivity;
import liu.chj.mbabygo.activity.RegisterActivity;

/**
 * 作者：柳成建
 * 日期：2017/1/17 - 11:30
 * 注释：
 */
public class HomeAdapter extends PagerAdapter {
    private List<View> viewList;
    private Activity activity;
    private static final String SHAREDPREFERENCES_NAME = "first_pref";

    public HomeAdapter(List<View> view, Activity activity) {
        this.viewList = new ArrayList<>();
        this.activity = activity;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        View view = viewList.get(position);
        collection.addView(view);
        if (position == viewList.size() - 1) {
            ImageView mStartskip = (ImageView) collection
                    .findViewById(R.id.iv_start_skip);
            ImageView mStartLogin = (ImageView) collection
                    .findViewById(R.id.iv_start_login);
            ImageView mStartRegist = (ImageView) collection
                    .findViewById(R.id.iv_start_regist);
            mStartskip.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 设置已经引导
                    setGuided();
                    goHome();
                }

            });
            mStartLogin.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 设置已经引导
                    setGuided();
                    goLogin();
                }

            });
            mStartRegist.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 设置已经引导
                    setGuided();
                    goRegist();
                }

            });
        }
        return view;
    }

    /**
     *  跳转到MainActivity
     */
    private void goHome() {

        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }
    /**
     *  跳转到登录界面
     */
    private void goLogin() {

        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }
    /**
     *  跳转到注册界面
     */
    private void goRegist() {
        // 跳转
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivity(intent);
    }
    /**
     *
     * method desc：设置已经引导过了，下次启动不用再次引导
     */
    private void setGuided() {
        SharedPreferences preferences = activity.getSharedPreferences(
                SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        // 存入数据
        editor.putBoolean("isFirstIn", false);
        // 提交修改
        editor.commit();
    }
    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void setData(@Nullable List<View> list) {
        this.viewList.clear();
        if (list != null && !list.isEmpty()) {
            this.viewList.addAll(list);
        }

        notifyDataSetChanged();
    }

    @NonNull
    List<View> getData() {
        if (viewList == null) {
            viewList = new ArrayList<>();
        }

        return viewList;
    }
}
