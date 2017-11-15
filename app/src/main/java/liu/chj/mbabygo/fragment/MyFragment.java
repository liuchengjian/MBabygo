package liu.chj.mbabygo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chendong.gank.library.SuperBadgeHelper;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.Tag.Tag;
import liu.chj.mbabygo.activity.ActivityMyActivity;
import liu.chj.mbabygo.activity.ApplyDoyenActivity;
import liu.chj.mbabygo.activity.LoginActivity;
import liu.chj.mbabygo.activity.MyAllActivity;
import liu.chj.mbabygo.activity.MyCollectActivity;
import liu.chj.mbabygo.activity.MyFollowActivity;
import liu.chj.mbabygo.activity.MyFollowerActivity;
import liu.chj.mbabygo.activity.MyNewsActivity;
import liu.chj.mbabygo.activity.MyPraiselActivity;
import liu.chj.mbabygo.activity.PersonalProfileActivity;
import liu.chj.mbabygo.activity.SystemSetActivity;
import liu.chj.mbabygo.app.StatusBarUtils;


/**
 * 作者：柳成建
 * 日期：2016/11/3 - 14:56
 * 注释：
 */
public class MyFragment extends Fragment implements View.OnClickListener,Tag{
    @ViewInject(R.id.iv_my_Photo)
    private ImageView iv_my_photo;
    /**
     * 登录和注册
     */
    @ViewInject(R.id.iv_login_and_register)
    private ImageView iv_login_and_register;
    /**
     *消息
     */
    @ViewInject(R.id.rl_my_news)
    private RelativeLayout rl_my_news;
    /**
     *我的活动
     */
    @ViewInject(R.id.rl_my_activity)
    private RelativeLayout rl_my_activity;
    /**
     *设置
     */
    @ViewInject(R.id.rl_my_set)
    private RelativeLayout rl_my_set;
    /**
     *收藏
     */
    @ViewInject(R.id.rl_my_collect)
    private RelativeLayout rl_my_collect;
    /**
     *点赞
     */
    @ViewInject(R.id.rl_my_praise)
    private RelativeLayout rl_my_praise;
    /**
     *申请达人
     */
    @ViewInject(R.id.ll_my_apply_doyen)
    private LinearLayout ll_my_apply_doyen;
    /**
     *动态
     */
    @ViewInject(R.id.ll_my_activity)
    private LinearLayout ll_my_activity;
    /**
     *关注
     */
    @ViewInject(R.id.ll_my_follow)
    private LinearLayout ll_my_follow;
    /**
     *粉丝
     */
    @ViewInject(R.id.ll_my_follower)
    private LinearLayout ll_my_follower;
    SuperBadgeHelper superBadgeHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, null);

        x.view().inject(this, view);
        iv_my_photo.setOnClickListener(this);
        iv_login_and_register.setOnClickListener(this);
        ll_my_apply_doyen.setOnClickListener(this);
        rl_my_set.setOnClickListener(this);
        ll_my_activity.setOnClickListener(this);
        ll_my_follow.setOnClickListener(this);
        ll_my_follower.setOnClickListener(this);
        rl_my_news.setOnClickListener(this);
        rl_my_activity.setOnClickListener(this);
        rl_my_praise.setOnClickListener(this);
        rl_my_collect.setOnClickListener(this);
//        superBadgeHelper = SuperBadgeHelper.init(getActivity(),rl_my_news, Tag.sendTag,5);
//        superBadgeHelper.bindView(Tag.MyTag);
        return view;
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.iv_my_Photo:
                Intent personalprofile = new Intent(getActivity(), PersonalProfileActivity.class);
                startActivity(personalprofile);
                break;
            case R.id.iv_login_and_register:
                Intent login_and_register= new Intent(getActivity(), LoginActivity.class);
                startActivity(login_and_register);
                break;
            case R.id.ll_my_apply_doyen:
                Intent apply_doyen= new Intent(getActivity(), ApplyDoyenActivity.class);
                startActivity(apply_doyen);
                break;
            /**
             * 系统设置
             */
            case R.id.rl_my_set:
                Intent my_set= new Intent(getActivity(), SystemSetActivity.class);
                startActivity(my_set);
                break;
            /**
             * 动态
             */
            case R.id.ll_my_activity:
                Intent my_activityt= new Intent(getActivity(),MyAllActivity.class);
                startActivity(my_activityt);
                break;
            /**
             * 关注
             */
            case R.id.ll_my_follow:
                Intent my_follow= new Intent(getActivity(),MyFollowActivity.class);
                startActivity(my_follow);
                break;
            /**
             * 粉丝
             */
            case R.id.ll_my_follower:
                Intent my_follower= new Intent(getActivity(),MyFollowerActivity.class);
                startActivity(my_follower);
                break;
            /**
             * 消息
             */
            case R.id.rl_my_news:
                Intent my_news= new Intent(getActivity(),MyNewsActivity.class);
                startActivity(my_news);
//                superBadgeHelper.read();
                break;
            /**
             * 我的活动
             */
            case R.id.rl_my_activity:
                Intent my_activity= new Intent(getActivity(),ActivityMyActivity.class);
                startActivity(my_activity);
                break;
            /**
             * 点赞
             */
            case R.id.rl_my_praise:
                Intent my_praise= new Intent(getActivity(),MyPraiselActivity.class);
                startActivity(my_praise);
                break;
            case R.id.rl_my_collect:
                Intent my_collect= new Intent(getActivity(),MyCollectActivity.class);
                startActivity(my_collect);
                break;
            default:
                break;
        }
    }

}
