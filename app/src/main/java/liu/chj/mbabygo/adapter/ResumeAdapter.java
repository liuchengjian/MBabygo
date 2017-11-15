package liu.chj.mbabygo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import liu.chj.mbabygo.fragment.ActivityRoutingFragment;
import liu.chj.mbabygo.fragment.DoyenFragment;
import liu.chj.mbabygo.fragment.RoutingActivityFragment;

/**
 * 作者：柳成建
 * 日期：2016/11/24 - 16:12
 * 注释：
 */
public class ResumeAdapter extends FragmentPagerAdapter{
    public ResumeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (0 == position) {
            fragment = new RoutingActivityFragment();
        } else if (1 == position) {
            fragment = new ActivityRoutingFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "简介";
            case 1:
                return "行程";
        }
        return null;
    }
}
