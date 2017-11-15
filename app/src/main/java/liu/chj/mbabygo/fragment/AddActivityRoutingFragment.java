package liu.chj.mbabygo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import liu.chj.mbabygo.R;

/**
 * 作者：柳成建
 * 日期：2016/12/23 - 9:24
 * 注释：活动详情行程添加界面Fragment
 */
public class AddActivityRoutingFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_add_activity_routing,null);
        return view;
    }
}
